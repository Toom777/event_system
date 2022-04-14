<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动" prop="activityId">
        <el-input
            v-model="queryParams.activityName"
            placeholder="请输入活动名称"
            clearable
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户" prop="userName">
        <el-input
            v-model="queryParams.userName"
            placeholder="请输入用户名字"
            clearable
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-s-check"
            size="mini"
            @click="handleAdd"
        >确认</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="el-icon-check"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
        >审核</el-button>
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
    </el-row>

    <el-table v-loading="loading" :data="confirmationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="用户名字" align="center" prop="userName" />
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
              @click="handleAudit(scope.row)"

          >审核</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="activityName">
          <el-input disabled="disabled" v-model="form.activityName" placeholder="" />
        </el-form-item>
        <el-form-item label="用户名字" prop="userName">
          <el-input disabled="disabled" v-model="form.userName" placeholder="" />
        </el-form-item>

        <el-form-item label="报名时间" prop="enrollmentTime">
          <el-date-picker clearable size="small"
                          style="width: 100%"
                          v-model="form.enrollmentTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核结果" prop="result">
          <el-select v-model="form.result" placeholder="请选择"  :disabled="mydisabled">
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
        <el-form-item label="签到时间" prop="checkIn">
          <el-date-picker clearable size="small"
                          v-model="form.checkIn"
                          style="width: 100%"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择签到时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="签退时间" prop="checkOut">
          <el-date-picker clearable size="small"
                          v-model="form.checkOut"
                          style="width: 100%"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择签退时间">
          </el-date-picker>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listConfirmation, getConfirmation, delConfirmation, addConfirmation, updateConfirmation } from "@/api/confirmation";
import {getUser} from "@/api/user";
export default {
  name: "confirmation",
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
        activityName: '',
        userName: '',
        result: ''
      },
      // 表单参数
      form: {},

      /*不可使用*/
      mydisabled: true,

      userForm: {},
      // 表单校验
      rules: {
        activityId: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
      },
      resultList: [{
        result: '0',
        resultName: '待审核'
      }, {
        result: '1',
        resultName: '通过'
      }, {
        result: '2',
        resultName: '不通过'
      }]
    };

  },
  methods: {
    /*审核时效*/
    disabledStatus() {
      if (this.form.checkIn != null || this.form.result === "2") {
        this.mydisabled = true;
      } else {
        this.mydisabled = false;
      }
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
    /*活动ID转换*/
    formatActivity() {

    },
    /*活动ID转换*/
    formatUser() {

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
        confirmationId: null,
        activityId: null,
        userId: null,
        userName: null,
        activityName: null,
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
      this.reset();
      const confirmationId = row.confirmationId || this.ids;
      const userId = row.userId;
      getConfirmation(confirmationId).then(response => {
        this.form = response.data.data;
        this.disabledStatus();
        this.open = true;
      });
    },
    /** 活动结束确认按钮操作 */
    handleAdd() {
      this.reset();

    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const confirmationId = row.confirmationId || this.ids
      getConfirmation(confirmationId).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改活动确认";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.confirmationId != null) {
            updateConfirmation(this.form).then(response => {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.open = false;
              this.getList();
            });
          } else {
            addConfirmation(this.form).then(response => {
              this.$message({
                message: '新增成功！',
                type: 'success'
              });
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const confirmationIds = row.confirmationId || this.ids;
      this.$confirm('是否删除选中？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delConfirmation(confirmationIds);
      }).then(() => {
        this.getList();
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      }).catch(() => {});
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
</style>
