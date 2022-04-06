<template>
  <div class="container">
    <!--轮播图-->
    <el-row>
      <carousel-content/>
    </el-row>

    <!--消息栏-->
    <el-row>
      <message/>
    </el-row>
    <!--数据统计-->
    <statistics/>
    <!--活动栏-->
    <el-row>
      <new-activity/>
    </el-row>

  </div>
</template>

<script>
import Message from "@/views/home/index/Message";
import Statistics from "@/views/home/index/Statistics";
import CarouselContent from "@/views/home/index/CarouselContent";
import NewActivity from "@/views/home/index/NewActivity";
export default {
  name: "MainContent",
  components: {NewActivity, CarouselContent, Statistics, Message}
}
</script>

<style scoped>


</style>
