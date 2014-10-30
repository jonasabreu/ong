#! /bin/bash

cat dtb_2013.csv | awk -F ";" '{print $1$7":\""$8"\","}' | sort | uniq
