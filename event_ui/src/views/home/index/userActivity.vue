<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动ID" prop="activityId">
        <el-input
            v-model="queryParams.activityId"
            placeholder="请输入活动ID"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="审核结果" prop="result">
        <el-input
            v-model="queryParams.result"
            placeholder="请输入审核结果"
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


    <el-table v-loading="loading" :data="confirmationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="activityId" />
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
              @click="handleDelete(scope.row)"
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
        <li class="list-group-item"><table><tbody><tr><td>活动地点</td><td><span>{{form.activitiyLocation}}</span></td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>联系人&emsp;</td><td>{{form.contactName}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>联系电话</td><td>{{form.contactPhone}}</td></tr></tbody></table></li>
        <li class="list-group-item"><table><tbody><tr><td>活动内容</td><td>{{form.activityContent}}</td></tr></tbody></table></li>
      </ul>
    </el-dialog>
  </div>
</template>

<script>
import {
  addConfirmation,
  delConfirmation,
  getConfirmation,
  listConfirmation,
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
        activityId: '',
        result: ''
      },
      // 表单参数
      form: {},

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
    /*查看*/
    handleSearch(row){
      this.reset();
      const collectionId = row.collectionId || this.ids;
      const activityId = row.activityId;
      getActivity(activityId).then(response => {
        console.log("115151", response);
        this.form = response.data.data;
        this.open = true;
        this.title = "修改活动收藏";
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
      listConfirmation(this.queryParams).then(response => {
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
        confirmationtId: null,
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
      this.ids = selection.map(item => item.confirmationtId)
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
    /** 删除按钮操作 */
    handleDelete(row) {

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
