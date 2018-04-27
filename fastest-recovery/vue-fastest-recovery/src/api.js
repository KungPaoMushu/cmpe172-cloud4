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
    })
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
  }

}