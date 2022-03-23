import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import tab from './tab'

Vue.use(Vuex)

export default new Vuex.Store({
  getters:{},
  modules: {
    user,
    tab
  }
})
