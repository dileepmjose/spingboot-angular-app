
// This script updates the companyArray field in each person document to a random company
db = connect("mongodb://localhost:27017/demo");


// Step 1: Load all companies into an array
const companies = db.company.find().toArray();

// Step 2: Helper function
function getRandomCompanies(sourceList, count) {
  const shuffled = [...sourceList].sort(() => 0.5 - Math.random());
  return shuffled.slice(0, count);
}

// Step 3: Update each person with random companies
db.persons.find().forEach(function(person) {
  const numCompanies = Math.floor(Math.random() * 3) + 2; // Random 2 to 4
  const selectedCompanies = getRandomCompanies(companies, numCompanies);

    print("Updating person:", person.name, "with", numCompanies, "companies");
    

    //remove id from each company object
    selectedCompanies.forEach(function(company) {
        delete company._id;
    });

    print(selectedCompanies);

     db.persons.updateOne(
    { _id: person._id },
    { $set: { company: selectedCompanies } }
    );

    print("Updated person: " + person._id);
  

});