#! /bin/bash

openssl genrsa -out my_key.key 2048
openssl req -new -key my_key.key -out my_request.csr
openssl x509 -req -days 3650 -in my_request.csr -signkey my_key.key -out my_cert.crt
openssl pkcs12 -keypbe PBE-SHA1-3DES -certpbe PBE-SHA1-3DES -export -in my_cert.crt -inkey my_key.key -out cert.pfx -name "my-name"

rm -f my_key.key my_request.csr my_cert.crt 
