<template>
  <el-container class="home">
    <el-header>
      <common-tag/>
    </el-header>
    <el-main>
      <main-content/>
    </el-main>
  </el-container>
</template>

<script>
import HomeHeader from "@/components/HomeHeader";
import CommonTag from "@/components/CommonTag";
import MainContent from "./MainContent"

export default {
  name: "home",
  components: {CommonTag, HomeHeader, MainContent},
  data() {
    return {

    }
  }
}
</script>

<style scoped>
.home{
  background-color: #707070;
}
.el-header{
  background-color: #42b983;
}

</style>
