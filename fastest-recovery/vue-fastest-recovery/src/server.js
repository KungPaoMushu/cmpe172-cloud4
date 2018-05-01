const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')
const Sequelize = require('sequelize')
const epilogue = require('epilogue')
const OktaJwtVerifier = require('@okta/jwt-verifier')
const db = require('./models');
const Post = db.Post;
const Employee = db.employees;
const Salary = db.salaries;
const Title = db.titles;
const Department = db.departments;

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

//Create the dynamic REST resource for our Employee model
let employeeResource = epilogue.resource({
  model: Employee,
  endpoints: ['/employees', '/employees/:id']
})

//Create the dynamic REST resource for our Salary model
let salaryResource = epilogue.resource({
  model: Salary,
  endpoints: ['/salaries', '/salaries/:id']
})

//Create the dynamic REST resource for our Title model
let titleResource = epilogue.resource({
  model: Title,
  endpoints: ['/titles', '/titles/:id']
})

//Create the dynamic REST resource for our Department model
let departmentResource = epilogue.resource({
  model: Department,
  endpoints: ['/departments', '/departments/:id']
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



const sequelize = new Sequelize('sakila', 'boss', 'boss12', {
  host: '127.0.0.1',
  dialect: 'mysql'
});