<template>
  <v-navigation-drawer clipped disable-route-watcher disable-resize-watcher fixed app v-model="sidebar">
    <v-card flat>
      <v-card-title class="title">Welcome, {{ user.given_name }}</v-card-title>
    </v-card>
    <v-divider></v-divider>
    <v-list dense class="pt-0">
      <v-list-tile v-for="item in items" :key="item.title" @click="" :to="item.to">
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
          { title: 'Home', icon: 'dashboard', to: '/' },
          { title: 'Employees', icon: 'people', to: '/employees' },
          { title: 'Departments', icon: 'location_city', to: '/departments' }
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
        return this.$store.getters.user
      }
    }
  }
</script>