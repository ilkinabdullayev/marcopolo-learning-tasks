const Joi = require("joi");
const mongoose = require("mongoose");

const productSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
    trim: true
  },
  description: String,
  image_url: String,
  created_by: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Users',
    required: true
  },
  createdAt: { type: Date, default: Date.now }
});

const Product = mongoose.model("Products", productSchema);

function validateProduct(product) {
  const schema = {
    title: Joi.string()
      .min(5)
      .max(50)
      .required(),
    description: Joi.string()
      .min(5)
      .max(200),
    image_url: Joi.string()
  };

  return Joi.validate(product, schema);
}

module.exports = {
  Product,
  validateProduct: validateProduct
};