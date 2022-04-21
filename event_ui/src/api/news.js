import axios from '../request'

// 获取资讯类型
export function listNewsType() {
  return axios({
    url: '/news/getNewsType',
    method: 'get'
  })
}

// 查询资讯列表
export function listNews(query) {
  return axios({
    url: '/news/searchList',
    method: 'get',
    params: query
  })
}

// 查询资讯详细
export function getNews(newsId) {
  return axios({
    url: '/news/selectById/' + newsId,
    method: 'get'
  })
}

// 新增资讯
export function addNews(data) {
  return axios({
    url: '/news/insert',
    method: 'post',
    data: data
  })
}

// 修改资讯
export function updateNews(data) {
  return axios({
    url: '/news/update',
    method: 'put',
    data: data
  })
}

// 删除资讯
export function delNews(newsId) {
  return axios({
    url: '/news/del/' + newsId,
    method: 'delete'
  })
}

// 做为轮播图
export function carouselNews(newsId) {
  return axios({
    url: '/news/addCarousel/' + newsId,
    method: 'put'
  })
}

// 搜索作为轮播图的资讯
export function listCarouselNews() {
  return axios({
    url: '/news/carouselList',
    method: 'get'
  })
}

//消除作为轮播图的资格
export function delCarousel(newsId) {
  return axios({
    url: '/news/delCarousel/' + newsId,
    method: 'delete'
  })
}
