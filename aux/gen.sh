#! /bin/bash

FILE=fechamento.csv
echo "DATA;DESCRIÇÃO;QTDE;CARTÃO;DINHEIRO;DESPESA/INVESTIMENTO" > fechamento.csv

sqlite3 ong.db < ../src/main/resources/query/fechamento.sql | tr "|" ";" >> fechamento.csv
