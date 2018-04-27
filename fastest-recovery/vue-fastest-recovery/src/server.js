const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')
const Sequelize = require('sequelize')
const epilogue = require('epilogue')
const OktaJwtVerifier = require('@okta/jwt-verifier')
const db = require('./models');
const Actor = db.Actor;

const oktaJwtVerifier = new OktaJwtVerifier({
  clientId: '0oaethn5zrHz7JNpw0h7',
  issuer: 'https://dev-487888.oktapreview.com/oauth2/default'
})

let app = express()
app.use(cors())
app.use(bodyParser.json())

// verify JWT token middleware
app.use((req, res, next) => {
  // require every request to have an authorization header
  if (!req.headers.authorization) {
    return next(new Error('Authorization header is required'))
  }
  let parts = req.headers.authorization.trim().split(' ')
  let accessToken = parts.pop()
  oktaJwtVerifier.verifyAccessToken(accessToken)
    .then(jwt => {
      req.user = {
        uid: jwt.claims.uid,
        email: jwt.claims.sub
      }
      next()
    })
    .catch(next) // jwt did not verify!
})



// Initialize epilogue
epilogue.initialize({
  app: app,
  sequelize: db
})

// Create the dynamic REST resource for our Actor model
let actorResource = epilogue.resource({
  model: Actor,
  endpoints: ['/actors', '/actors/:id']
})



// Resets the database and launches the express app on :8081
db.sequelize.sync()
.then(() => {
  app.listen(8081, () => {
    console.log('listening to port localhost:8081')
  })
})
.catch(err => {
  console.error('Unable to connect to the database:', err);
});

