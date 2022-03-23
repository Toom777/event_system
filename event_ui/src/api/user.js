import axios from '../request'

// 获取所有社区名称+ID
export function communityIdAndName(){
  return axios({
    url: '/community/selectIdAndName',
    method: 'get'
  })
}

// 查询用户列表
export function listUser(query) {
  return axios({
    url: '/user/list',
    method: 'get',
    params: query
  })
}

//条件查询用户列表
export function listUserByConditions(query){
  return axios({
    url: '/user/searchList',
    method: 'get',
    params: query
  })
}


// 查询用户详细
export function getUser(userId) {
  return axios({
    url: '/user/selectById/' + userId,
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return axios({
    url: '/user/insert',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return axios({
    url: '/user/update',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return axios({
    url: '/user/del/' + userId,
    method: 'delete'
  })
}


