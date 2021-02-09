#!/bin/bash

mongoimport --host mk-mongo-db --db user --collection users --type json --file /users.json --jsonArray
mongoimport --host mk-mongo-db --db product --collection products --type json --file /products.json --jsonArray
