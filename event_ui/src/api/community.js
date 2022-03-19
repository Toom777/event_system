import axios from '../request'

export function communityIdAndName(){
    return axios({
        url: '/community/selectIdAndName',
        method: 'get'
    })
}
