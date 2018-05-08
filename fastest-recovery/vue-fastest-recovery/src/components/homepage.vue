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
                      <v-flex xs12 md4>
                        <v-btn block color="blue lighten-3" href="https://dev-487888.oktapreview.com/home/github/0oaethjs2fl28KhP50h7/114?fromHome=true"><img src="https://op1static.oktacdn.com/assets/img/logos/github.68d84f592210c884e703a5d119ea73d7.png"></v-btn>
                      </v-flex>
                      <v-flex xs12 md4>
                        <v-btn block color="blue lighten-3" href="https://dev-487888.oktapreview.com/home/inboxbygmail/0oaevlcjniAqjK3GJ0h7/aln1cx5cu343wIl7s1d8?fromHome=true"><img src="https://op1static.oktacdn.com/bc/globalFileStoreRecord?id=gfsci5ec9tdLSnFTr0h7"></v-btn>
                      </v-flex>
                      <v-flex xs12 md4>
                        <v-btn block color="blue lighten-3" href="https://dev-487888.oktapreview.com/home/sjsudev487888_jenkinssaml_1/0oaevjb47jsIipWNc0h7/alnevk4yw067N3dZj0h7?fromHome=true"><img src="https://op1static.oktacdn.com/bc/globalFileStoreRecord?id=gfs3upz62lj5X2n5X0h7"></v-btn>
                      </v-flex>
                      <v-flex xs12 md4>
                        <v-btn block color="blue lighten-3" href="https://dev-487888.oktapreview.com/home/twitter/0oaew70xjoi2pelVk0h7/150?fromHome=true"><img src="https://op1static.oktacdn.com/assets/img/logos/twitter.5497b8fc1bc55930b05f0ba41b77b49d.png"></v-btn>
                      </v-flex>
                      <v-flex xs12 md4>
                        <v-btn block color="blue lighten-3" href="https://dev-487888.oktapreview.com/home/skydrive/0oaew70ocwhEzIKnX0h7/857?fromHome=true"><img src="https://op1static.oktacdn.com/bc/globalFileStoreRecord?id=gfs2e9vec3LLQPQYBNJZ"></v-btn>
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
              <h2 class="headline">Company X</h2>
              <span class="subheading">
                Employee Management System
              </span>
            </div>
          </v-flex>
          <v-flex xs12>
            <v-container grid-list-xl>
              <v-layout row wrap>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text class="text-xs-center">
                      <v-icon x-large class="blue--text text--lighten-2">grid_on</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-xs-center">View Employee Data</div>
                    </v-card-title>
                    <v-card-text>
                      Search the employee database for information such as names, birthdays, hire dates, and more! You can also browse department and access the employees' salary information.
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text class="text-xs-center">
                      <v-icon x-large class="blue--text text--lighten-2">trending_up</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline">View Salary Trends</div>
                    </v-card-title>
                    <v-card-text>
                      View each employee's salary records on a line chart - easily observe what the salary trend looks like with a simple graph!
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card class="elevation-0 transparent">
                    <v-card-text class="text-xs-center">
                      <v-icon x-large class="blue--text text--lighten-2">security</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-xs-center">Access SSO</div>
                    </v-card-title>
                    <v-card-text>
                      Use your SSO Login to access other linked applications such as Twitter, Jenkins, GitHub, OneDrive, and much more! No need to remember all those different passwords - Okta has you covered!
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