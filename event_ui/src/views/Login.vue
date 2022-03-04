<template>
  <div id="app" class="login">
    <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="login-form">
      <h3 class="title">社区公益活动管理系统</h3>
      <!--账号-->
      <el-form-item prop="username">
        <el-input
            placeholder="账 号"
            prefix-icon="el-icon-user-solid"
            v-model="loginForm.username">
        </el-input>
      </el-form-item>

      <!--密码-->
      <el-form-item prop="password">
        <el-input
            placeholder="密 码"
            prefix-icon="el-icon-s-help"
            v-model="loginForm.password">
        </el-input>
      </el-form-item>

      <!--验证码-->
<!--      <el-form-item label="活动名称" prop="name">
        <el-input v-model="input" placeholder="请输入内容"></el-input>
        <div class="login-code">
          <el-image></el-image>
        </div>
      </el-form-item>-->

      <!--登录按钮-->
      <el-form-item style="width:100%;">
        <el-button type="primary" @click="submitForm()" :loading="loading">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <el-button type="primary" @click="register()">
          注册
        </el-button>
      </el-form-item>



    </el-form>

    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2022 Toom All Rights Reserved.</span>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      /*初始化*/
      loginForm: {
        username: '',
        password: '',
      },
      /*验证校验*/
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      },
      loading: false
    };
  },
  methods: {
    submitForm() {
      this.loading = true;
      /*跳转到首页*/
      this.$router.push("/index")
    },
    register(){
      this.$router.push("/register")
    }
  }
}
</script>

<style scoped>
#app{
  background-color: #42b983;
}
.login{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.title{
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}
.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
}
.el-input {
  height: 38px;
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
</style>
