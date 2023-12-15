import request from '@/utils/request'


/**
 * 发布任务
 * @param data
 * @returns {*}
 */
export function issue(data) {
  return request({
    url: '/task/issue',
    method: 'post',
    data
  })
}

/**
 * 更新任务
 * @param data
 * @returns {*}
 */
export function update(data) {
  return request({
    url: '/task/update',
    method: 'post',
    data
  })
}
