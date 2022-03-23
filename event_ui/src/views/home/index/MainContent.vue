<template>
  <div>
    <h4 style="height: 50px;">右侧首页内容</h4>
    <div class="x-index-section">
      <div class="container">
        <message/>
        <statistics/>
      </div>
    </div>
  </div>
</template>

<script>
import Message from "@/views/home/index/Message";
import Statistics from "@/views/home/index/Statistics";
export default {
  name: "main",
  components: {Statistics, Message}
}
</script>

<style scoped>
.x-index-section {
  padding-top: 30px;
  padding-bottom: 30px;
}
.container{
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}

</style>
