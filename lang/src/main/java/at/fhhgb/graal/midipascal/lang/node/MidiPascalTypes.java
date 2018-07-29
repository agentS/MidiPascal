package at.fhhgb.graal.midipascal.lang.node;

import at.fhhgb.graal.midipascal.lang.runtime.MidiPascalFunction;
import com.oracle.truffle.api.dsl.TypeSystem;

@TypeSystem({int.class, MidiPascalFunction.class, MidiPascalSymbolNode.class})
public abstract class MidiPascalTypes
{
}
