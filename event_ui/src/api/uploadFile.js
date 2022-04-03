import axios from '../request'

/*上传图片*/
export function uploadPicture(data) {
    return axios({
        url: '/upload/uploadPicture',
        method: 'post',
        data: data
    })
}
