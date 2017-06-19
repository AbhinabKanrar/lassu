#!/bin/bash

# script to start web2

java -Dpartner.site=http://localhost:8081 -DAUTH_TOKEN=2ebfc0e6-7e32-4c95-81a7-f9b0980f7d04 -jar lassu-web/target/lassu-web.jar
