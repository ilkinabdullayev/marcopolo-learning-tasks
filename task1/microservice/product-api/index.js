const express = require("express");
require("./startup/logging")();
require("./startup/database")();

const app = express();
require("./startup/routes")(app);

const winston = require("winston");

const port = process.env.PORT || 5000;
const server = app.listen(port, () =>
  winston.info("Listening on port", port, "...")
);

module.exports = server;