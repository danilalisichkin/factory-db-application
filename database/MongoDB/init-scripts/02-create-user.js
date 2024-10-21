use factory_db;

db.createUser({
    user: "factory_user",
    pwd: "admin",
    roles: [
        { role: "readWrite", db: "factory_db" },
        { role: "dbAdmin", db: "factory_db" },
        { role: "userAdmin", db: "factory_db" }
    ]
});