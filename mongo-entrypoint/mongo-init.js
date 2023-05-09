
db = db.getSiblingDB('iot_data'); 

db.house.insertOne({"id": 1332111111,
    "unixTime": 0,
    "value": 0,
    "workLoad": 0,
    "plugId": 0,
    "houseHoldId": 1,
    "houseId": 0});

db.createUser(
    {
        user: "iotUser",
        pwd: "Ahihi123@",
        roles: [ { role: "readWrite", db: "iot_data"} ],
    }
)

