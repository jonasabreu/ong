#! /bin/bash

FILE=fechamento.csv
echo "DATA;DESCRIÇÃO;QTDE;CARTÃO;DINHEIRO;DESPESA/INVESTIMENTO" > fechamento.csv

sqlite3 ong.db < sql.sql | tr "|" ";" >> fechamento.csv
