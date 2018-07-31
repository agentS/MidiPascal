#!/usr/bin/env bash

COMPONENT_DIR="component_temp_dir"
LANGUAGE_PATH="$COMPONENT_DIR/jre/languages/midipascal"

rm -rf COMPONENT_DIR

mkdir -p "$LANGUAGE_PATH"
cp ../lang/target/midipascal_language.jar "$LANGUAGE_PATH"

mkdir -p "$LANGUAGE_PATH/launcher"
cp ../launcher/target/midipascal_launcher.jar "$LANGUAGE_PATH/launcher/"

mkdir -p "$LANGUAGE_PATH/bin"
cp ../mp $LANGUAGE_PATH/bin/

mkdir -p "$COMPONENT_DIR/META-INF"
MANIFEST="$COMPONENT_DIR/META-INF/MANIFEST.MF"
touch "$MANIFEST"
echo "Bundle-Name: MidiPascal" >> "$MANIFEST"
echo "Bundle-Symbolic-Name: agents.midipascal" >> "$MANIFEST"
echo "Bundle-Version: 0.0" >> "$MANIFEST"
echo 'Bundle-RequireCapability: org.graalvm; filter:="(&(graalvm_version=1.0.0-rc4)(os_arch=amd64))"' >> "$MANIFEST"
echo "x-GraalVM-Polyglot-Part: True" >> "$MANIFEST"

cd $COMPONENT_DIR
jar cfm ../midipascal-component.jar META-INF/MANIFEST.MF .

echo "bin/mp = ../jre/languages/midipascal/bin/mp" > META-INF/symlinks
jar uf ../midipascal-component.jar META-INF/symlinks

echo "jre/languages/midipascal/bin/mp = rwxrwxr-x" > META-INF/permissions
jar uf ../midipascal-component.jar META-INF/permissions
cd ..
rm -rf $COMPONENT_DIR
