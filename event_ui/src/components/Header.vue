<template>
  <header>
      <div class="l-content">
        <el-button plain icon="el-icon-menu" size="mini"></el-button>
        <h3 style="color: #ffffff">首页</h3>
        <p style="color: white; margin-left: 100px">{{name}}</p>
      </div>
      <div class="r-content">
        <el-dropdown>
          <el-button class="user" type="primary">
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>多喝水</el-dropdown-item>
            <el-dropdown-item>要开心</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
  </header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      name: ''
    }
  },
  methods: {
    getUserName() {
      this.name = this.$store.getters.getUser.name
    }
  },
  created() {
    this.getUserName()
  }
}
</script>

<style scoped>
/*.hello{
  position: relative;
  top:50%;
  transform:translateY(-50%);
  left:50%;
  transform:translateX(-50%);
  width: 90%;
}*/

header{
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

.r-content{
  display: flex;
  align-items: center;
}
.r-content .user{
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
</style>
