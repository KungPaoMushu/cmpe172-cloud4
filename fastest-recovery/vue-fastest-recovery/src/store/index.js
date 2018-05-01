import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    user: null,
    sidebar: false
  },
  getters: {
    user (state) {
      return state.user
    },
    authenticated (state) {
      return state.user !== null && state.user !== undefined
    },
    sidebar (state) {
      return state.sidebar
    }
  },
  mutations: {
    setUser (state, payload) {
      state.user = payload
    },
    setSidebar (state, payload) {
      state.sidebar = payload
    },
    toggleSidebar (state) {
      state.sidebar = !state.sidebar
    }
  },
  actions: {

  }
})
