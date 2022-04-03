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
              <el-radio v-model="registerForm.sex" label="0">男</el-radio>
              <el-radio v-model="registerForm.sex" label="1">女</el-radio>
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
              <el-select
                  v-model="registerForm.community"
                  clearable
                  placeholder=""
                  :loading="loading"
              >
                <el-option
                    v-for="item in communityNameList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">{{item.label}}
                </el-option>
              </el-select>
            </el-form-item>

            <!--邮箱-->
            <el-form-item label="邮 箱" prop="email">
              <el-input v-model="registerForm.email" clearable></el-input>
            </el-form-item>

            <!--职业-->
            <el-form-item label="职 业" prop="career">
              <el-input v-model="registerForm.career" clearable></el-input>
            </el-form-item>

            <!--政治面貌-->
            <el-form-item label="政治面貌" prop="politicalStatus">
              <el-input v-model="registerForm.politicalStatus" clearable></el-input>
            </el-form-item>

            <!--出生日期-->
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
        communityId: '',
        community: '',
        name: '',
        sex: '',
        politicalStatus: '',
        email: '',
        phone: '',
        career: '',
        birthday: ''
      },

      /*动态社区选择*/
      communityNameList: [],

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
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { min: 11, max: 11, message: '请输入11位手机号码', trigger: 'blur' },
          {
            pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
            message: '请输入正确的手机号码'
          }
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
      this.$refs[forName].validate(valid => {
        if (valid){
          this.loading = true;

          /*社区数据转化*/
          this.axios.get('/community/selectByName/' + this.registerForm.community).then((res) =>{
            this.registerForm.communityId = parseInt(res.data.data)
            console.log(typeof this.registerForm.communityId)
          }).then((res) =>{
            /*传入后台*/
            this.axios.post('/user/insert', this.registerForm).then((res) =>{
              console.log(res)
              if (res.status == 200){
                this.$message("注册成功！");
                this.$router.push("/login");
              }
            }).catch(() => {
              this.$message("账号已经存在！");
              this.loading = false;
            })
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
    },

    /*搜索数据库中社区名称并注入*/
    searchList(){
      this.axios('/community/selectAllName').then((res) =>{
        /*判断是否有数据*/
        if (res.data.data != null){
          /*将后台传的数据遍历到communityNameList*/
          for (var x = 0; x < res.data.data.length; x++){
            let obj = {}
            //value 同 label一样 为了后续方便查id
            obj.value = res.data.data[x]
            obj.label = res.data.data[x]
            this.communityNameList.push(obj)
          }
        }else {
          console.log("获取数据失败！")
        }
      })
    }

  },
  created() {
    this.searchList()
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
