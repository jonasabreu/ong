#! /bin/bash

echo "================> Gerando war"
sbt package

echo "================> Copiando war"
scp target/scala-2.10/*.war play:~/ong.war

echo "================> Derrubando jetty"
ssh play "~/jetty/bin/jetty.sh stop"

echo "================> Backup do db"
ssh play "cp -v ~/jetty/ong.db ."

echo "================> Deploy do war"
ssh play "cp -v ong.war jetty/webapps/."

echo "================> Subindo jetty"
ssh play "~/jetty/bin/jetty.sh start"

echo "================> Verificando logs. Ctrl+c para encerrar."
ssh play "tail -f jetty/logs/`date '+%Y_%m_%d.stderrout.log'`"

echo "================> Fim :)"

