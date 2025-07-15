 print("----------------------------------------------------------Upadte movie rating to 5 all French movies-------------------------------------------");
db = connect("mongodb://localhost:27017/demo");

// upadte movie rating to 5 all French movies
db.country.find().forEach(function(country) {
    var countryName = country.name.common;

    if (countryName === "France") {
        db.movie.updateMany(
            { countries: countryName },
            { $set: { rated: "APPROVED" } }
        );
    }
});

 print("---------------------------------------------All countries and their associated movies, printing the country name and the titles of--------------------------------------------------------------------------");

// This script retrieves all countries and their associated movies, printing the country name and the titles of
db.country.find().forEach(function(country) {
  
    var countryName = country.name.common;
    var movies = db.movie.find({ countries: countryName }).toArray();
    
    if (movies.length > 0) {
        
        print("Country: " + countryName);
        print("--------------------");
        movies.forEach(function(movie) {
        print(" -----------> Movie: " + movie.title);
        });
    } else {
       print("Country:" + countryName +":"+ " has no movies.");
    }
})

 print("------------------------------------------------etrieves employee information, including name, age, department, skills---------------------------------------------------------------------------------------");

// It connects to the database and retrieves employee information, including name, age, department, skills
db.employee.find().forEach(emp=> {
  print("Name:", emp.name);
  print("Age:", emp.age);
  print("Department:", emp.department);
  if (emp.skills && emp.skills.length > 0) {
    print("Skills:", emp.skills.join(", "));
  } else {
    print("No skills available.");
  }
  if (emp.experience && emp.experience.length > 0) {
    emp.experience.forEach(exp => {
      print(`Experience at ${exp.company}: ${exp.role} (${exp.years} years)`);
    });
  } else {
    print("No experience available.");
  }
  if(emp.address && emp.address.length >0) {
    print("Address:", emp.address.street, emp.address.city, emp.address.type, emp.address.zip);
  } else {
    print("No address available.");
  }
});





