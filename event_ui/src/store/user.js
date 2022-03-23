import {login} from "@/api/login";


const user = {
    state: {
        token: localStorage.getItem("token"),
        userInfo: JSON.parse(localStorage.getItem("userInfo")),
        name: localStorage.getItem("name"),
        avatar: localStorage.getItem("avatar"),
        roles: localStorage.getItem("roles"),
        permissions: localStorage.getItem("permissions")
    },
    //同步
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
            localStorage.setItem("token", token);
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo;
            localStorage.setItem("userInfo", JSON.stringify(userInfo));
        }
        ,
        SET_NAME: (state, name) => {
            state.name = name;
            localStorage.setItem("name", name);
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar;
            localStorage.setItem("avatar", avatar);
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles;
            localStorage.setItem("roles", roles);
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions;
            localStorage.setItem("permissions", JSON.stringify(permissions));
        },
        REMOVE_INFO: (state) => {
            state.token = '';
            state.userInfo = {};
            state.name = '';
            state.avatar = '';
            state.roles = '';
            state.permissions = '';
            localStorage.setItem("token", '');
            localStorage.setItem("userInfo", JSON.stringify(''))
            localStorage.setItem("name", '');
            localStorage.setItem("avatar", '');
            localStorage.setItem("roles", '');
            localStorage.setItem("permissions", '');
        }
    },

    //异步
    actions: {
        //登录
        Login({ commit }, userInfo) {
            const username = userInfo.username
            const password = userInfo.password
            const code = userInfo.code
            const uuid = userInfo.uuid
            return new Promise((resolve, reject) => {
            login(username, password, code, uuid).then(res => {
                //console.log(res)
                /*将登录表单提交到store*/
                commit("SET_TOKEN", res.data.data.token)
                commit("SET_USERINFO", res.data.data.user)
                commit("SET_NAME", res.data.data.user.name)
                commit("SET_AVATAR", res.data.data.user.avatar)
                commit("SET_ROLES", res.data.data.roles)
                commit("SET_PERMISSIONS", res.data.data.permissions)
                resolve()
            }).catch(error => {
                reject(error)
            })
            })
        }

    },
    getters: {
        getUser: state =>{
            return state.userInfo;
        },
        getName: state => {
            return state.name;
        },
        getAvatar: state => {
            return state.avatar;
        },
        getRoles: state => {
            return state.roles;
        },
        getPermissions: state => {
            return state.permissions;
        },
        getToken: state => {
            if (state.token == null){
                return '';
            }else {
                return state.token;
            }
        }
    }
}

export default user
