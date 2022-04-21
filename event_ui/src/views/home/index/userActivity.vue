<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="activityName">
        <el-input
            v-model="queryParams.activityName"
            placeholder="请输入活动名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="审核结果" prop="result">
        <el-select v-model="queryParams.result" placeholder="请选择">
          <el-option
              v-for="item in resultList"
              :key="item.result"
              :label="item.resultName"
              :value="item.result"
              @keyup.enter.native="handleQuery"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetForm('queryForm')">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="confirmationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="activityId" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="报名时间" align="center" prop="enrollmentTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.enrollmentTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核结果" align="center" prop="result" >
        <template slot-scope="scope">
          <el-tag :type="resultType(scope.row.result)">{{ resultName(scope.row.result) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="签到时间" align="center" prop="checkIn" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.checkIn }}</span>
        </template>
      </el-table-column>
      <el-table-column label="签退时间" align="center" prop="checkOut" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.checkOut }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleSearch(scope.row)"

          >查看</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleCheck(scope.row)"
          >签到</el-button>
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

    <!-- 添加或修改活动确认对话框 -->
    <el-dialog title="活动详情" :visible.sync="open" width="500px" append-to-body>
      <ul class="list-group">
        <h2>{{form.activityName}}</h2>
        <li class="list-group-item"><table><tbody><tr><td>活动编号</td><td>{{form.activityId}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>活动标签</td><td>{{form.activityTag}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>发布组织</td><td>{{form.contactName}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>活动时间</td><td>{{form.beginTime}} ~ {{form.endTime}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>报名人数</td><td>{{form.confirmCount}} / {{form.allowCount}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>截止报名</td><td>{{form.deadline}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>活动地点</td><td><span>{{form.activityLocation}}</span></td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>联系人&emsp;</td><td>{{form.contactName}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>联系电话</td><td>{{form.contactPhone}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>活动内容</td><td>{{form.activityContent}}</td></tr></tbody></table></li>
      </ul>
    </el-dialog>
  </div>
</template>

<script>
import {
  addConfirmation, checkActivity,
  delConfirmation,
  getConfirmation,
  listConfirmation, listUser,
  updateConfirmation
} from "@/api/confirmation";
import {getActivity} from "@/api/activity";

export default {
  name: "userActivity",
  data() {
    return{
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
      // 活动确认表格数据
      confirmationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageCurrent: 1,
        pageSize: 10,
        userId: this.$route.query.userId,
        activityName: '',
        result: ''
      },
      // 表单参数
      form: {},

      resultList: [{
        result: '0',
        resultName: '待审核'
      }, {
        result: '1',
        resultName: '通过'
      }, {
        result: '2',
        resultName: '不通过'
      }],

      /*签到表单参数*/
      checkForm: {},
      // 表单校验
      rules: {
        activityId: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
      }

    }
  },
  methods: {
    /*日期格式转化*/
    dateFotmat(time){
      var date=new Date(time);
      var year=date.getFullYear();
      /* 在日期格式中，月份是从0开始的，因此要加0
       * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
       * */
      var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
      var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
      var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
      var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
      var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    /*查看*/
    handleSearch(row){
      this.reset();
      const activityId = row.activityId;
      getActivity(activityId).then(response => {
        console.log("115151", response);
        this.form = response.data.data;
        this.open = true;
      });
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.queryParams.pageCurrent = currentPage;
      this.getList()
    },
    /*结果变化*/
    resultType(item) {
      if (item == 0) {
        return "";
      } else if (item == 1) {
        return "success";
      } else {
        return "info";
      }

    },
    resultName(item){
      if (item == 0) {
        return "待审核";
      } else if (item == 1) {
        return "通过";
      } else {
        return "不通过";
      }
    },
    /** 查询活动确认列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.confirmationList = response.data.rows;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        confirmationId: null,
        activityId: null,
        userId: null,
        enrollmentTime: null,
        result: '',
        checkIn: null,
        checkOut: null,
        createBy: '',
        createTime: null,
        updateBy: '',
        updateTime: null,
        remark: ''
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageCurrent = 1;
      this.getList();
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
      this.ids = selection.map(item => item.confirmationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /*审核*/
    handleAudit(row) {

    },

    /** 修改按钮操作 */
    handleUpdate(row) {

    },
    /** 提交按钮 */
    submitForm() {

    },
    /** 签到按钮操作 */
    handleCheck(row) {
      if (row.result == 0){
        this.$message({
          message: '活动待审核，不能签到！',
          type: 'warning'
        });
      } else if (row.result == 2) {
        this.$message({
          message: '活动不通过，不能签到！',
          type: 'error'
        });
      } else {

        getActivity(row.activityId).then(res => {
          this.form = res.data.data;

          if (this.form.beginTime < this.dateFotmat(new Date()) && this.form.endTime > this.dateFotmat(new Date())) {
            console.log("进入第二层：", row);
            if (row.checkIn != null && row.checkOut != null){
              this.$message({
                message: '不能反复签到！',
                type: 'warning'
              });
            } else {
              this.checkForm.checkTime = this.dateFotmat(new Date());
              this.checkForm.confirmationId = row.confirmationId;
              checkActivity(this.checkForm).then(() => {
                this.$message({
                  message: '签到成功！当前签到时间为：' + this.checkForm.checkTime,
                  type: 'success'
                });
                this.getList();
              }).catch(() => {
                console.log("失败了");
              });
            }
          } else if (this.form.beginTime > this.dateFotmat(new Date())) {
            this.$message({
              message: '还未到签到时间！',
              type: 'error'
            });
          } else if (this.form.beginTime < this.dateFotmat(new Date())){
            this.$message({
              message: '活动已结束！',
              type: 'error'
            });
          }
        });

      }
      this.getList();
    },
  },
  created() {
    this.getList();
  }

}
</script>

<style scoped>
.app-container{
  padding: 20px;
}
.list-group {
  padding-left: 0px;
  list-style: none;
}
</style>
