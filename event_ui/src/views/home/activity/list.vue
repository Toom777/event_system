<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="searchForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="activityName">
        <el-input
            v-model="queryParams.activityName"
            placeholder="请输入活动名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="活动标签" prop="activityTag">
        <el-select
            v-model="queryParams.activityTag"
            clearable
            allow-create
            filterable
            placeholder="请选择活动标签"
        >
          <el-option
              v-for="item in tagList"
              :key="item.tagNum"
              :label="item.tagName"
              :value="item.tagNum"
              @keyup.enter.native="handleQuery"
          >
            {{item.tagName}}
          </el-option>
        </el-select>

      </el-form-item>
      <el-form-item label="活动时间">
        <el-date-picker
            @change="dataTimeChange"
            ref="searchForm"
            v-model="dataTime"
            type="datetimerange"
            value-format="yyyy-MM-dd HH:mm:ss"
            :picker-options="pickerOptions"
            range-separator="-"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            align="right">
        </el-date-picker>
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

    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="活动ID" align="center" prop="activityId" />
      <el-table-column label="发布人ID" align="center" prop="userId" />-->
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="活动标签" align="center" prop="activityTag" >
        <template slot-scope="scope">
          <el-tag>{{scope.row.activityTag}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="可报名人数" align="center" prop="allowCount" />
      <el-table-column label="已报名人数" align="center" prop="confirmCount" />
      <el-table-column label="报名截止时间" align="center" prop="deadline" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.deadline }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="联系人" align="center" prop="contactName" />
      <el-table-column label="联系电话" align="center" prop="contactPhone" />-->
      <el-table-column label="活动积分" align="center" prop="activityPoint" />
<!--      <el-table-column label="图片" align="center" prop="picture" />-->
      <el-table-column label="活动开始时间" align="center" prop="beginTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.beginTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.endTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="statusType(scope.row.status)">{{ statusName(scope.row.status) }}</el-tag>
        </template>
      </el-table-column>
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
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


    <!-- 添加或修改活动信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="650px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="活动名称" prop="activityName">
              <el-input v-model="form.activityName" placeholder="请输入活动名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="活动标签" prop="activityTag">
              <el-input v-model="form.activityTag" placeholder="请输入活动标签" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="活动地点" prop="activityLocation">
              <el-input v-model="form.activityLocation" placeholder="请输入活动地点" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报名要求" prop="requirement">
              <el-input v-model="form.requirement" placeholder="请输入报名要求" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="联系人" prop="contactName">
              <el-input v-model="form.contactName" placeholder="请输入联系人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="contactPhone">
              <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="beginTime">
              <el-date-picker
                  clearable size="small"
                  v-model="form.beginTime"
                  type="datetime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择活动开始时间"
                  style="width: 100%;"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker
                  clearable size="small"
                  v-model="form.endTime"
                  type="datetime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择活动结束时间"
                  style="width: 100%;"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="截止时间" prop="deadline">
              <el-date-picker
                  clearable size="small"
                  v-model="form.deadline"
                  type="datetime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择报名截止时间"
                  style="width: 100%;"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="活动人数" prop="allowCount">
              <el-input-number v-model="form.allowCount" :min="1"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="活动积分" prop="activityPoint">
              <el-input v-model="form.activityPoint" placeholder="请输入活动积分" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="图片" prop="picture">
              <el-input v-if="false"v-model="form.picture"></el-input>
              <el-upload
                  class="upload-demo"
                  ref="upload"
                  :data="form"
                  action="http://localhost:8888/upload/upload"
                  accept="image/jpeg,image/png,image/jpg"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"
                  :limit="1"
                  :on-success="handleUploadSuccess"
              >
                <el-button size="small" type="primary">选择图片</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png格式，且不超过500kb</div>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="活动内容">
          <el-input type="textarea" v-model="form.activityContent"></el-input>
        </el-form-item>

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
import { listActivity, getActivity, delActivity, addActivity, updateActivity, listActivityTag } from "@/api/activity";
import { uploadPicture } from "@/api/uploadFile";

export default {
  name: "ActivityList",
  data() {
    return {
      /*标签*/
      tagList: {
        tagNum: '',
        tagName: ''
      },
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
      // 活动信息表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageCurrent: 1,
        pageSize: 5,
        activityName: '',
        activityTag: '',
        beginTime: '',
        endTime: ''
      },
      /*查询的日期时间范围*/
      dataTime: ['', ''],
      /* 图片上传列表 */
      fileList: [],
      // 表单参数
      form: {},
      /*图片表单参数*/
      formData: '',
      // 表单校验
      rules: {
        activityName: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        beginTime : [
          {  required: true, message: "请选择时间", trigger: "change"}
        ],
        endTime : [
          {  required: true, message: "请选择时间", trigger: "change"}
        ]
      },
      /*日期快捷选择*/
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
    };
  },
  created() {
    this.getList();
    this.getActivityTags();
  },
  methods: {
    /*获取数据库中活动标签数据*/
    getActivityTags() {
      listActivityTag().then(res => {
        this.tagList = res.data.data;
        console.log(this.tagList);
      })
    },
    /*状态变化*/
    statusType(item) {
      if (item == 0) {
        return "success";
      } else if (item == 1) {
        return "";
      } else {
        return "info";
      }

    },
    statusName(item){
      if (item == 0) {
        return "未开始";
      } else if (item == 1) {
        return "进行中";
      } else {
        return "已结束";
      }
    },
    /*时间发生变化*/
    dataTimeChange() {
      console.log(this.dataTime);
      if (this.dataTime == null || this.dataTime.length == 0) {
        this.queryParams.beginTime = '';
        this.queryParams.endTime = '';
      } else {
        if (this.dataTime[0] != null && this.dataTime[0] != '') {
          this.queryParams.beginTime = this.dataTime[0];
        }
        if (this.dataTime[1] != null && this.dataTime[1] != '') {
          this.queryParams.endTime = this.dataTime[1];
        }
      }
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.queryParams.pageCurrent = currentPage;
      this.getList()
    },
    /** 查询活动信息列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.data.rows;
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
        activityId: undefined,
        userId: undefined,
        activityName: undefined,
        activityTag: undefined,
        activityContent: undefined,
        requirement: "无",
        allowCount: 1,
        confirmCount: undefined,
        deadline: undefined,
        contactName: undefined,
        contactPhone: undefined,
        activityLocation: undefined,
        activityPoint: undefined,
        picture: undefined,
        beginTime: undefined,
        endTime: undefined,
        status: "0",
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetForm(formName) {
      if (this.$refs[formName]){
        this.$nextTick(() => {
          if (formName == "searchForm") {
            this.dataTime =  ['', ''];
          }
          this.$refs[formName].resetFields();
        })
      }

    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.activityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const activityId = row.activityId || this.ids
      getActivity(activityId).then(response => {
        this.form = response.data.data;
        this.open = true;
        this.title = "修改活动信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.activityId != null) {
            updateActivity(this.form).then(response => {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.open = false;
              this.getActivityTags();
              this.getList();
            });
          } else {
            this.form.userId = this.$store.getters.getUser.userId;
            addActivity(this.form).then(response => {
              this.$message({
                message: '活动创建成功！',
                type: 'success'
              });
              this.open = false;
              this.getActivityTags();
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const activityIds = row.activityId || this.ids;
      this.$confirm('是否删除选中活动？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delActivity(activityIds);
      }).then(() => {
        this.getActivityTags();
        this.getList();
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      }).catch(() => {});
    },


    /*图片上传部分*/
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    }
    ,
    handleBeforeUpload(item) {

      this.formData.append("file", item.file);
      return false;
    },
    handleUploadSuccess(res, file) {
      if(res.url){
        this.form.picture = res.url;
      }
    },
    handleError(err, file, fileList) {
      console.log("err" + err);
      console.log("file" + file);
      console.log("fileList" + fileList);
    },
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-3);

      console.log("file" , file);
      console.log("list: " , JSON.stringify(fileList));



    },
    /*图片上传*/
    uploadPhoto(item) {
      //验证图片格式大小信息
      const isJPG = item.file.type == 'image/jpeg' || item.file.type == 'image/png';
      const isLt2M = item.file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG 或 PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!');
      }
      if (isJPG && isJPG) {
        let formData = new FormData();
        formData.append("file", item.file);
        uploadPicture(formData).then(res => {
/*          this.fileList.name = item.file.name;
          this.fileList.url = res.data.url;*/
          this.form.picture = res.data.url;
          console.log("2 " + this.form.picture);
        })
      }
    }


  }
};
</script>
<style>
.app-container {
  padding: 20px;
}

</style>
