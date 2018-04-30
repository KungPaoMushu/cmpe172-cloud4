const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')
const Sequelize = require('sequelize')
const epilogue = require('epilogue')
const OktaJwtVerifier = require('@okta/jwt-verifier')
const db = require('./models');
const Post = db.Post;
const Task = db.Task;
const Actor = db.actor;
const Film = db.film;
const Rental = db.rental;

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



//Create the dynamic REST resource for our Post model
let userResource = epilogue.resource({
  model: Post,
  endpoints: ['/posts', '/posts/:id']
})

// Create the dynamic REST resource for our Task model
let taskResource = epilogue.resource({
  model: Task,
  endpoints: ['/tasks', '/tasks/:id']
})


// Create the dynamic REST resource for our Actor model
let actorResource = epilogue.resource({
  model: Actor,
  endpoints: ['/actors', '/actors/:id']
})

// Create the dynamic REST resource for our Film model
let filmResource = epilogue.resource({
  model: Film,
  endpoints: ['/films', '/films/:id']
})


// Create the dynamic REST resource for our Rental model
let rentalResource = epilogue.resource({
  model: Rental,
  endpoints: ['/rentals', '/actors/:id']
})

app.get('/api/getActors', (req,res) =>{
  console.log("i made it ")
  Actor.findAll(
  {
    where: {
      id: 1
    }
  }
  )
  .then(actors => res.send(actors))
  .catch(err =>  res.send(err));

});

function deleteActor(id){
   
    Actor.destroy({
        where: {actor_id: 200}
    })
    .then(function(){
            res.send({ "result": "deleted actor"});
    })
}

// Resets the database and launches the express app on :8081
db.sequelize.sync()
.then(() => {
  //deleteActor();
  app.listen(8081, () => {
    console.log('listening to port localhost:8081')
  })
})
.catch(err => {
  console.error('Unable to connect to the database:', err);
});

