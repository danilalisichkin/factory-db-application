db = db.getSiblingDB('factory_db');

db.categories.insertMany([
    { id: 1, name: 'Furniture', parent_id: null },
    { id: 2, name: 'Bedroom Furniture', parent_id: 1 },
    { id: 3, name: 'Beds', parent_id: 2 },
    { id: 4, name: 'Nightstands', parent_id: 2 },
    { id: 5, name: 'Living Room Furniture', parent_id: 1 },
    { id: 6, name: 'Coffee Tables', parent_id: 5 },
    { id: 7, name: 'Cabinets', parent_id: 5 },
    { id: 8, name: 'Kitchen Furniture', parent_id: 1 },
    { id: 9, name: 'Bar Stools', parent_id: 8 },
    { id: 10, name: 'Dining Tables', parent_id: 8 }
]);

db.products.insertMany([
    { id: 1, name: 'Royal Bed', category_id: 3, price: 3200.00 },
    { id: 2, name: 'Slavyanka Bed', category_id: 3, price: 2200.00 },
    { id: 3, name: 'Feast Table', category_id: 10, price: 1400.00 },
    { id: 4, name: 'Two Nightstands Plus Table', category_id: 4, price: 400.00 },
    { id: 5, name: 'Glass Cube Cabinet', category_id: 7, price: 2000.00 }
]);

db.materials.insertMany([
    { id: 1, name: 'Wood - Birch', supplier_name: 'LLC "Lesogryzy"' },               // 1
    { id: 2, name: 'Wood - Oak', supplier_name: 'LLC "Lesogryzy"' },                 // 2
    { id: 3, name: 'Acrylic Glass', supplier_name: 'OJSC "Glass Factory "Neman""' }, // 3
    { id: 4, name: 'Fiberboard', supplier_name: 'OJSC "MaterialPlus"' },             // 4
    { id: 5, name: 'Particle Board', supplier_name: 'OJSC "MaterialPlus"' },         // 5
    { id: 6, name: 'Gold', supplier_name: 'OJSC "BelJewelryTrade"' },                // 6
    { id: 7, name: 'Natural Leather', supplier_name: 'LLC "Wild Hunter"' },          // 7
    { id: 8, name: 'Textile', supplier_name: 'LLC "Minsk Textile Factory"' }         // 8
]);

db.product_materials.insertMany([
    { id: '1-2', product_sku: 1, material_sku: 2 },
    { id: '1-4', product_sku: 1, material_sku: 4 },
    { id: '1-6', product_sku: 1, material_sku: 6 },
    { id: '1-8', product_sku: 1, material_sku: 8 },
    { id: '1-7', product_sku: 1, material_sku: 7 },
    { id: '2-1', product_sku: 2, material_sku: 1 },
    { id: '2-4', product_sku: 2, material_sku: 4 },
    { id: '2-8', product_sku: 2, material_sku: 8 },
    { id: '3-4', product_sku: 3, material_sku: 4 },
    { id: '3-5', product_sku: 3, material_sku: 5 },
    { id: '4-4', product_sku: 4, material_sku: 4 },
    { id: '4-5', product_sku: 4, material_sku: 5 },
    { id: '5-3', product_sku: 5, material_sku: 3 },
    { id: '5-5', product_sku: 5, material_sku: 5 }
]);

db.clients.insertMany([
    { phone_number: '375291111111', organization_name: 'OJSC "AMI Furniture"', email: 'ami@gmail.com', legal_address: 'Dzerzhinsk, Lenin St. 12' },
    { phone_number: '375331223445', organization_name: 'LLC "IT Solutions"', email: 'itsolutions@gmail.com', legal_address: 'Minsk, International St. 14, office 4' },
    { phone_number: '375441114444', organization_name: 'State Educational Institution "School 15 of Minsk"', email: 'shool15minsk@mail.ru', legal_address: 'Minsk, Kalinin St. 52' }
]);

db.product_orders.insertMany([
    { id: '375291111111-1', client_phone_number: '375291111111', product_sku: 1, quantity: 2 },
    { id: '375291111111-2', client_phone_number: '375291111111', product_sku: 2, quantity: 10 },
    { id: '375291111111-3', client_phone_number: '375291111111', product_sku: 3, quantity: 10 },
    { id: '375291111111-4', client_phone_number: '375291111111', product_sku: 4, quantity: 4 },
    { id: '375331223445-5', client_phone_number: '375331223445', product_sku: 5, quantity: 10 },
    { id: '375331223445-3', client_phone_number: '375331223445', product_sku: 3, quantity: 3 },
    { id: '375441114444-5', client_phone_number: '375441114444', product_sku: 5, quantity: 10 }
]);
