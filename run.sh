#!/usr/bin/env bash

cd client
npm run build
cd ../server
mvn clean install
cd ..