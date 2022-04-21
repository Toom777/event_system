export default {
    state: {
        /*菜单栏收缩*/
        isCollapse: false,
        tabsList: [
            {
                path: '/home',
                name: 'home',
                label: '首页',
                icon: 'home'
            }
        ],
        /*选中菜单*/
        currentMenu: null
    },
    actions: {
        selectMenu(context, value) {
/*            console.log("这是context：",context);
            console.log("这是val：",value);*/
            context.commit('ElSelectMenu', value);
        }
    },
    mutations: {
        collapseMenu(state) {
            state.isCollapse = !state.isCollapse;
        },
        ElSelectMenu(state, val){
/*            console.log("mutations中的state:", state);
            console.log("mutations中的val:", val);*/
            if (val !== 'home') {
                state.currentMenu = val
/*                console.log("加工后的state:", state);*/
                const result = state.tabsList.findIndex(item => item.name === val)
                if (result === -1) {
                    state.tabsList.push(val)
                }
            } else {
                state.currentMenu = null
            }
        }
    }

}
