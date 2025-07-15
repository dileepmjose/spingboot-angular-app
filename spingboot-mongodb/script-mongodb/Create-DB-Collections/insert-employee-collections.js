// This script inserts employee data into the MongoDB database.

db = connect("mongodb://localhost:27017/demo");

db.employee.insertMany([
  {
    _id: ObjectId("64f1a7fabc33333333333333"),
    name: "Robert Brown",
    age: 40,
    department: "Finance",
    address: [
      { type: "home", street: "12 Wall St", city: "New York", zip: "10005" },
      { type: "work", street: "200 Finance Ave", city: "New York", zip: "10006" }
    ],
    skills: ["Accounting", "Excel", "SAP"],
    hobbies: ["Chess", "Golf"],
    experience: [
      { company: "FinanceCorp", role: "Analyst", years: 3 },
      { company: "MoneyMatters", role: "Senior Analyst", years: 5 }
    ]
  },
  {
    _id: ObjectId("64f1a7fabc44444444444444"),
    name: "Emily Clark",
    age: 32,
    department: "Product",
    address: [
      { type: "home", street: "88 Ocean Dr", city: "Miami", zip: "33101" },
      { type: "work", street: "100 Product Blvd", city: "Miami", zip: "33102" }
    ],
    skills: ["Product Management", "Figma", "User Research"],
    hobbies: ["Swimming", "Blogging"],
    experience: [
      { company: "TechFlow", role: "Product Owner", years: 4 }
    ]
  },
  {
    _id: ObjectId("64f1a7fabc55555555555555"),
    name: "Carlos Martinez",
    age: 40,
    department: "Sales",
    address: [
      { type: "home", street: "22 Sunset Blvd", city: "Los Angeles", zip: "90028" },
      { type: "work", street: "400 Sales Ave", city: "Los Angeles", zip: "90029" }
    ],
    skills: ["CRM", "Negotiation", "Lead Generation"],
    hobbies: ["Running", "Cooking"],
    experience: [
      { company: "SellRight", role: "Sales Rep", years: 2 },
      { company: "GlobalSales", role: "Account Manager", years: 3 }
    ]
  },
  {
    _id: ObjectId("64f1a7fabc66666666666666"),
    name: "Linda Green",
    age: 45,
    department: "Legal",
    address: [
      { type: "home", street: "77 Legal St", city: "Boston", zip: "02108" },
      { type: "work", street: "300 Justice Ave", city: "Boston", zip: "02109" }
    ],
    skills: ["Corporate Law", "Compliance", "Contracts"],
    hobbies: ["Piano", "Volunteering"],
    experience: [
      { company: "LegalEdge", role: "Legal Advisor", years: 8 }
    ]
  },
  {
    _id: ObjectId("64f1a7fabc77777777777777"),
    name: "Amit Sharma",
    age: 38,
    department: "IT Support",
    address: [
      { type: "home", street: "101 Tech Lane", city: "Seattle", zip: "98101" },
      { type: "work", street: "800 HelpDesk Rd", city: "Seattle", zip: "98102" }
    ],
    skills: ["Networking", "Troubleshooting", "Linux"],
    hobbies: ["Gaming", "Woodworking"],
    experience: [
      { company: "SysServe", role: "Support Engineer", years: 6 },
      { company: "FixIT", role: "Lead Support", years: 2 }
    ]
  },
  {
    _id: ObjectId("64f1a7fabc88888888888888"),
    name: "Monica Reyes",
    age: 40,
    department: "Finance",
    address: [
      { type: "home", street: "34 Wall St", city: "New York", zip: "10005" },
      { type: "work", street: "210 Finance Ave", city: "New York", zip: "10006" }
    ],
    skills: ["Financial Analysis", "SAP", "Excel"],
    hobbies: ["Tennis", "Reading"],
    experience: [
      { company: "MoneyMatters", role: "Financial Analyst", years: 6 },
      { company: "FinanceCorp", role: "Senior Analyst", years: 4 }
    ]
  },
  {
    _id: ObjectId("64f1a7fabc99999999999999"),
    name: "Jason Miller",
    age: 40,
    department: "Product",
    address: [
      { type: "home", street: "101 Innovation Dr", city: "San Francisco", zip: "94105" },
      { type: "work", street: "300 Product Blvd", city: "San Francisco", zip: "94107" }
    ],
    skills: ["UX Design", "Jira", "Prototyping"],
    hobbies: ["Gaming", "Sketching"],
    experience: [
      { company: "TechFlow", role: "Junior Product Manager", years: 2 }
    ]
  }
]);


// This script inserts vehicle data into the MongoDB database.

db.vehicle.insertMany([
  {
    _id: ObjectId("64f1b8cdef11111111111111"),
    type: "Car",
    brand: "Toyota",
    model: "Camry",
    registration: "NY1234",
    employeeId: ObjectId("64f1a7fabc11111111111111"), // Robert Brown
    insurance: {
      provider: "Geico",
      policyNumber: "G123456789",
      validTill: ISODate("2026-01-31")
    },
    drivers: [
      { name: "John Doe", license: "NY-JD123", relation: "primary" },
      { name: "Jane Smith", license: "NY-JS789", relation: "secondary" }
    ],
    serviceRecords: [
      {
        date: ISODate("2024-12-01"),
        serviceCenter: "AutoFix NYC",
        workDone: ["Oil change", "Brake pads"]
      },
      {
        date: ISODate("2023-07-01"),
        serviceCenter: "QuickService",
        workDone: ["Battery replacement"]
      }
    ]
  },
  {
    _id: ObjectId("64f1b8cdef22222222222222"),
    type: "Bike",
    brand: "Yamaha",
    model: "FZ",
    registration: "CH5678",
    employeeId: ObjectId("64f1a7fabc44444444444444"), // Emily Clark
    insurance: {
      provider: "Progressive",
      policyNumber: "P987654321",
      validTill: ISODate("2025-10-15")
    },
    drivers: [
      { name: "Jane Smith", license: "CH-JS345", relation: "primary" }
    ],
    serviceRecords: []
  },{
    _id: ObjectId("64f1b8cdef99999999999999"),
    type: "Car",
    brand: "BMW",
    model: "X3",
    registration: "BOS3141",
    employeeId: ObjectId("64f1a7fabc55555555555555"), // Carlos Martinez
    insurance: {
      provider: "Liberty Mutual",
      policyNumber: "LMX31415",
      validTill: ISODate("2025-09-15T00:00:00Z")
    },
    drivers: [
      { name: "Linda Green", license: "MA-LG321", relation: "primary" }
    ],
    serviceRecords: [
      {
        date: ISODate("2024-03-10T00:00:00Z"),
        serviceCenter: "Euro Garage",
        workDone: ["Tire change", "Alignment"]
      }
    ]
  },
  {
    _id: ObjectId("64f1b8cdef10101010101010"),
    type: "Van",
    brand: "Mercedes-Benz",
    model: "Sprinter",
    registration: "NYSPR789",
    employeeId: ObjectId("64f1a7fabc66666666666666"), // Linda Green
    insurance: {
      provider: "Allianz",
      policyNumber: "ALZ202450",
      validTill: ISODate("2026-01-01T00:00:00Z")
    },
    drivers: [
      { name: "Carlos Martinez", license: "LA-CM100", relation: "primary" }
    ],
    serviceRecords: [
      {
        date: ISODate("2023-12-20T00:00:00Z"),
        serviceCenter: "VanFix Center",
        workDone: ["Brake fluid", "Door lock fix"]
      }
    ]
  },
  {
    _id: ObjectId("64f1b8cdef11111111111112"),
    type: "Scooter",
    brand: "Honda",
    model: "Activa",
    registration: "WA9988",
    employeeId: ObjectId("64f1a7fabc77777777777777"), // Amit Sharma
    insurance: {
      provider: "GEICO",
      policyNumber: "GEO442211",
      validTill: ISODate("2025-06-30T00:00:00Z")
    },
    drivers: [
      { name: "Amit Sharma", license: "WA-AS789", relation: "primary" }
    ],
    serviceRecords: [
      {
        date: ISODate("2024-05-05T00:00:00Z"),
        serviceCenter: "TwoWheel Seattle",
        workDone: ["Oil change"]
      }
    ]
  },
  {
    _id: ObjectId("64f1b8cdef12121212121212"),
    type: "Car",
    brand: "Ford",
    model: "Focus",
    registration: "CHI0101",
    employeeId: ObjectId("64f1a7fabc44444444444444"), // Emily Clark
    insurance: {
      provider: "State Farm",
      policyNumber: "SF019283",
      validTill: ISODate("2025-12-31T00:00:00Z")
    },
    drivers: [
      { name: "Emily Clark", license: "MI-EC321", relation: "primary" },
      { name: "Jane Smith", license: "CH-JS345", relation: "authorized" }
    ],
    serviceRecords: [
      {
        date: ISODate("2024-02-15T00:00:00Z"),
        serviceCenter: "CityAuto Chicago",
        workDone: ["Engine oil", "Battery check"]
      }
    ]
  }
]);

