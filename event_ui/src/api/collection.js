import axios from '../request'

//查看是否收藏
export function confirmCollect(query) {
  return axios({
    url: '/collection/confirmCollect',
    method: 'get',
    params: query
  })
}

// 查询活动收藏列表
export function listCollection(query) {
  return axios({
    url: '/collection/searchList',
    method: 'get',
    params: query
  })
}

// 查询活动收藏详细
export function getCollection(collectionId) {
  return axios({
    url: '/collection/' + collectionId,
    method: 'get'
  })
}

// 新增活动收藏
export function addCollection(data) {
  return axios({
    url: '/collection/insert',
    method: 'post',
    data: data
  })
}

// 修改活动收藏
export function updateCollection(data) {
  return axios({
    url: '/collection/update',
    method: 'put',
    data: data
  })
}

// 删除活动收藏
export function delCollection(collectionId) {
  return axios({
    url: '/collection/del/' + collectionId,
    method: 'delete'
  })
}

//根据活动ID和用户ID删除活动收藏
export function delCollectionyAUId(query) {
  return axios({
    url: '/collection/delByAUId',
    method: 'delete',
    params: query
  })
}
