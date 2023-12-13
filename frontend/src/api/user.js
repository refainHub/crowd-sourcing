import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/crowd-sourcing/user/login',
    method: 'post',
    data
  })
}


export function tryLogin() {
  return request({
    url: '/crowd-sourcing/user/tryLogin',
    method: 'get'
  })
}
