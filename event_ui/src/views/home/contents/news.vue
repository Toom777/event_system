<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="资讯类型" prop="activityTag">
        <el-select
            v-model="queryParams.newsType"
            clearable
            allow-create
            filterable
            placeholder="请选择资讯类型"
        >
          <el-option
              v-for="item in typeList"
              :key="item.typeNum"
              :label="item.typeName"
              :value="item.typeNum"
              @keyup.enter.native="handleQuery"
          >
            {{item.typeName}}
          </el-option>
        </el-select>

      </el-form-item>
      <el-form-item label="资讯标题" prop="newsTitle">
        <el-input
            v-model="queryParams.newsTitle"
            placeholder="请输入资讯标题"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetForm">重置</el-button>
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
    </el-row>

    <el-table v-loading="loading" :data="newsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="发布人" align="center" prop="editor" />
      <el-table-column label="资讯标题" align="center" prop="newsTitle" />
      <el-table-column label="资讯类型" align="center" prop="newsType" />
      <el-table-column label="资讯状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == '0' ? 'success': 'danger'">{{scope.row.status == '0' ? '正常' : '禁用'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
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


    <!-- 添加或修改资讯 弹出框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发布人" prop="editor">
          <el-input v-model="form.editor" placeholder="请输入发布人名字" />
        </el-form-item>
        <el-form-item label="资讯标题" prop="newsTitle">
          <el-input v-model="form.newsTitle" placeholder="请输入资讯标题" />
        </el-form-item>
        <el-form-item label="资讯内容">
          <quill-editor v-model="form.newsContent" :min-height="192"></quill-editor>
        </el-form-item>
<!--        <el-form-item label="类型" prop="newsType">
          <el-input v-model="form.newsType" placeholder="" />
        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listNews, getNews, delNews, addNews, updateNews, listNewsType } from "@/api/news";
import QuillEditor from "@/components/quillEditor/index";

export default {
  name: "News",
  components: {
    QuillEditor
  },
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
      // 资讯表格数据
      newsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageCurrent: 1,
        pageSize: 5,
        newsTitle: '',
        newsType: '',
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        newsTitle: [
          { required: true, message: "资讯标题不能为空", trigger: "blur" }
        ],
      },
      /*资讯类型列表*/
      typeList:[
          {typeNum: '', typeName: ''}
      ],
    };
  },
  methods: {
    /*获取资讯类型*/
    getNewsType() {
      listNewsType().then(res => {
        this.typeList = res.data.data;
      });
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.queryParams.pageCurrent = currentPage;
      this.getList()
    },
    /** 查询资讯列表 */
    getList() {
      this.loading = true;
      listNews(this.queryParams).then(response => {
        this.newsList = response.data.rows;
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
        newsId: null,
        editor: null,
        newsTitle: null,
        newsType: null,
        newsContent: null,
        picture: null,
        status: "0",
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
      this.ids = selection.map(item => item.newsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资讯";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const newsId = row.newsId || this.ids
      getNews(newsId).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改资讯";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.newsId != null) {
            updateNews(this.form).then(response => {
              this.$message({
                message: '修改成功！',
                type: 'success'
              });
              this.open = false;
              this.getNewsType();
              this.getList();
            });
          } else {
            addNews(this.form).then(response => {
              this.$message({
                message: '添加成功！',
                type: 'success'
              });
              this.open = false;
              this.getNewsType();
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const newsIds = row.newsId || this.ids;
      this.$confirm('是否确认删除资讯编号为"' + newsIds + '"的数据项？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delNews(newsIds);
      }).then(() => {
        this.getNewsType();
        this.getList();
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      }).catch(() => {});
    }
  },
  created() {
    this.getList();
    this.getNewsType();
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>
