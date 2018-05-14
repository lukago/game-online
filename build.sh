#!/usr/bin/env bash

cd client
ng build
cd ../server
mvn clean install
cd ..