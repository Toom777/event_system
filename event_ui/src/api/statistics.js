import axios from '../request'

export function getStatistics() {
    return axios({
        url: '/statistics/statisticsCount',
        method: 'get'
    })
}
