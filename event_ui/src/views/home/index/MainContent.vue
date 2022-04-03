<template>
  <div class="container">
    <!--轮播图-->
    <el-row>
      <carousel-content/>
    </el-row>

    <!--数据统计-->
    <statistics/>

    <!--消息栏-->
    <el-row>
      <message/>
    </el-row>


  </div>
</template>

<script>
import Message from "@/views/home/index/Message";
import Statistics from "@/views/home/index/Statistics";
import CarouselContent from "@/views/home/index/CarouselContent";
export default {
  name: "MainContent",
  components: {CarouselContent, Statistics, Message}
}
</script>

<style scoped>


</style>
