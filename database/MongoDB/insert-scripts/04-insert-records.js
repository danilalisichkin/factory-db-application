db = db.getSiblingDB('factory_db');

db.categories.insertMany([
    { _id: NumberInt(1), name: 'Furniture', parent_id: null },            // 1
    { _id: NumberInt(2), name: 'Bedroom Furniture', parent_id: 1 },       // 2
    { _id: NumberInt(3), name: 'Beds', parent_id: 2 },                    // 3
    { _id: NumberInt(4), name: 'Nightstands', parent_id: 2 },             // 4
    { _id: NumberInt(5), name: 'Living Room Furniture', parent_id: 1 },   // 5
    { _id: NumberInt(6), name: 'Coffee Tables', parent_id: 5 },           // 6
    { _id: NumberInt(7), name: 'Cabinets', parent_id: 5 },                // 7
    { _id: NumberInt(8), name: 'Kitchen Furniture', parent_id: 1 },       // 8
    { _id: NumberInt(9), name: 'Bar Stools', parent_id: 8 },              // 9
    { _id: NumberInt(10), name: 'Dining Tables', parent_id: 8 }           // 10
]);

db.products.insertMany([
    { _id: NumberInt(1), name: 'Royal Bed', category_id: 3, price: 3200.00 },                 // 1
    { _id: NumberInt(2), name: 'Slavyanka Bed', category_id: 3, price: 2200.00 },             // 2
    { _id: NumberInt(3), name: 'Feast Table', category_id: 10, price: 1400.00 },              // 3
    { _id: NumberInt(4), name: 'Two Nightstands Plus Table', category_id: 4, price: 400.00 }, // 4
    { _id: NumberInt(5), name: 'Glass Cube Cabinet', category_id: 7, price: 2000.00 }         // 5
]);

db.materials.insertMany([
    { name: 'Wood - Birch', supplier_name: 'LLC "Lesogryzy"' },               // 1
    { name: 'Wood - Oak', supplier_name: 'LLC "Lesogryzy"' },                 // 2
    { name: 'Acrylic Glass', supplier_name: 'OJSC "Glass Factory "Neman""' }, // 3
    { name: 'Fiberboard', supplier_name: 'OJSC "MaterialPlus"' },             // 4
    { name: 'Particle Board', supplier_name: 'OJSC "MaterialPlus"' },         // 5
    { name: 'Gold', supplier_name: 'OJSC "BelJewelryTrade"' },                // 6
    { name: 'Natural Leather', supplier_name: 'LLC "Wild Hunter"' },          // 7
    { name: 'Textile', supplier_name: 'LLC "Minsk Textile Factory"' }         // 8
]);

db.product_materials.insertMany([
    { product_sku: 1, material_sku: 2 },
    { product_sku: 1, material_sku: 4 },
    { product_sku: 1, material_sku: 6 },
    { product_sku: 1, material_sku: 8 },
    { product_sku: 1, material_sku: 7 },
    { product_sku: 2, material_sku: 1 },
    { product_sku: 2, material_sku: 4 },
    { product_sku: 2, material_sku: 8 },
    { product_sku: 3, material_sku: 4 },
    { product_sku: 3, material_sku: 5 },
    { product_sku: 4, material_sku: 4 },
    { product_sku: 4, material_sku: 5 },
    { product_sku: 5, material_sku: 3 },
    { product_sku: 5, material_sku: 5 }
]);

db.clients.insertMany([
    { phone_number: '375291111111', organization_name: 'OJSC "AMI Furniture"', email: 'ami@gmail.com', legal_address: 'Dzerzhinsk, Lenin St. 12' },
    { phone_number: '375331223445', organization_name: 'LLC "IT Solutions"', email: 'itsolutions@gmail.com', legal_address: 'Minsk, International St. 14, office 4' },
    { phone_number: '375441114444', organization_name: 'State Educational Institution "School 15 of Minsk"', email: 'shool15minsk@mail.ru', legal_address: 'Minsk, Kalinin St. 52' }
]);

db.product_orders.insertMany([
    { client_phone_number: '375291111111', product_sku: 1, quantity: 2 },
    { client_phone_number: '375291111111', product_sku: 2, quantity: 10 },
    { client_phone_number: '375291111111', product_sku: 3, quantity: 10 },
    { client_phone_number: '375291111111', product_sku: 4, quantity: 4 },
    { client_phone_number: '375331223445', product_sku: 5, quantity: 10 },
    { client_phone_number: '375331223445', product_sku: 3, quantity: 3 },
    { client_phone_number: '375441114444', product_sku: 5, quantity: 10 }
]);
