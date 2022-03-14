<template>
  <el-header>
    <div class="l-content">
      <el-button plain icon="el-icon-menu" size="mini"></el-button>
      <h3 style="color: #ffffff">首页</h3>
    </div>
    <div class="r-content">
      <el-dropdown>
        <el-button class="user" type="primary">
          用户
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>多喝水</el-dropdown-item>
          <el-dropdown-item>要开心</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script>
export default {
  name: "HomeHeader"
}
</script>

<style scoped>
.el-header{
  display: flex;
  height: 100%;
  justify-content: space-between;
  align-items: center;
}
.l-content{
  display: flex;
  align-items: center;
}
.l-content .el-button{
  margin-right: 20px;
}

.r-content .user{
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
</style>
