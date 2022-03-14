<template>
  <el-container style="height: 100%;">
    <!--左侧菜单栏-->
    <el-aside width="180px">
      <nav-menu></nav-menu>
    </el-aside>

    <el-container>
      <!--顶部-->
      <el-header>
        <Header></Header>
      </el-header>
      <!--主要页面-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import NavMenu from "@/components/NavMenu";
import Header from "@/components/Header";
export default {
  name: "index",
  components: {Header, NavMenu},
}
</script>

<style>
.el-header{
  background-color: #545c64;
  color: #333;
}

.el-aside {
  background-color: #545c64;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
}

</style>
