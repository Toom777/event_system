<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="searchForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社区名称" prop="communityId">
        <el-select
            v-model="queryParams.communityId"
            clearable
            placeholder="选择社区"
            :loading="loading"
        >
          <el-option
              v-for="item in communityList"
              :key="item.communityId"
              :label="item.communityName"
              :value="item.communityId"
              @keyup.enter.native="handleQuery"
          >
            {{item.communityName}}
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="用户账号" prop="username">
        <el-input
            v-model="queryParams.username"
            placeholder="输入用户账号"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="真实姓名" prop="name">
        <el-input
            v-model="queryParams.name"
            placeholder="输入用户姓名"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetForm('searchForm')">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
        >导出</el-button>
      </el-col>-->

    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" width="70" align="center" prop="userId" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="所在社区" align="center" prop="communityId" :formatter="communityIdFormatter"/>
      <el-table-column label="用户账号" align="center" prop="username" />
      <el-table-column label="用户类型" width="90" align="center" prop="userType" :formatter="userTypeFormatter"/>
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="手机号码" align="center" prop="phone" />
      <el-table-column label="性别" align="center" prop="sex" :formatter="sexFormatter"/>
      <el-table-column label="政治面貌" align="center" prop="politicalStatus" />
      <el-table-column label="职业" align="center" prop="career" />
      <el-table-column label="出生日期" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.birthday }}</span>
        </template>
      </el-table-column>
      <el-table-column label="积分" align="center" prop="points" />
      <el-table-column label="星级" align="center" prop="sating" />
      <el-table-column label="服务时长" align="center" prop="hours" />
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == '0' ? 'success': 'danger'">{{scope.row.status == '0' ? '正常' : '禁用'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="最后登录时间" align="center" prop="loginDate" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.loginDate }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-refresh"
              @click="handleReloadPassword(scope.row)"
          >还原</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="queryParams.pageSize"
        :current-page="queryParams.pageCurrent"
        @current-change="getPage"
    >
    </el-pagination>


    <!-- 添加用户 弹出层 -->
    <el-dialog :title="title" :visible.sync="dialogOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" @keyup.enter.native="submitForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号" prop="username">
              <el-input v-model="form.username" placeholder="请输入账号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属社区" prop="communityId">
              <el-select
                  v-model="form.communityId"
                  clearable
                  placeholder="选择社区"
                  :loading="loading"
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
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="form.sex" @change="radioChange">
                <el-radio label="0">男</el-radio>
                <el-radio label="1">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入用户邮箱" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker clearable size="small"
                              v-model="form.birthday"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择出生日期"
                              style="width: 100%;">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-tooltip :content="'Switch value: ' + form.status" placement="top">
                <el-switch
                    v-model="form.status"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                    active-value="0"
                    inactive-value="1">
                </el-switch>
              </el-tooltip>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!--修改用户弹出层-->
    <el-dialog :title="title" :visible.sync="updateOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" @keyup.enter.native="submitForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="账号" prop="username">
              <el-input v-model="form.username" placeholder="请输入账号" :disabled="true"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属社区" prop="communityId">
              <el-select
                  v-model="form.communityId"
                  clearable
                  placeholder="选择社区"
                  :loading="loading"
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
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="form.sex" @change="radioChange">
                <el-radio label="0">男</el-radio>
                <el-radio label="1">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入用户邮箱" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker clearable size="small"
                              v-model="form.birthday"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择出生日期"
                              style="width: 100%;">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户类型" prop="userType">
              <el-select v-model="form.userType">
                <el-option
                    v-for="item in userTypeList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="政治面貌" prop="politicalStatus">
              <el-input v-model="form.politicalStatus" placeholder="请输入政治面貌" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职业" prop="career">
              <el-input v-model="form.career" placeholder="请输入职业" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="积分" prop="points">
              <el-input-number v-model="form.points" :min="0" size="medium" style="width: 100%;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="星级" prop="sating">
              <el-input v-model="form.sating" placeholder="" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="服务时长" prop="hours">
              <el-input-number v-model="form.hours" :min="0" size="medium" style="width: 100%;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-tooltip :content="'Switch value: ' + form.status" placement="top">
                <el-switch
                    v-model="form.status"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                    active-value="0"
                    inactive-value="1">
                </el-switch>
              </el-tooltip>
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser, communityIdAndName, listUserByConditions } from "@/api/user";

export default {
  name: "User",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      dialogOpen: false,
      updateOpen: false,
      // 查询参数
      queryParams: {
        pageCurrent: 1,
        pageSize: 5,
        communityId: '',
        username: '',
        name: '',
      },
      /*用户类型列表 此处直接写死了*/
      userTypeList: [
        { label: '普通用户', value: '03'},
        { label: '社区管理员', value: '02'},
        { label: '超级管理员', value: '01'}
      ],
      /*社区列表*/
      communityList: {
        communityId: '',
        communityName: ''
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户账号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true}
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getCommunityList();
  },
  methods: {
    /*监听我的radio*/
    radioChange(row){
      console.log(row)
    },
    /*性别格式转换*/
    sexFormatter(row){
      const sex = row.sex
      if (sex == 0){
        return '男'
      }else if (sex == 1){
        return '女'
      }
    },
    /*用户类型转换*/
    userTypeFormatter(row){
      const type = row.userType
      if (type == "01"){
        return "超级管理员"
      }else if (type == '02'){
        return "社区管理员"
      }else if (type == '03'){
        return "普通用户"
      }
    },
    /*社区Id转换*/
    communityIdFormatter(row){
      //console.log(this.communityList)
      const id = row.communityId
      if (id == null){
        return ''
      }
      for (let i = 0; i < this.communityList.length; i++){
        if (id == this.communityList[i].communityId){
          //console.log(id, this.communityList[i].communityName)
          return this.communityList[i].communityName
        }
      }
    },

    /*条件查询*/
    searchUser() {
      this.loading = true;
      listUserByConditions(this.queryParams).then(res => {
        this.userList = res.data.rows;
        this.total = res.data.total;
        this.loading = false;
      })
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.queryParams.pageCurrent = currentPage;
      this.getList()
    },
    /*获取社区列表*/
    getCommunityList() {
      communityIdAndName().then(res => {
        this.communityList = res.data.data
        //console.log(this.communityList)
      })
    },
    /** 获取用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(res => {
        this.userList = res.data.rows;
        this.total = res.data.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.updateOpen = false;
      this.dialogOpen = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        communityId: undefined,
        username: undefined,
        name: undefined,
        password: undefined,
        userType: '03',
        email: undefined,
        phone: undefined,
        sex: undefined,
        politicalStatus: "",
        career: undefined,
        birthday: undefined,
        points: undefined,
        sating: undefined,
        hours: undefined,
        avatar: '',
        status: "0",
        delFlag: '0',
        loginIp: undefined,
        loginDate: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined
      };
      this.resetForm("form");
    },
    //搜索用户
    search() {
      listUser(this.queryParams).then(res => {
        this.userList = res.data.rows;
        this.total = res.data.total;
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageCurrent = 1;
      this.searchUser();
    },
    /** 重置按钮操作 */
    resetForm(formName) {
      if (this.$refs[formName]){
        this.$nextTick(() => {
          this.$refs[formName].resetFields();
        })
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.dialogOpen = true;
      this.title = "添加用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getUser(userId).then(res => {
        this.form = res.data.data;
        this.updateOpen = true;
        this.title = "修改用户";
      });
    },
    /*重置密码*/
    handleReloadPassword(row) {
      this.reset();
      const userId = row.userId || this.ids
      getUser(userId).then(res => {
        this.form = res.data.data;
        this.form.password = "123456"
        if (this.form.userId != null) {
          updateUser(this.form).then(res => {
            this.$message({
              message: '密码重置成功！新密码为：123456',
              type: 'success'
            });
            this.getList();
          });
        }
      });

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != null) {
            updateUser(this.form).then(res => {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.updateOpen = false;
              this.getList();
            });
          } else {
            this.form.password = "123456";
            addUser(this.form).then(res => {
              this.$message({
                message: '用户创建成功，默认密码：123456',
                type: 'success'
              });
              this.dialogOpen = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$confirm('是否删除该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>
.app-container {
  padding: 20px;
}

</style>
