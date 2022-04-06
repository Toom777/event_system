import axios from '../request'


// 查询公告列表
export function listNotice(query) {
  return axios({
    url: '/notice/searchList',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getNotice(noticeId) {
  return axios({
    url: '/notice/selectById/' + noticeId,
    method: 'get'
  })
}

// 新增公告
export function addNotice(data) {
  return axios({
    url: '/notice/insert',
    method: 'post',
    data: data
  })
}

// 修改公告
export function updateNotice(data) {
  return axios({
    url: '/notice/update',
    method: 'put',
    data: data
  })
}

// 删除公告
export function delNotice(noticeId) {
  return axios({
    url: '/notice/del/' + noticeId,
    method: 'delete'
  })
}
