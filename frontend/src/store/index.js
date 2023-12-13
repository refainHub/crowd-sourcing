import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'


Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userId: null,
    userIdentity: null,
    isLogin: false,// 登录状态
    // token: '' // 用户登录成功后持有的token
  },
  modules: {
    app,
    settings,
    user,

  },
  getters
})

export default store
