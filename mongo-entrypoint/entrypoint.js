var db = connect("mongodb://root:example@localhost:27017/root");

db = db.getSiblingDB('iot_data'); // we can not use "use" statement here to switch db

db.createUser(
    {
        user: "user",
        pwd: "pass",
        roles: [ { role: "readWrite", db: "iot_data"} ],
        passwordDigestor: "server",
    }
)