const Joi = require('joi');
const mongoose = require('mongoose');
const express = require('express');
const { Product, validateProduct } = require('../models/product');
const router = express.Router();
const auth = require("../middleware/auth");
const admin = require("../middleware/admin");

router.get('/', auth, async (req, res) => {
    const createdByRule = req.user.role !== 'ADMIN' ? ' -created_by' : '';
    const products = await Product.find()
    .select("-__v" + createdByRule);
    res.send(products);
});

router.get('/:id', auth, async (req, res) => {
    if (!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).send('Invalid Product id');
    }

    const createdByRule = req.user.role !== 'ADMIN' ? ' -created_by' : '';
    const product = await Product.findById(req.params.id)
                                 .select("-__v" + createdByRule);

    if (!product) return res.status(404).send('The Product with the given ID was not found.');

    res.send(product);
});

router.post('/', [auth, admin], async (req, res) => {
    const { error } = validateProduct(req.body);
    if (error) return res.status(400).send(error.details[0].message);

    let product = new Product({
        title: req.body.title,
        description: req.body.description,
        image_url: req.body.image_url,
        created_by: req.user.username
    });

    product = await product.save();
    res.send(product);
});

router.put('/:id', [auth, admin], async (req, res) => {
    if (!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).send('Invalid Product id');
    }


    const { error } = validateProduct(req.body);
    if (error) return res.status(400).send(error.details[0].message);

    const createdByRule = req.user.role !== 'ADMIN' ? ' -created_by' : '';
    const product = await Product.findByIdAndUpdate(req.params.id,
        {
            title: req.body.title,
            description: req.body.description,
            image_url: req.body.image_url,
        }, { new: true })
        .select("-__v" + createdByRule);

    if (!product) return res.status(404).send('The product with the given ID was not found.');

    res.send(product);
});

router.delete('/:id', [auth, admin], async (req, res) => {
    if (!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).send('Invalid Product id');
    }

    const product = await Product.findByIdAndRemove(req.params.id);

    if (!product) return res.status(404).send('The Product with the given ID was not found.');

    res.send(product);
});


module.exports = router;