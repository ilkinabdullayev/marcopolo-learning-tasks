const config = require("config");
const fetch = require('node-fetch');


async function auth(req, res, next) {
  const token = req.header("Authorization");
  
  console.log(`User is authorizing by ${token} token`);

  try {
    const response = await fetch(
      `${config.authentication.url}/query`,
      {
        headers: {
          'Authorization': token
        }
      }
    );
  } catch(err) {
    res.status(503).send(`Authentication Service ${config.authentication.url} is unavailable`);
  }

  const data = await response.json();

  console.log(`Auth result:`, data)
  if (data.code && data.code === 401) {
     res.status(data.code).send(data.message);
  }


  req.user = data;
  next();
}

module.exports = auth;