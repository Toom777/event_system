import axios from "axios";
import {Message} from 'element-ui'
import router from './router'
/*axios配置*/
axios.defaults.baseURL = "http://localhost:8888"

//请求拦截
axios.interceptors.request.use(config => {
    return config;
}, function (error) {
    console.log('error!');
    return Promise.reject(error);
});

//响应拦截
axios.interceptors.response.use(response => {
        let res = response.data;
        if (res.code === 200) {
            //Element.Message.success(res.message, {duration: 3 * 1000})
            return response
        } else if(res.code === 401){
            Message.error(res.message, {duration: 3 * 1000})
            router.push("/login")
            return Promise.reject(res.message)
        }else {
            Message.error(res.message, {duration: 3 * 1000})
            return Promise.reject(res.message)
        }
    },(error => {
        return Promise.reject(error);
    })
);

export default axios;
