<template>
  <el-aside width="200px">
    <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router>
      <!--无子级首页-->
      <el-menu-item index="/home">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <!--有子级-->
      <el-menu-item index="/user">
        <i class="el-icon-s-home"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>

      <el-menu-item index="/community">
          <i class="el-icon-s-home"></i>
        <span slot="title">社区管理</span>
      </el-menu-item>

      <el-submenu index="3">
        <template slot="title"><i class="el-icon-setting"></i>活动管理</template>
        <el-menu-item index="/pageone">发布活动</el-menu-item>
        <el-menu-item index="/pagetwo">活动列表</el-menu-item>
        <el-menu-item index="/pagethree">活动跟踪</el-menu-item>
      </el-submenu>

    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: "NavMenu",
  data () {
    return{

    };
  },
  methods: {
  }

}
</script>

<style scoped>

</style>
