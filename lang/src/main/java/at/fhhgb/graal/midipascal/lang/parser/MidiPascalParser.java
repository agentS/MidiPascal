package at.fhhgb.graal.midipascal.lang.parser;

import at.fhhgb.graal.midipascal.lang.MidiPascalLanguage;
import at.fhhgb.graal.midipascal.lang.node.*;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.source.Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MidiPascalParser
{
	private MidiPascalLexer lexer;
	private List<MidiPascalSymbol> tokens;

	private int tokenIndex = 0;
	private MidiPascalSymbol currentToken = null;

	private FrameDescriptor frames;
	private Map<String, MidiPascalSymbolNode> symbols;

	public MidiPascalParser(Source source)
	{
		this.lexer = new MidiPascalLexer(new BufferedReader(source.getReader()));
	}

	public MidiPascalRootNode read(MidiPascalLanguage language)
			throws IOException, MidiPascalSyntaxException
	{
		this.frames = new FrameDescriptor();
		this.symbols = new HashMap<>();

		this.tokens = this.lexer.readSymbols();
		List<MidiPascalStatementNode> nodes = this.startExpression();
		/*
		System.out.println("---");
		System.out.println("node sequence:");
		for (MidiPascalStatementNode printed : nodes)
		{
			System.out.println(printed.getClass().getName());
		}
		System.out.println("---");
		*/

		return new MidiPascalRootNode(language, this.frames, nodes);
	}

	private List<MidiPascalStatementNode> startExpression() throws MidiPascalSyntaxException
	{
		this.tokenIndex = 0;
		this.currentToken = this.tokens.get(this.tokenIndex);

		return this.midiPascal();
	}

	private List<MidiPascalStatementNode> midiPascal() throws MidiPascalSyntaxException
	{
		if (this.currentToken == MidiPascalKeywordSymbol.PROGRAM)
		{
			this.moveToNextSymbol();

			this.identifier();
			if (this.currentToken == MidiPascalSimpleSymbol.SEMICOLON)
			{
				this.moveToNextSymbol();
			}
			else
			{
				throw new MidiPascalSyntaxException("';' expected after program name");
			}

			while (this.currentToken == MidiPascalKeywordSymbol.VARIABLE)
			{
				this.variableDeclaration();
			}
			if (this.currentToken == MidiPascalKeywordSymbol.BEGIN)
			{
				this.moveToNextSymbol();
				List<MidiPascalStatementNode> nodes = this.statementSequence(new ArrayList<>());

				if (this.currentToken == MidiPascalKeywordSymbol.END)
				{
					this.moveToNextSymbol();

					if (this.currentToken != MidiPascalSimpleSymbol.PERIOD)
					{
						throw new MidiPascalSyntaxException("'.' expected after END symbol of the main procedure");
					}
					return nodes;
				}
				else
				{
					throw new MidiPascalSyntaxException("END symbol expected after the main procedure");
				}
			}
			else
			{
				throw new MidiPascalSyntaxException("BEGIN symbol of the main procedure expected");
			}
		}
		else
		{
			throw new MidiPascalSyntaxException("PROGRAM symbol expected at the beginning of the program");
		}
	}

	private MidiPascalSymbolNode identifier() throws MidiPascalSyntaxException
	{
		MidiPascalSymbolNode result = null;
		if (this.currentToken instanceof MidiPascalTextSymbol)
		{
			result = this.symbols.get(this.currentToken.toString());
			this.moveToNextSymbol();
		}
		return result;
	}

	private void variableDeclaration() throws MidiPascalSyntaxException
	{
		if (this.currentToken == MidiPascalKeywordSymbol.VARIABLE)
		{
			this.moveToNextSymbol();
			this.identifierList();

			if (this.currentToken == MidiPascalSimpleSymbol.COLON)
			{
				this.moveToNextSymbol();
				if (this.currentToken == MidiPascalKeywordSymbol.INTEGER_DATA_TYPE)
				{
					this.moveToNextSymbol();
					if (this.currentToken == MidiPascalSimpleSymbol.SEMICOLON)
					{
						this.moveToNextSymbol();
					}
					else
					{
						throw new MidiPascalSyntaxException("';' expected after the data type of the variable declaration");
					}
				}
				else
				{
					throw new MidiPascalSyntaxException("INTEGER is the only supported data type");
				}
			}
			else
			{
				throw new MidiPascalSyntaxException("':' expected after variable identifier list");
			}
		}
	}

	private void identifierList() throws MidiPascalSyntaxException
	{
		if (this.currentToken instanceof MidiPascalTextSymbol)
		{
			String variableName = this.currentToken.toString();
			if (this.symbols.containsKey(variableName))
			{
				throw new MidiPascalSyntaxException("The variable " + variableName + " is already defined.");
			}

			MidiPascalSymbolNode addedSymbol = new MidiPascalSymbolNode
			(
					this.frames.addFrameSlot(variableName, FrameSlotKind.Int)
			);
			this.symbols.put(variableName, addedSymbol);
			this.moveToNextSymbol();

			if (this.currentToken == MidiPascalSimpleSymbol.COMMA)
			{
				this.moveToNextSymbol();
				this.identifierList();
			}
		}
		else
		{
			throw new MidiPascalSyntaxException("Identifier required for variable declaration");
		}
	}

	private List<MidiPascalStatementNode> statementSequence(List<MidiPascalStatementNode> statements) throws MidiPascalSyntaxException
	{
		if (this.currentToken != MidiPascalKeywordSymbol.END)
		{
			MidiPascalStatementNode addedStatement = this.statement();
			statements.add(addedStatement);

			if (this.currentToken == MidiPascalSimpleSymbol.SEMICOLON)
			{
				this.moveToNextSymbol();
				this.statementSequence(statements);
			}
		}

		return statements;
	}

	private MidiPascalStatementNode statement() throws MidiPascalSyntaxException
	{
		MidiPascalStatementNode result;

		if (this.currentToken instanceof MidiPascalTextSymbol)
		{
			MidiPascalSymbolNode assignedVariable = this.symbols.get(this.currentToken.toString());
			this.moveToNextSymbol();
			if (this.currentToken == MidiPascalSimpleSymbol.COLON)
			{
				this.moveToNextSymbol();
				if (this.currentToken == MidiPascalSimpleSymbol.EQUALS)
				{
					this.moveToNextSymbol();

					MidiPascalExpressionNode expression = this.expression();
					MidiPascalAssignmentNode assignment = new MidiPascalAssignmentNode(assignedVariable, expression);
					result = assignment;
				}
				else
				{
					throw new MidiPascalSyntaxException("Assignment operator expected after a variable name");
				}
			}
			else
			{
				throw new MidiPascalSyntaxException("Assignment operator expected after a variable name");
			}
		}
		else if (this.currentToken == MidiPascalKeywordSymbol.READ)
		{
			this.moveToNextSymbol();
			if (this.currentToken == MidiPascalSimpleSymbol.OPEN_PARENTHESIS)
			{
				this.moveToNextSymbol();
				if (this.currentToken instanceof MidiPascalTextSymbol)
				{
					MidiPascalSymbolNode target = this.symbols.get(this.currentToken.toString());
					MidiPascalReadNode readNode = new MidiPascalReadNode(target);
					result = readNode;

					this.moveToNextSymbol();
					if (this.currentToken == MidiPascalSimpleSymbol.CLOSING_PARENTHESIS)
					{
						this.moveToNextSymbol();
					}
					else
					{
						throw new MidiPascalSyntaxException("')' expected after the READ command's parameter");
					}
				}
				else
				{
					throw new MidiPascalSyntaxException("Identifier expected as parameter for the read command");
				}
			}
			else
			{
				throw new MidiPascalSyntaxException("'(' expected after READ command");
			}
		}
		else if (this.currentToken == MidiPascalKeywordSymbol.WRITE)
		{
			this.moveToNextSymbol();
			if (this.currentToken == MidiPascalSimpleSymbol.OPEN_PARENTHESIS)
			{
				this.moveToNextSymbol();
				MidiPascalExpressionNode parameter;
				if (this.currentToken instanceof MidiPascalTextSymbol)
				{
					parameter = this.symbols.get(this.currentToken.toString());
					this.moveToNextSymbol();
				}
				else
				{
					parameter = this.expression();
				}
				MidiPascalWriteNode writeNode = new MidiPascalWriteNode(parameter);
				result = writeNode;

				if (this.currentToken == MidiPascalSimpleSymbol.CLOSING_PARENTHESIS)
				{
					this.moveToNextSymbol();
				}
				else
				{
					throw new MidiPascalSyntaxException("')' expected after the WRITE command's parameter");
				}
			}
			else
			{
				throw new MidiPascalSyntaxException("'(' expected after the WRITE command");
			}
		}
		else if (this.currentToken == MidiPascalKeywordSymbol.IF)
		{
			this.moveToNextSymbol();
			MidiPascalSymbolNode condition = this.identifier();
			if (this.currentToken == MidiPascalKeywordSymbol.THEN)
			{
				this.moveToNextSymbol();
				MidiPascalStatementNode thenNode = this.statement();
				MidiPascalStatementNode elseNode = null;
				if (this.currentToken == MidiPascalSimpleSymbol.SEMICOLON)
				{
					this.moveToNextSymbol();
				}
				else
				{
					if (this.currentToken == MidiPascalKeywordSymbol.ELSE)
					{
						this.moveToNextSymbol();
						elseNode = this.statement();
					}
					else
					{
						throw new MidiPascalSyntaxException("Either ELSE block of ';' token expected at the end of a THEN block");
					}
				}

				MidiPascalIfNode ifNode = new MidiPascalIfNode(condition, thenNode, elseNode);
				result = ifNode;
			}
			else
			{
				throw new MidiPascalSyntaxException("THEN expected after the IF statement's variable");
			}
		}
		else if (this.currentToken == MidiPascalKeywordSymbol.WHILE)
		{
			this.moveToNextSymbol();
			MidiPascalSymbolNode condition = this.identifier();

			if (this.currentToken == MidiPascalKeywordSymbol.DO)
			{
				this.moveToNextSymbol();

				MidiPascalStatementNode body = this.statement();

				MidiPascalWhileNode loop = new MidiPascalWhileNode(condition, body);
				result = loop;
			}
			else
			{
				throw new MidiPascalSyntaxException("DO expected at the start of a WHILE loop");
			}
		}
		else if (this.currentToken == MidiPascalKeywordSymbol.BEGIN)
		{
			this.moveToNextSymbol();

			List<MidiPascalStatementNode> statements = this.statementSequence(new ArrayList<>());
			MidiPascalBlockNode blockNode = new MidiPascalBlockNode
			(
					statements.toArray
					(
							new MidiPascalStatementNode[statements.size()]
					)
			);
			result = blockNode;

			if (this.currentToken == MidiPascalKeywordSymbol.END)
			{
				this.moveToNextSymbol();
			}
			else
			{
				throw new MidiPascalSyntaxException("END expected at the end of a block");
			}
		}
		else if (this.currentToken == MidiPascalKeywordSymbol.POLYGLOT)
		{
			this.moveToNextSymbol();
			if (this.currentToken == MidiPascalSimpleSymbol.OPEN_PARENTHESIS)
			{
				this.moveToNextSymbol();
				if (this.currentToken instanceof MidiPascalStringSymbol)
				{
					String targetLanguage = this.currentToken.toString();
					this.moveToNextSymbol();

					if (this.currentToken == MidiPascalSimpleSymbol.COMMA)
					{
						this.moveToNextSymbol();

						MidiPascalExpressionNode parameter = this.expression();
						result = new MidiPascalPolyglotNode(targetLanguage, parameter);

						if (this.currentToken == MidiPascalSimpleSymbol.CLOSING_PARENTHESIS)
						{
							this.moveToNextSymbol();
						}
						else
						{
							System.out.println(this.currentToken);
							throw new MidiPascalSyntaxException("')' expected after the POLYGLOT command's parameter");
						}
					}
					else
					{
						throw new MidiPascalSyntaxException("',' expected between the two parameters of the POLYGLOT command");
					}
				}
				else
				{
					throw new MidiPascalSyntaxException("String parameter with the target language expected as the POLYGLOT command's first parameter");
				}
			}
			else
			{
				throw new MidiPascalSyntaxException("'(' expected after the POLYGLOT command");
			}
		}
		else
		{
			result = new MidiPascalEmptyStatementNode();
		}
		return result;
	}

	private MidiPascalExpressionNode expression() throws MidiPascalSyntaxException
	{
		MidiPascalExpressionNode result = null;
		MidiPascalExpressionNode leftHandSide = this.term();

		if (this.currentToken == MidiPascalSimpleSymbol.PLUS)
		{
			this.moveToNextSymbol();
			MidiPascalExpressionNode rightHandSide = this.expression();

			if ((isStringNode(leftHandSide)) || (isStringNode(rightHandSide)))
			{
				result = new MidiPascalStringConcatenationNode(leftHandSide, rightHandSide);
			}
			else
			{
				result = new MidiPascalAdditionNode(leftHandSide, rightHandSide);
			}
		}
		else if (this.currentToken == MidiPascalSimpleSymbol.MINUS)
		{
			this.moveToNextSymbol();
			MidiPascalExpressionNode rightHandSide = this.expression();
			result = new MidiPascalSubtractionNode(leftHandSide, rightHandSide);
		}
		else
		{
			result = leftHandSide;
		}
		return result;
	}

	private MidiPascalExpressionNode term() throws MidiPascalSyntaxException
	{
		MidiPascalExpressionNode result = null;
		MidiPascalExpressionNode leftHandSide = this.factor();

		if (this.currentToken == MidiPascalSimpleSymbol.MULTIPLICATION)
		{
			this.moveToNextSymbol();
			MidiPascalExpressionNode rightHandSide = this.term();
			result = new MidiPascalMultiplicationNode(leftHandSide, rightHandSide);
		}
		else if (this.currentToken == MidiPascalSimpleSymbol.DIVISION)
		{
			this.moveToNextSymbol();
			MidiPascalExpressionNode rightHandSide = this.term();
			result = new MidiPascalDivisionNode(leftHandSide, rightHandSide);
		}
		else
		{
			result = leftHandSide;
		}
		return result;
	}

	private MidiPascalExpressionNode factor() throws MidiPascalSyntaxException
	{
		MidiPascalExpressionNode result = null;
		if (this.currentToken instanceof MidiPascalNumberSymbol)
		{
			MidiPascalNumberSymbol integerLiteral = ((MidiPascalNumberSymbol) this.currentToken);
			result = new MidiPascalIntegerNode(integerLiteral.getValue());
			this.moveToNextSymbol();
		}
		else if (this.currentToken instanceof MidiPascalTextSymbol)
		{
			String variableName = this.currentToken.toString();
			result = this.symbols.get(variableName);
			this.moveToNextSymbol();
		}
		else if (this.currentToken instanceof MidiPascalStringSymbol)
		{
			result = new MidiPascalStringNode(this.currentToken.toString());
			this.moveToNextSymbol();
		}
		else if (this.currentToken == MidiPascalSimpleSymbol.OPEN_PARENTHESIS)
		{
			this.moveToNextSymbol();
			result = this.expression();
			if (this.currentToken == MidiPascalSimpleSymbol.CLOSING_PARENTHESIS)
			{
				this.moveToNextSymbol();
			}
			else
			{
				throw new MidiPascalSyntaxException("')' expected");
			}
		}
		return result;
	}

	private void moveToNextSymbol()
	{
		++this.tokenIndex;
		this.currentToken = this.tokens.get(this.tokenIndex);
	}

	private static boolean isStringNode(MidiPascalExpressionNode examined)
	{
		return ((examined instanceof MidiPascalStringNode) || (examined instanceof MidiPascalStringConcatenationNode));
	}
}
