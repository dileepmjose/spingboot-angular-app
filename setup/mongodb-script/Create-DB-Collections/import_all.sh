#!/bin/bash
mongoimport --db=demo --collection=books --file=books.json 
mongoimport --db=demo --collection=country --file=country.json 
mongoimport --db=demo --collection=movies --file=movies.json
mongoimport --db=demo --collection=company --file=company.json --jsonArray
mongoimport --db=demo --collection=persons --file=persons.json --jsonArray

mongosh "mongodb://localhost:27017/demo" insert-employee-collections.js
