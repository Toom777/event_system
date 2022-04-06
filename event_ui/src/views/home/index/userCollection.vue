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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetForm('queryForm')">重置</el-button>
      </el-form-item>
    </el-form>


    <el-row :gutter="10" class="mb8">
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

    <el-table v-loading="loading" :data="collectionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
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

    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
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
import { listCollection, getCollection, delCollection, addCollection, updateCollection } from "@/api/collection";
import {getActivity} from "@/api/activity";
export default {
  name: "UserCollection",
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
      // 活动收藏表格数据
      collectionList: [],
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        activityId: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  methods: {
    /*获取当前点击页*/
    getPage(currentPage){
      this.queryParams.pageCurrent = currentPage;
      this.getList()
    },
    /*查看*/
    handleSearch(row){
      const collectionId = row.collectionId;
      const activityId = row.activityId;
      console.log(activityId);
      this.$router.push({
        path: '/activityContent',
        query: {
          activityId: activityId
        }
      });
    },
    /** 重置按钮操作 */
    resetForm(formName) {
      if (this.$refs[formName]){
        this.$nextTick(() => {
          this.$refs[formName].resetFields();
        })
      }
    },
    /** 查询活动收藏列表 */
    getList() {
      this.loading = true;
      listCollection(this.queryParams).then(response => {
        this.collectionList = response.data.rows;
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
        collectionId: null,
        userId: null,
        activityId: null,
        activityName: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageCurrent = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.collectionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const collectionIds = row.collectionId || this.ids;
      this.$confirm('是否删除选中活动？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delCollection(collectionIds);
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
.list-group {
  padding-left: 0px;
  list-style: none;
}
</style>
