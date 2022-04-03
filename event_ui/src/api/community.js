import axios from '../request'

export function communityIdAndName(){
    return axios({
        url: '/community/selectIdAndName',
        method: 'get'
    })
}


// 查询社区列表
export function listCommunity(query) {
    return axios({
        url: '/community/searchList',
        method: 'get',
        params: query
    })
}

// 查询社区详细
export function getCommunity(communityId) {
    return axios({
        url: '/community/selectById/' + communityId,
        method: 'get'
    })
}

// 新增社区
export function addCommunity(data) {
    return axios({
        url: '/community/insert',
        method: 'post',
        data: data
    })
}

// 修改社区
export function updateCommunity(data) {
    return axios({
        url: '/community/update',
        method: 'put',
        data: data
    })
}

// 删除社区
export function delCommunity(communityId) {
    return axios({
        url: '/community/del/' + communityId,
        method: 'delete'
    })
}
