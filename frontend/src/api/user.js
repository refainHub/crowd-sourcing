import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}


export function tryLogin() {
  return request({
    url: '/user/tryLogin',
    method: 'get'
  })
}
