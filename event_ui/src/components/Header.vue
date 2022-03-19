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
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
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
    /*获取用户名*/
    getUserName() {
      this.name = this.$store.getters.getUser.name
    },
    /*退出登录*/
    logout(){
      this.$confirm('确认退出?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '退出成功!'
        });
        this.$store.commit("REMOVE_INFO");
        this.$router.replace("/login");
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    }
  },
  created() {
    this.getUserName()
  }
}
</script>

<style scoped>

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
