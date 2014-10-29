#! /bin/bash

rm -f keystore.jks
keytool -storepass "123456" -keypass "123456" -genkeypair -alias selfsignedtest -keyalg RSA -keysize 2048 -keystore keystore.jks

echo "Chave gerada. Verificando:"

keytool -storepass "123456" -keypass "123456" -list -keystore keystore.jks
