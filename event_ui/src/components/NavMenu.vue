<template>
    <el-menu
        :default-active="MenuActive"
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
    >
      <h4 style="color: white;">社区公益活动管理系统</h4>
      <el-menu-item index="home" @click="saveActive('home')">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <!--没有子菜单-->
      <el-menu-item
          :index="item.menu.path"
          v-for="item in menuList"
          :key="item.menu.menuId"
          v-if="item.subMenu == ''"
          @click="saveActive(item.menu.path)"
      >
        <i :class="item.menu.icon"></i>
        <span slot="title">{{item.menu.menuName}}</span>
      </el-menu-item>

      <!--有子菜单-->
      <el-submenu
          :index="item.menu.path"
          v-for="item in menuList"
          :key="item.menu.menuId"
          v-if="item.subMenu != ''"
      >
        <template slot="title">
          <i :class="item.menu.icon"></i>
          <span>{{item.menu.menuName}}</span>
        </template>
        <el-menu-item
            :index="subItem.path"
            v-for="subItem in item.subMenu"
            :key="subItem.menuId"
            @click="saveActive(subItem.path)"
        >
          <i :class="subItem.icon"></i>
          <span slot="title">{{subItem.menuName}}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
</template>

<script>
export default {
  name: "NavMenu",
  data () {
    return{
        /*菜单是否收起*/
        isCollapse: false,
        /*菜单列表*/
        menuList: [],
        /*当前浏览的模块*/
        MenuActive: localStorage.getItem("active")
    };
  },
  methods: {
    /*获取菜单列表*/
    getMenuList() {
      let username = "";
      /*判断store中是否有用户*/
      if (this.$store.getters.getUser == "" || this.$store.getters.getUser == null){
        username = "";
      }else {
        username = this.$store.getters.getUser.username;
      }
      this.axios.get("/getRouters?username=" + username, {
        headers: {
          "Authorization": this.$store.getters.getToken
        }
      }).then((res) => {
        console.log(res);
        const menuList = res.data.data;
        this.menuList = menuList;
      })
    },
    saveActive(val) {
      localStorage.setItem("active", val);
      this.MenuActive = val;
    }
  },
  created() {
    this.getMenuList()
  }

}
</script>

<style>
/*消除侧边导航栏子菜单突出*/
.el-menu{
  border-right: 0;
}
</style>
