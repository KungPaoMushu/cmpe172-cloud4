<template>
  <v-toolbar flat color="error" class="white--text">
    <v-toolbar-title>Fastest Recovery</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn v-if="!authenticated" flat color="white" @click.stop="onLogin">Login</v-btn>
      <v-btn v-if="authenticated" flat color="white" to="/profile">Profile</v-btn>
      <v-btn v-if="authenticated" flat color="white" @click.stop="onLogout">Logout</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  export default {
    props: ['authenticated'],
    methods: {
      onLogin () {
        this.$emit('login')
      },
      onLogout () {
        this.$emit('logout')
      }
    }
  }
</script>