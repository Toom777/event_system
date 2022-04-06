<template>
  <div id="app" class="login">
    <el-form :model="loginForm"
             :rules="rules"
             ref="loginForm"
             @keyup.enter.native="submitForm"
             label-width="100px"
             class="login-form">
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
      <el-form-item prop="code" >
        <el-input
            v-model="loginForm.code"
            placeholder="验证码"
            style="width: 63%"
        >
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div>
      </el-form-item>


      <!--登录按钮-->
      <el-form-item style="width:100%;">
        <el-button type="primary" @click="submitForm()"  :loading="loading">
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
        code: '',
        uuid: ''
      },
      /*验证校验*/
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]

      },
      loading: false,
      codeUrl: ''
    };
  },
  methods: {
    /*获取验证码*/
    getCode() {
      this.axios.get("/captchaImage").then((res) => {
        this.codeUrl = "data:image/jpg;base64," + res.data.img;
        this.loginForm.uuid = res.data.uuid;
        //console.log(res)
      });
    },

    submitForm() {
      /*表单验证*/
      this.$refs.loginForm.validate(valid => {
        if (valid){
          this.loading = true;
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({path: "/index"});
            this.$message({
              showClose: true,
              message: this.$store.getters.getName + ' 登录成功',
              type: 'success'
            });
          }).catch(() => {
            this.loading = false;
            this.loginForm.code = '';
            this.getCode();
          })
        }
      });
    },
    register(){
      this.$router.push("/register")
    },
    clear(){
      this.$store.commit("REMOVE_INFO")
      /*将本地存储的菜单栏active值调为home*/
      localStorage.setItem("active", "home")
    }
  },
  created() {
    this.clear()
    this.getCode()
  }
}
</script>

<style scoped>

.login{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-color: #42b983;
}
.title{
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}
.login-form {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  box-shadow: 0 0 30px #707070;
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
.login-code {
  width: 33%;
  height: 38px;
  float: right;
}

.login-code .login-code-img {
  height: 38px;
}
</style>
