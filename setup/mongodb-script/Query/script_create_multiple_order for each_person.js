 print("------Create mutiple Order in Order Collection For Each Person Person Collection randon number orders maxium 10 --------------");
 db = connect("mongodb://localhost:27017/demo");
 print("-Connected-");
 db.persons.find().forEach(function(persons) {
     print("-Processing-");
    var personId = persons._id;
    var personName = persons.name;

    // Create multiple orders for each person
    
    var orderCount = Math.floor(Math.random() * 6) + 1; // Random number of orders between 1 and 10
    for (var i = 0; i < orderCount; i++) {
        var order = {
            personId: personId,
            orderDate: new Date(),
            items: [
                {
                    itemName: "Item " + (i + 1),
                    quantity: Math.floor(Math.random() * 5) + 1, // Random quantity between 1 and 5
                    price: (Math.random() * 100).toFixed(2) // Random price between 0 and 100
                }
            ]
        };

        db.order.insert(order);
    }
    print("Created " + orderCount + " orders for person: " + personName);
 });

 // group Order for Each Person using Aggregation

 db.order.aggregate([
     {
         $group: {
             _id: "$personId",
             totalOrders: { $sum: 1 },
             totalItems: { $sum: { $size: "$items" } },
             totalPrice: { $sum: { $sum: "$items.price" } }
         }
     },
     {
         $lookup: {
             from: "persons",
             localField: "_id",
             foreignField: "_id",
             as: "personDetails"
         }
     },
     {
         $unwind: "$personDetails"
     },
     {
         $project: {
             _id: 0,
             personId: "$_id",
             personName: "$personDetails.name",
             totalOrders: 1,
             totalItems: 1,
             totalPrice: 1
         }
     }
 ]).forEach(function(result) {
     print("Person: " + result.personName + ", Total Orders: " + result.totalOrders + ", Total Items: " + result.totalItems + ", Total Price: $" + result.totalPrice.toFixed(2));
 });
 print("-------------------------------------------------End of Order Creation and Aggregation-------------------------------------------------");

// Update Order Items to ensure price is a float
 db.order.find().forEach(function(order) {
    let updatedItems = [];

    let needsUpdate = false;

    order.items.forEach(function(item) {
        if (typeof item.price === "string") {
            // Convert to float
            item.price = parseFloat(item.price);
            needsUpdate = true;
        }
        updatedItems.push(item);
    });

    if (needsUpdate) {
        db.order.updateOne(
            { _id: order._id },
            { $set: { items: updatedItems } }
        );
        print("Updated order: " + order._id);
    }
});