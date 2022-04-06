import axios from '../request'

export function userConfirmation(query) {
  return axios({
    url: '/confirmation/searchListByUser',
    method: 'get',
    params: query
  })
}


//通过活动Id查找对应用户ID
export function EnrollJuagement(query) {
  return axios({
    url: '/confirmation/confirmEnrollment',
    method: 'get',
    params: query
  })
}


// 查询活动确认列表
export function listConfirmation(query) {
  return axios({
    url: '/confirmation/searchList',
    method: 'get',
    params: query
  })
}

// 查询活动确认详细
export function getConfirmation(confirmationtId) {
  return axios({
    url: '/confirmation/selectById/' + confirmationtId,
    method: 'get'
  })
}

// 新增活动确认
export function addConfirmation(data) {
  return axios({
    url: '/confirmation/insert',
    method: 'post',
    data: data
  })
}

// 修改活动确认
export function updateConfirmation(data) {
  return axios({
    url: '/confirmation/update',
    method: 'put',
    data: data
  })
}

// 删除活动确认
export function delConfirmation(confirmationtId) {
  return axios({
    url: '/confirmation/del/' + confirmationtId,
    method: 'delete'
  })
}
