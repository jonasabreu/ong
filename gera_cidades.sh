#! /bin/bash

cat dtb_2013.csv | grep -v "^Uf" | awk -F ";" '{print "<option value=\""$1$7"\">"$8"</option>"}' | sort | uniq
