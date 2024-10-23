db = db.getSiblingDB('factory_db');

db.createCollection('categories');
db.createCollection('products');
db.createCollection('materials');
db.createCollection('product_materials');
db.createCollection('clients');
db.createCollection('product_orders');