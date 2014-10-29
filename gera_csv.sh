#! /bin/bash

sqlite3 ong.db " select STRFTIME('%Y-%m-%d', l.createdAt) as dia, sum(i.valor * i.quantidade) as ValorDia from items i join lancamentos l on i.lancamento_id = l.id where valor > 0 group by dia;" | tr "|" ";" > a.csv
