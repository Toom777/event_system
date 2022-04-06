<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="box-card" style="text-align: left">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <i class="el-icon-user-solid"></i>姓名
                <div class="pull-right">{{ user.name }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-phone"></i>手机号码
                <div class="pull-right">{{ user.phone }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-star-on"></i>星级
                <div class="pull-right">{{ user.sating }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-s-opportunity"></i>服务时长
                <div class="pull-right">{{ user.hours }}小时</div>
              </li>
            </ul>
          </div>
        </el-card>

      </el-col>
      <el-col :span="18" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>基本资料</span>
          </div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="基本资料" name="userinfo">
              <el-form ref="form" :model="user" :rules="rules" label-width="80px">
                <el-form-item label="名字" prop="name">
                  <el-input v-model="user.name" maxlength="30" />
                </el-form-item>

                <el-form-item label="归属社区" prop="communityId">
                  <el-select
                      v-model="user.communityId"
                      clearable
                      placeholder="选择社区"
                      :loading="loading"
                      class="myItem"
                  >
                    <el-option
                        v-for="item in communityList"
                        :key="item.communityId"
                        :label="item.communityName"
                        :value="item.communityId"
                    >
                      {{item.communityName}}
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="手机号码" prop="phone">
                  <el-input v-model="user.phone" maxlength="11" />
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                  <el-input v-model="user.email" maxlength="50" />
                </el-form-item>
                <el-form-item label="性别">
                  <el-radio-group v-model="user.sex" style="text-align: left" class="myItem">
                    <el-radio label="0">男</el-radio>
                    <el-radio label="1">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="职业" prop="career">
                  <el-input v-model="user.career" placeholder="请输入职业" />
                </el-form-item>
                <el-form-item label="政治面貌" prop="politicalStatus">
                  <el-input v-model="user.politicalStatus" placeholder="请输入政治面貌" />
                </el-form-item>
                <el-form-item label="出生日期" prop="birthday">
                  <el-date-picker clearable size="small"
                                  v-model="user.birthday"
                                  type="date"
                                  value-format="yyyy-MM-dd"
                                  placeholder="选择出生日期"
                                  style="width: 100%;">
                  </el-date-picker>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" size="mini" @click="submit">保存</el-button>
                  <el-button type="danger" size="mini" @click="reset">恢复</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="">
              <el-form ref="form" :model="user" :rules="rules" label-width="80px">
                <el-form-item label="新密码" prop="newPassword">
                  <el-input v-model="user.newPassword" placeholder="请输入新密码" type="password" show-password/>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                  <el-input v-model="user.confirmPassword" placeholder="请确认密码" type="password" show-password/>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" size="mini" @click="submit">保存</el-button>
                  <el-button type="danger" size="mini" @click="reset">重置</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {communityIdAndName, getUser, updateUser} from '@/api/user';
import confirmation from "@/views/home/activity/confirmation";
export default {
  name: "Information",
  data() {
    const equalToPassword = (rule, value, callback) => {
          if (this.user.newPassword !== value) {
            callback(new Error("两次输入的密码不一致"));
          } else {
            callback();
          }
    };
    return{
      loading: true,
      activeTab: "userinfo",
      user: {},
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名字不能为空", trigger: "blur" }
        ],
        phone: [
          {  message: "手机号码不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        newPassword: [
          { message: "新密码不能为空", trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ],
        confirmPassword: [
          {  message: "确认密码不能为空", trigger: "blur" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      },
      /*社区列表*/
      communityList: {
        communityId: '',
        communityName: ''
      },
    };

  },
  methods: {
    getCommunityList() {
      communityIdAndName().then(res => {
        this.communityList = res.data.data;
        this.loading = false;
        //console.log(this.communityList)
      })
    },
    getUser() {
      getUser(this.$route.query.userId).then(res => {
        this.user = res.data.data;
        console.log(res);
      })
    },
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.user.confirmPassword) {
            this.user.password = this.user.confirmPassword;
          }
          updateUser(this.user).then(res => {
            this.$message({
              message: '修改成功',
              type: 'success'
            });
          });
        }
      });
    },
    reset() {
      this.getUser();
    }
  },
  created() {
    this.getCommunityList();
    this.getUser();
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
.clearfix:after {
   visibility: hidden;
   display: block;
   font-size: 0;
   content: " ";
   clear: both;
   height: 0;
}
.text-center {
  text-align: center
}
.list-group {
  padding-left: 0px;
  list-style: none;
}
.list-group-item {
  border-bottom: 1px solid #e7eaec;
  margin-bottom: -1px;
  padding: 11px 0px;
  font-size: 13px;
}
.list-group-striped > .list-group-item {
  border-left: 0;
  border-right: 0;
  border-radius: 0;
  padding-left: 0;
  padding-right: 0;
}

.pull-right {
  float: right !important;
}
.el-card__header {
  padding: 14px 15px 7px;
  min-height: 40px;
}
.card-box {
  padding-right: 15px;
  padding-left: 15px;
  margin-bottom: 10px;
}
.myItem{
  width: 100%;
}
</style>
