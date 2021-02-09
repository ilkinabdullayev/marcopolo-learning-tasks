const mongoose = require("mongoose");
const config = require("config");
const winston = require("winston");

const getUrlOfMongo = () => {
  const { host, port, database } = config.mongo;
  winston.info(config);
  return `mongodb://${host}:${port}/${database}`;
}


module.exports = function connectToDatabase() {
  const db = getUrlOfMongo();
  mongoose
    .connect(
      db,
      { 
          useNewUrlParser: true, 
          useUnifiedTopology:true,
          authSource: {
            auth: {
              authSource: config.get("mongo.authDB")
            }
          }
      }
    )
    .then(() => console.info(`Connected to ${db} successfully...`));
};