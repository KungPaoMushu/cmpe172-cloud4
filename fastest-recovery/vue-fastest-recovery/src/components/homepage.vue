<template>
  <v-container fluid py-0 px-0>
    <v-layout row wrap align-center v-if="authenticated">
      <v-flex xs12>
        <v-layout
          column
          wrap
          class="my-5"
          align-center
        >
          <v-flex xs12 sm4 class="my-3">
            <div class="text-xs-center">
              <h2 class="headline">Get started with the application</h2>
            </div>
          </v-flex>
          <v-flex xs12>
            <v-container grid-list-xl>
              <v-layout row wrap>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text>
                      <Timeline :id="'cisco'" :sourceType="'profile'" :options="{ tweetLimit: '3' }"/>
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md8>
                  <v-container fluid>
                    <v-layout row wrap>
                      <v-flex xs12>
                        <v-card class="elevation-0 transparent">
                          <v-card-title primary-title class="layout justify-center">
                            <div class="headline">Manage employees</div>
                          </v-card-title>
                          <v-card-text>
                            Fastest Recovery is a proof-of-concept web application connected to Okta's SSO service. On this site, you may explore data and view graphs of all of Company X's employees. The application is also connected to GitHub, Jenkins, and Twitter.
                          </v-card-text>
                        </v-card>
                      </v-flex>
                      <v-flex xs12>
                        <v-btn block dark color="grey darken-3" href="https://dev-487888.oktapreview.com/home/github/0oaethjs2fl28KhP50h7/114?fromHome=true">Sign in to GitHub</v-btn>
                        <v-btn block dark color="blue lighten-2" href="https://dev-487888.oktapreview.com/home/inboxbygmail/0oaevlcjniAqjK3GJ0h7/aln1cx5cu343wIl7s1d8?fromHome=true">Sign in to Inbox by Gmail</v-btn>
                        <v-btn block dark color="red" href="https://dev-487888.oktapreview.com/home/sjsudev487888_jenkinssaml_1/0oaevjb47jsIipWNc0h7/alnevk4yw067N3dZj0h7?fromHome=true">Sign in to Jenkins</v-btn>
                        <v-btn block dark color="primary" href="https://dev-487888.oktapreview.com/home/twitter/0oaevl260toVRQaEz0h7/150?fromHome=true">Sign in to Twitter</v-btn>
                      </v-flex>
                    </v-layout>
                  </v-container>
                </v-flex>
              </v-layout>
            </v-container>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
    <v-layout row wrap align-center v-else>
      <v-flex xs12 class="text-xs-center">
        <v-parallax src="http://www.youthincmag.com/wp-content/uploads/2012/02/DigitalWorkplace.jpg" height="500">
          <v-layout
            column
            align-center
            justify-center
            class="white--text"
          >
            <img src="@/assets/logo.png" alt="Vuetify.js" height="200">
            <h1 class="white--text mb-2 display-1 text-xs-center">Welcome to Fastest Recovery</h1>
            <div class="subheading mb-3 text-xs-center">Please log in to continue</div>
            <v-btn
              class="blue mt-5"
              dark
              large
              @click="login"
            >
              Login
            </v-btn>
          </v-layout>
        </v-parallax>
      </v-flex>
      <v-flex xs12>
        <v-layout
          column
          wrap
          class="my-5"
          align-center
        >
          <v-flex xs12 sm4 class="my-3">
            <div class="text-xs-center">
              <h2 class="headline">The best way to start developing</h2>
              <span class="subheading">
                Cras facilisis mi vitae nunc 
              </span>
            </div>
          </v-flex>
          <v-flex xs12>
            <v-container grid-list-xl>
              <v-layout row wrap align-center>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text class="text-xs-center">
                      <v-icon x-large class="blue--text text--lighten-2">color_lens</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-xs-center">Material Design</div>
                    </v-card-title>
                    <v-card-text>
                      Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare. 
                      Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. 
                      Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti. 
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text class="text-xs-center">
                      <v-icon x-large class="blue--text text--lighten-2">flash_on</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline">Fast development</div>
                    </v-card-title>
                    <v-card-text>
                      Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare. 
                      Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. 
                      Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti. 
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text class="text-xs-center">
                      <v-icon x-large class="blue--text text--lighten-2">build</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-xs-center">Completely Open Sourced</div>
                    </v-card-title>
                    <v-card-text>
                      Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare. 
                      Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. 
                      Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti. 
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import { Tweet, Moment, Timeline } from 'vue-tweet-embed'
  export default {
    computed: {
      authenticated () {
        return this.$store.getters.authenticated
      }
    },
    methods: {
      login () {
        this.$auth.loginRedirect()
      }
    },
    components: {
      Timeline, Moment, Tweet
    }
  }
</script>