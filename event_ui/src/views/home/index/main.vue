<template>
  <div>
    <h4 style="height: 50px;">右侧首页内容</h4>
    <!--轮播图效果-->

  </div>
</template>

<script>
export default {
  name: "main"
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
