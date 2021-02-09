const error = require("../middleware/error");
const products = require("../routes/products");
const express = require("express");

module.exports = function(app) {
  app.use(express.json());
  app.use(express.urlencoded({ extended: true }));
  app.use("/products", products);
  app.use(error);
};