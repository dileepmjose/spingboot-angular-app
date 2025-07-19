
// Update Order Items to ensure price is a float
db = connect("mongodb://localhost:27017/demo");

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