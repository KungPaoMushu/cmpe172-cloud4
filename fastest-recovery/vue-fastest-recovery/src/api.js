import Vue from 'vue'
import axios from 'axios'

const client = axios.create({
  baseURL: 'http://localhost:8081/',
  json: true
})

export default {
  async execute (method, resource, data) {
    // inject the accessToken for each request
    let accessToken = await Vue.prototype.$auth.getAccessToken()
    return client({
      method,
      url: resource,
      data,
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    }).then(req => {
      return req.data
    }).catch(err => {
      console.error('error: ', err);
    });


  },
  getTasks () {
    return this.execute('get', '/tasks')
  },
  getTask (id) {
    return this.execute('get', `/tasks/${id}`)
  },
  createTask (data) {
    return this.execute('task', '/tasks', data)
  },
  updateTask (id, data) {
    return this.execute('put', `/tasks/${id}`, data)
  },
  deleteTask (id) {
    return this.execute('delete', `/tasks/${id}`)
  },
  getActors () {
    return this.execute('get', '/actors')
  },
  getActor (id) {
    return this.execute('get', `/actors/${id}`)
  },
  createActor (data) {
    return this.execute('actor', '/actors', data)
  },
  updateActor (id, data) {
    return this.execute('put', `/actors/${id}`, data)
  },
  deleteActor (id) {
    return this.execute('delete', `/actors/${id}`)
  },
  getPosts () {
    return this.execute('get', '/posts')
  },
  getPost (id) {
    return this.execute('get', `/posts/${id}`)
  },
  createPost (data) {
    return this.execute('post', '/posts', data)
  },
  updatePost (id, data) {
    return this.execute('put', `/posts/${id}`, data)
  },
  deletePost (id) {
    return this.execute('delete', `/posts/${id}`)
  },
  getFilm(id) {
    return this.execute('get', `/films/${id}`)
  },
  getFilms () {
    return this.execute('get', '/films')
  },
  getRental (id) {
    return this.execute('get', `/rentals/${id}`)
  },
  getRentals () {
    return this.execute('get', '/rentals')
  },
  getUser (id) {
    return this.execute('get', `/users/${id}`)
  },
  getUsers () {
    return this.execute('get', '/users')
  },
  createUser (data) {
    return this.execute('post', '/users', data)
  },
  updateUser (id, data) {
    return this.execute('put', `/users/${id}`, data)
  },
  deleterUser (id) {
    return this.execute('delete', `/users/${id}`)
  }

}