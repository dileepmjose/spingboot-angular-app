# mongodbscript
Steps
============================================================================================================================================================
    
    1)   run the command ./import_all.sh (This will Create demo db and its Collections)


=============================================================================================================================================================

# MongoDB_Resources
--------------------------------------------------------------------------------------------------------------------------------------------------------

*mongoimport --collection=[Collection Name] --db=[Database Name] --type=json --jsonArray [FileName.json]*

Example: 
----------------------------------------------------------------------------------------------------------------------------------------------------------------

- macOS: `mongoimport --collection=Recipes --db=firstDatabase --type=json --jsonArray example.json`
- Windows: `mongoimport.exe --collection=Example --db=firstDatabase --type=json --jsonArray example.json`

Please note that the **--jsonArray** parameter in the above command needs to be included when the .JSON file contains multiple MongoDB documents within a single JSON array. If the file does not have such a structure, then remove the **--jsonArray** parameter from the command. 

For instance, the "movies.json" file does not need **--jsonArray** parameter and can be imported like below: 

- macOS: `mongoimport --collection=Recipes --db=firstDatabase --type=json movies.json`
- Windows: `mongoimport.exe --collection=Example --db=firstDatabase --type=json movies.json`

----------------------------------------------------------------------------------------------------------------------------------------------------------------
Export mongodb demo

mongodump --db=demo --out=./demo_backup
--------------------------------------------------------------------------------------------------------------------------------------------------------
Import Mongodb demo

mongorestore --db=demo ./demo_backup/demo
--------------------------------------------------------------------------------------------------------------------------------------------------------
Connet to MongoDB shell
mongosh mongodb://localhost:27017/demo
--------------------------------------------------------------------------------------------------------------------------------------------------------



     