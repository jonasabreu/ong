#! /bin/bash

JAR=pdf-signature-applet.jar
OLD_JAR=applet-assinatura-1.0.jar
DIR=build/aux_dir

gradle fatJar

rm -fr $DIR
mkdir $DIR
mv build/libs/$OLD_JAR $DIR/.

cd $DIR
jar xvf $OLD_JAR
rm -rf $OLD_JAR
rm -rf META-INF/*.SF  
rm -rf META-INF/*.DSA 
rm -rf META-INF/*.RSA
jar cvmf META-INF/MANIFEST.MF $OLD_JAR *

echo "reduzindo tamanho do jar"
#mv $OLD_JAR $JAR
cd ../..
gradle proguard

echo "copiando jar"
cp -v $DIR/$JAR . 

echo "assinando jar"

jarsigner -storepass "123456" -keypass "123456" -keystore keystore.jks $JAR selfsignedtest


