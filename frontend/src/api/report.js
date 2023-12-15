import request from '@/utils/request'

export function lookReports(taskId) {
  return request({
    url: '/report/lookReports',
    method: 'get',
    params: {
      taskId: taskId
    }
  })
}

// 查找众包工人最新的报告
export function workerReport(taskId, userId) {
  return request({
    url: '/report/showAnnotation',
    method: 'get',
    params: {
      taskId: taskId,
      userId: userId
    }
  })
}

export function giveMarkAndComment(reportId, userId, star, comment) {
  return request({
    url: '/report/markAndComment',
    method: 'post',
    data: {
      reportId: reportId,
      userId: userId,
      mark: star,
      comment: comment
    }
  })
}

export function getCommentsUnderReport(reportId) {
  return request({
    url: '/report/getCommentsUnderReport',
    method: 'get',
    params: {
      reportId: reportId
    }
  })
}

export function giveAnnotation(reportId, userId, taskId, note, steps, device, star, starNum, annotationUserId) {
  console.log(userId)
  return request({
    url: '/report/addAnnotation',
    method: 'post',
    data: {
      id: reportId,
      userId: userId,
      taskId: taskId,
      note: note,
      steps: steps,
      device: device,
      star: star,
      starNum: starNum,
      annotationUserId: annotationUserId
    }
  })
}

export function findSimilarReportsFromSameTask(reportId) {
  return request({
    url: '/report/similarReports',
    method: 'get',
    params: {
      reportId: reportId
    }
  })
}

export function findCoworkers(taskId, userId) {
  return request({
    url: '/report/findCoworkers',
    method: 'get',
    params: {
      taskId: taskId,
      userId: userId
    }
  })
}

export function findLowQualityReport(reportId) {
  return request({
    url: '/report/lowQuality',
    method: 'get',
    params: {
      reportId: reportId
    }
  })
}

export function getReportClassUnderTask(taskId) {
  return request({
    url: '/report/cluster',
    method: 'get',
    params: {
      taskId: taskId
    }
  })
}
