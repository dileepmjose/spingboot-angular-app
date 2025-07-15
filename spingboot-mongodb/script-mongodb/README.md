--------------------------------------------------------------------------------------------------------------------------------------------------------
Connet to MongoDB shell
mongosh mongodb://localhost:27017/demo

db.movie.find();
db.country.find();
db.theaters.find();

db.country.find();
db.country.find({  borders: { $all: ["IND"] }}) 
--------------------------------------------------------------------------------------------------------------------------------------------------------



How to Run
------------
1. Insert Records to mongodb

   mongosh insert_collections.js     

2. Connect Mongodb Shell 

    mongosh "mongodb://localhost:27017/hr_management"  
   

3. Run the Each comment from file to understandFunctilaity
    mongodb-script-keyfunction

4. Run displayEmployee.js file see Employee Details

Keyword	Description
-----------------------------------------------------------
show dbs	            Lists all databases
use <db>	            Switch to or create a database
db	Shows               current database
show collections	    Lists all collections in current DB
db.createCollection()	Creates a new collection
db.dropDatabase()	    Deletes the current database
db.collection.drop()	Deletes a collection
-----------------------------------------------------------
📋 Document Operations
-----------------------------------------------------------
Keyword	Description
insertOne()	    Insert a single document
insertMany()	Insert multiple documents
find()	        Query documents
findOne()	    Returns one matching document
updateOne()	    Update one document
updateMany()	Update multiple documents
deleteOne()	    Delete one document
deleteMany()	Delete multiple documents
replaceOne()	Replace a document
------------------------------------------------------------
🧠 Query Operators
-----------------------------------------------------------
🟢 Comparison
Operator	Description
-----------------------------------------------------------
$eq	    Equals
$ne	    Not equal
$gt	    Greater than
$gte    Greater than or equal
$lt	    Less than
$lte	Less than or equal
$in	    Matches any value in array
$nin	Does not match any in array
-----------------------------------------------------------
🟠 Logical
-----------------------------------------------------------
Operator	Description
-----------------------------------------------------------
$and	Joins query clauses with AND
$or	    Joins query clauses with OR
$not	Inverts the effect of a query
$nor	Matches if all fail

🔵 Element
-----------------------------------------------------------
Operator	Description
-----------------------------------------------------------
$exists	Checks if field exists
$type	Matches a BSON type

🟣 Evaluation
-----------------------------------------------------------
Operator	Description
-----------------------------------------------------------
$regex	Regular expression matching
$expr	Allows using aggregation expressions in query
$mod	Modulo
$text	Text search
$where	JavaScript expression

🔄 Update Operators
-----------------------------------------------------------
Operator	Description
-----------------------------------------------------------
$set	    Sets value of a field
$unset	    Removes a field
$inc	    Increments field value
$mul	    Multiplies field value
$rename	    Renames a field
$push	    Adds value to an array
$pull	    Removes value from array
$addToSet	Adds value if not already present
------------------------------------------------------------
🧮 Aggregation Keywords
-----------------------------------------------------------
Keyword	Description
-----------------------------------------------------------
$match	    Filters documents (like WHERE)
$group	    Groups documents by field
$sort	    Sorts documents
$project	Reshapes documents
$limit	    Limits number of documents
$skip	    Skips documents
$lookup	    Joins collections (like SQL JOIN)
$unwind	    Deconstructs arrays

------------------------------------------------------------
$match, $group, $project, $sort, $limit, $skip, $unwind, $lookup, $addFields, $count, $facet
--------------------------------------------------------------
db.collection.distinct("field")
db.collection.renameCollection("newName")
db.collection.stats()
db.collection.validate()
db.collection.find().explain("executionStats")
--------------------------------------------------------------
Query Operators
--------------------------------------------------------------
$regex Example # Finds names starting with 'A', case-insensitive
db.users.find({ name: { $regex: "^A", $options: "i" } })
--------------------------------------------------------------
Aggregation - $group Example

db.orders.aggregate([
{ $group: { _id: "$item", total: { $sum: 1 } } }
])
-----------------------------------------------------------
Aggregation - $lookup (Join)
db.orders.aggregate([
{
$lookup: {
from: "customers",
localField: "customerId",
foreignField: "_id",
as: "customerDetails"
}
}
])

-----------------------------------------------------------
$regex
-----------------------------------------------------------
$regex Example
db.users.find({ name: { $regex: "^A", $options: "i" } })
-----------------------------------------------------------
# Finds names starting with 'A', case-insensitive
-----------------------------------------------------------
Aggregation - $group Example
-----------------------------------------------------------
db.orders.aggregate([
{ $group: { _id: "$item", total: { $sum: 1 } } }
])
-----------------------------------------------------------
Aggregation - $lookup (Join)
-----------------------------------------------------------
db.orders.aggregate([
{
$lookup: {
from: "customers",
localField: "customerId",
foreignField: "_id",
as: "customerDetails"
}
}
])
-----------------------------------------------------------
Aggregation - $match + $project
-----------------------------------------------------------
db.orders.aggregate([
{ $match: { status: "shipped" } },
{ $project: { item: 1, quantity: 1, _id: 0 } }
])
-----------------------------------------------------------
Aggregation - $unwind
# Splits array values into separate documents
db.inventory.aggregate([
{ $unwind: "$tags" }
])

-----------------------------------------------------------
Aggregation - $addFields
-----------------------------------------------------------
db.orders.aggregate([
{ $addFields: { totalWithTax: { $multiply: ["$price", 1.1] } } }
])
-----------------------------------------------------------
Aggregation - $sort + $limit
-----------------------------------------------------------
db.products.aggregate([
{ $sort: { price: -1 } },
{ $limit: 5 }
])
-----------------------------------------------------------
Aggregation - $facet (Multiple Pipelines)
-----------------------------------------------------------
db.movies.aggregate([
{
$facet: {
"Top Rated": [
{ $match: { rating: { $gte: 8 } } },
{ $sort: { rating: -1 } }
])
"Recent": [
{ $sort: { year: -1 } },
{ $limit: 5 }
]
}
}
])
-----------------------------------------------------------
Aggregation - Conditional ($cond)
-----------------------------------------------------------
db.scores.aggregate([
{
$project: {
name: 1,
grade: {
$cond: { if: { $gte: ["$score", 50] }, then: "Pass", else: "Fail" }
}
}
}
])
--------------------------------------------------------------
   
