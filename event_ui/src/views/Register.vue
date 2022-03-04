<template>
  <div id="app" class="register">
    <el-form
        label-position="right"
        :model="registerForm"
        :rules="rules"
        ref="registerForm"
        label-width="100px"
        class="register-form">
      <h3 class="title">用户注册</h3>

      <el-row>
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <!--账号-->
            <el-form-item label="账 号" prop="username">
              <el-input v-model="registerForm.username" clearable></el-input>
            </el-form-item>

            <!--密码-->
            <el-form-item label="密 码" prop="password">
              <el-input type="password" v-model="registerForm.password" clearable></el-input>
            </el-form-item>

            <!--真实姓名-->
            <el-form-item label="真实姓名" prop="name">
              <el-input v-model="registerForm.name" clearable></el-input>
            </el-form-item>

            <!--性别-->
            <el-form-item label="性 别" prop="sex">
              <el-radio v-model="registerForm.sex" label="1" >男</el-radio>
              <el-radio v-model="registerForm.sex" label="2">女</el-radio>
            </el-form-item>

            <el-form-item label="手机号" prop="phone">
              <el-input v-model="registerForm.phone" clearable></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content bg-purple-light">

            <!--社区-->
            <el-form-item label="社 区" prop="community">
              <el-select v-model="registerForm.community" clearable placeholder="">
                <el-option
                    v-for="item in communities"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="邮 箱" prop="email">
              <el-input v-model="registerForm.email" clearable></el-input>
            </el-form-item>



            <el-form-item label="职 业" prop="career">
              <el-input v-model="registerForm.career" clearable></el-input>
            </el-form-item>

            <el-form-item label="政治面貌" prop="political">
              <el-input v-model="registerForm.political" clearable></el-input>
            </el-form-item>

            <el-form-item label="出生日期" prop="birthday">
              <div class="block">
                <span class="demonstration"></span>
                <el-date-picker
                    v-model="registerForm.birthday"
                    type="date"
                    prefix-icon="none">
                </el-date-picker>
              </div>
            </el-form-item>
          </div>
        </el-col>
      </el-row>


      <!--注册按钮-->
      <el-form-item style="width:100%;">
        <el-button
            :loading="loading"
            size="medium"
            type="primary"
            @click="handleRegister('registerForm')"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
        <el-button
          type="info"
          @click="reload('registerForm')">
          重置
        </el-button>
        <div style="float: right;">
          <router-link class="link-type" :to="'/login'">使用已有账户登录</router-link>
        </div>
      </el-form-item>



    </el-form>

    <!--  底部  -->
    <div class="el-register-footer">
      <span>Copyright © 2022 Toom All Rights Reserved.</span>
    </div>

  </div>
</template>

<script>
export default {
  data() {

    return {
      /*初始化*/
      registerForm: {
        username: '',
        password: '',
        community: '',
        name: '',
        sex: '',
        political: '',
        email: '',
        phone: '',
        career: '',
        birthday: ''
      },

      /*动态社区选择*/
      communities: [{
        value: '选项1',
        label: '九龙社区'
      }, {
        value: '选项2',
        label: '龙城社区'
      }, {
        value: '选项3',
        label: '长沙社区'
      }, {
        value: '选项4',
        label: '海珠社区'
      }]
      ,

      /*验证校验*/
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' }
        ],
        sex: [
          { required: true }
        ]
      },

      loading: false
    };


  },
  methods: {
    /*注册按钮*/
    handleRegister(forName){
      this.$refs.registerForm.validate(valid => {
        if (valid){
          this.loading = true;
          console.log(this.registerForm)
          axios.post('http://localhost:8888/register', this.registerForm).then((res) =>{
            console.log(res)
            if (res.status == 200){
              this.$message("注册成功！")
            }
          })
        }else {
          console.log("error register!")
          return false;
        }
      })
    },
    /*重置*/
    reload(forName){
      this.$refs[forName].resetFields();
    }
  }
}
</script>

<style scoped>
#app{
  background-color: #42b983;
}
.register{
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
.register-form {
  border-radius: 6px;
  background: #ffffff;
  width: 700px;
  padding: 25px 25px 5px 25px;
}
.el-input,.el-select{
  height: 38px;
  width: 100%;
}

.el-register-footer {
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
/*布局*/
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
