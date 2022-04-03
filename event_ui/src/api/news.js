import axios from '../request'

// 查询资讯列表
export function listNews(query) {
  return axios({
    url: '/news/list',
    method: 'get',
    params: query
  })
}

// 查询资讯详细
export function getNews(newsId) {
  return axios({
    url: '/news/' + newsId,
    method: 'get'
  })
}

// 新增资讯
export function addNews(data) {
  return axios({
    url: '/news',
    method: 'post',
    data: data
  })
}

// 修改资讯
export function updateNews(data) {
  return axios({
    url: '/news',
    method: 'put',
    data: data
  })
}

// 删除资讯
export function delNews(newsId) {
  return axios({
    url: '/news/' + newsId,
    method: 'delete'
  })
}