import { createStore } from 'vuex'

export default createStore({
  state: {
    //用户信息
    user: JSON.parse(localStorage.getItem("user")) || null,
 
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
