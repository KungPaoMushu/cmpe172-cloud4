<template>
  <v-toolbar fixed app clipped-left flat color="primary" class="white--text">
    <v-toolbar-side-icon dark @click="onSidebar" :disabled="!authenticated"></v-toolbar-side-icon>
    <v-toolbar-title>Fastest Recovery</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn v-if="!authenticated" flat color="white" @click.stop="onLogin">Login</v-btn>
      <v-btn v-if="authenticated" flat color="white" @click.stop="onLogout">Logout</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  export default {
    computed: {
      authenticated () {
        return this.$store.getters.authenticated
      }
    },
    methods: {
      onLogin () {
        this.$emit('login')
      },
      onLogout () {
        this.$emit('logout')
      },
      onSidebar () {
        this.$store.commit('toggleSidebar')
      }
    }
  }
</script>