import axios from '../request'

//登录
export function login(username, password, code, uuid) {
    const data = {
        username,
        password,
        code,
        uuid
    }
    return axios({
        url: '/login',
        method: 'post',
        data: data
    })
}
