<template>
  <v-app>
    <sidebar v-if="authenticated"></sidebar>
    <navbar @login="login" @logout="logout"></navbar>
    <v-content>
      <router-view/>
    </v-content>
    </v-navigation-drawer>
  </v-app>
</template>

<script>
  import Navbar from './components/core/navbar'
  import Sidebar from './components/core/sidebar'
  export default {
    name: 'app',
    computed: {
      authenticated () {
        return this.$store.getters.authenticated
      }
    },
    async created () {
      await this.refreshActiveUser()
    },
    watch: {
      // everytime a route is changed refresh the activeUser
      '$route': 'refreshActiveUser'
    },
    methods: {
      login () {
        this.$auth.loginRedirect()
      },
      async refreshActiveUser () {
        var activeUser = await this.$auth.getUser()
        this.$store.commit('setUser', activeUser)
      },
      async logout () {
        await this.$auth.logout()
        await this.refreshActiveUser()
        this.$router.push('/')
      }
    },
    components: {
      'navbar': Navbar,
      'sidebar': Sidebar
    }
  }
</script>
