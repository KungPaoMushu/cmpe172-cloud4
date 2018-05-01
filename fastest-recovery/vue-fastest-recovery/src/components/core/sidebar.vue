<template>
  <v-navigation-drawer clipped disable-route-watcher disable-resize-watcher fixed app v-model="sidebar">
    <v-card flat>
      <v-card-title class="title">Welcome, {{ user.given_name }}</v-card-title>
    </v-card>
    <v-divider></v-divider>
    <v-list dense class="pt-0">
      <v-list-tile v-for="item in items" :key="item.title" @click="">
        <v-list-tile-action>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-list-tile-title>{{ item.title }}</v-list-tile-title>
        </v-list-tile-content>
      </v-list-tile>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  export default {
    data () {
      return {
        items: [
          { title: 'Home', icon: 'dashboard' },
          { title: 'About', icon: 'question_answer' }
        ]
      }
    },
    computed: {
      sidebar: {
        get () {
          return this.$store.getters.sidebar
        },
        set (value) {
          this.$store.commit('setSidebar', value)
        }
      },
      user () {
        console.log(this.$store.getters.user)
        return this.$store.getters.user
      }
    }
  }
</script>