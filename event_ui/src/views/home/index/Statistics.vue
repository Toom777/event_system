<template>
  <div class="x-index-section">
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="info-box">
          <span class="info-box-icon bg-aqua"><i class="iconfont icon-group"></i></span>
          <div class="info-box-content">
            <span class="info-box-text">志愿者</span>
            <span id="SPAN_VolunteerCount" class="info-box-number">2121274</span>
          </div>
        </div>
      </el-col>

      <el-col :span="6">

      </el-col>

      <el-col :span="6">

      </el-col>

      <el-col :span="6">

      </el-col>
    </el-row>
  </div>
</template>

<script>
import '@/assets/iconfont/iconfont.css'
export default {
  name: "Statistics"
}
</script>


<style scoped>
.x-index-section{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.info-box{
  display: block;
  min-height: 90px;
  background: #fff;
  width: 100%;
  box-shadow: 0 1px 1px rgb(0 0 0);
  border-radius: 2px;
  margin-bottom: 15px;
}
.info-box-icon {
  border-top-left-radius: 2px;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 2px;
  display: block;
  float: left;
  height: 90px;
  width: 90px;
  text-align: center;
  font-size: 45px;
  line-height: 90px;
  background: rgba(0,0,0,0.2);
}
.bg-aqua {
  background-color: #00c0ef !important;
}
.icon-group{
  color: white;
}
</style>
