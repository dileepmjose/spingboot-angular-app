 print("------Create mutiple Order in Order Collection For Each Person Person Collection randon number orders maxium 10 --------------");
 db = connect("mongodb://localhost:27017/demo");
 // group Order for Each Person using Aggregation
db.order.aggregate([
    { $unwind: "$items" },
    {
        $group: {
            _id: "$personId",
            totalOrders: { $addToSet: "$_id" },  // Collect unique order IDs
            totalItems: { $sum: 1 },             // Each unwinded item = 1 item
            totalPrice: { $sum: "$items.price" } // Sum all item prices
        }
    },
    {
        $addFields: {
            totalOrders: { $size: "$totalOrders" }
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
    { $unwind: "$personDetails" },
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
    print("Person: " + result.personName +
          ", Total Orders: " + result.totalOrders +
          ", Total Items: " + result.totalItems +
          ", Total Price: $" + result.totalPrice.toFixed(2));
});

// Dsipaly Each Person Order
db.order.aggregate([
    { $unwind: "$items" },
    {
        $lookup: {
            from: "persons",
            localField: "personId",
            foreignField: "_id",
            as: "personDetails"
        }
    },
    { $unwind: "$personDetails" },

    // 🔽 Sort by person name (ascending)
    { $sort: { "personDetails.name": 1 } },

    {
        $project: {
            _id: 0,
            personId: "$personId",
            personName: "$personDetails.name",
            orderId: "$_id",
            itemName: "$items.name",
            itemPrice: "$items.price",
            orderDate: "$orderDate"
        }
    }
]).forEach(function(result) {
    print("Person: " + result.personName +
          ", Order ID: " + result.orderId +
          ", Item: " + result.itemName +
          ", Price: $" + parseFloat(result.itemPrice).toFixed(2) +
          ", Date: " + result.orderDate);
});
