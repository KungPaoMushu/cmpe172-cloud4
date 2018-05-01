/* eslint-disable */
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
  getEmployees () {
    return this.execute('get', '/employees')
  },
  getEmployee (id) {
    return this.execute('get', `/employees/${id}`)
  },
  createEmployee (data) {
    return this.execute('employee', '/employees', data)
  },
  updateEmployee (id, data) {
    return this.execute('put', `/employees/${id}`, data)
  },
  deleteEmployee (id) {
    return this.execute('delete', `/employees/${id}`)
  },
  getUser (id) {
    return this.execute('get', `/users/${id}`)
  },
  getUsers () {
    return this.execute('get', '/users')
  },
  createUser (data) {
    return this.execute('user', '/users', data)
  },
  updateUser (id, data) {
    return this.execute('put', `/users/${id}`, data)
  },
  deleterUser (id) {
    return this.execute('delete', `/users/${id}`)
  },
  getSalaries () {
    return this.execute('get', '/salaries')
  },
  getEmployeeSalaries (id) {
    return this.execute('get', `/salaries?emp_no=${id}`)
  },
  getTitles () {
    return this.execute('get', '/titles')
  },
  getDepartments () {
    return this.execute('get', '/departments')
  }

}