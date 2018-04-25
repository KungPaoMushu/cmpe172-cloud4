<template>
  <v-app>
    <navbar @login="login" @logout="logout" :authenticated="authenticated"></navbar>
    <v-content>
      <router-view/>
    </v-content>
    </v-navigation-drawer>
    <v-footer fixed app>
      <span>&copy; Cloud4, 2018</span>
    </v-footer>
  </v-app>
</template>

<script>
  import Navbar from './components/core/navbar'
  export default {
    name: 'app',
    data () {
      return {
        activeUser: null
      }
    },
    computed: {
      authenticated () {
        console.log(this.activeUser)
        return this.activeUser !== null && this.activeUser !== undefined
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
        this.activeUser = await this.$auth.getUser()
      },
      async logout () {
        await this.$auth.logout()
        await this.refreshActiveUser()
        this.$router.push('/')
      }
    },
    components: {
      'navbar': Navbar
    }
  }
</script>
