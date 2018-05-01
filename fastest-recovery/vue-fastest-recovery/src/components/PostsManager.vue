<template>
  <div class="container-fluid mt-4">
    <h1 class="h1">Posts Manager</h1>
    <b-alert :show="loading" variant="info">Loading...</b-alert>
    <b-row>
      <b-col>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Updated At</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="post in posts" :key="post.id">
              <td>{{ post.id }}</td>
              <td>{{ post.title }}</td>
              <td>{{ post.updatedAt }}</td>
              <td class="text-right">
                <a href="#" @click.prevent="populatePostToEdit(post)">Edit</a> -
                <a href="#" @click.prevent="deletePost(post.id)">Delete</a>
              </td>
            </tr>
          </tbody>
        </table>
      </b-col>
      <b-col lg="3">
        <b-card :title="(model.id ? 'Edit Post ID#' + model.id : 'New Post')">
          <form @submit.prevent="savePost">
            <b-form-group label="Title">
              <b-form-input type="text" v-model="model.title"></b-form-input>
            </b-form-group>
            <b-form-group label="Body">
              <b-form-textarea rows="4" v-model="model.body"></b-form-textarea>
            </b-form-group>
            <div>
              <b-btn type="submit" variant="success">Save Post</b-btn>
            </div>
          </form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import api from '@/api'
import axios from 'axios';

export default {
  data () {
    return {
      loading: false,
      posts: [],
      model: {},
      actors: [],
      actor: {},
      created_actor: {
        first_name: "ibrahim",
        last_name: "ibrahim"
      }
    }
  },
  async created () {
    this.refreshPosts()
  },
  methods: {
    async refreshPosts () {
      this.loading = true
      this.posts = await api.getPosts()
      
      console.log("testinging get all actors")
      this.actors = await api.getActors()
      console.log("testinging get all films")
      console.log(await api.getFilms())
      console.log("testinging get all rentals")
      console.log(await api.getRentals())
      console.log("testinging get all users")
      console.log(await api.getUsers())


      this.loading = false
    },
    async populatePostToEdit (post) {
      this.model = Object.assign({}, post)
    },
    async savePost () {
      if (this.model.id) {
        await api.updatePost(this.model.id, this.model)
      } else {
        await api.createPost(this.model)

        //does not work due to Authorization header is required
        //await api.createTask(this.created_task)
        //does not work
        //await api.createActor(this.created_actor)
      }
      this.model = {} // reset form
      await this.refreshPosts()
    },
    async deletePost (id) {
      if (confirm('Are you sure you want to delete this post?')) {
        // if we are editing a post we deleted, remove it from the form
        if (this.model.id === id) {
          this.model = {}
        }
        await api.deletePost(id)
        await this.refreshPosts()

        // does not work due to foreign key constraint, 
        //await api.deleteActor(200)
      }
    }
  }
}
</script>