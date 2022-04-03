import axios from '../request'

// 获取活动标签
export function listActivityTag() {
  return axios({
    url: '/activity/getActivityTags',
    method: 'get'
  })
}
// 查询活动信息列表
export function listActivity(query) {
  return axios({
    url: '/activity/searchList',
    method: 'get',
    params: query
  })
}

// 查询活动信息详细
export function getActivity(activityId) {
  return axios({
    url: '/activity/selectById/' + activityId,
    method: 'get'
  })
}

// 新增活动信息
export function addActivity(data) {
  return axios({
    url: '/activity/insert',
    method: 'post',
    data: data
  })
}

// 修改活动信息
export function updateActivity(data) {
  return axios({
    url: '/activity/update',
    method: 'put',
    data: data
  })
}

// 删除活动信息
export function delActivity(activityId) {
  return axios({
    url: '/activity/del/' + activityId,
    method: 'delete'
  })
}
