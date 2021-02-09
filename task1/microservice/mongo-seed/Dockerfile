FROM mongo

COPY products.json /products.json
COPY users.json /users.json

CMD mongoimport --host product-mongo-db --db product --collection products --type json --file /products.json --jsonArray