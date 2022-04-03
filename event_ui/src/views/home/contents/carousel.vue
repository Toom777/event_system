<template>
  <div class="app-container">
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
            @click=""
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click=""
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="pictureList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图片ID" align="center" prop="pictureId" />
      <el-table-column label="图片名称" align="center" prop="pictureName" />
      <el-table-column label="图片内容" align="center" prop="pictureContent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click=""
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click=""
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 添加或修改图片 弹出框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="formData"  label-width="80px" @keyup.enter.native="submitForm">


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Carousel",
  data() {
    return {
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 是否显示弹出层
      open: false,
      // 弹出层标题
      title: "",
      // 图片列表
      pictureList: [],
      //弹出框表单参数
      formData: {},

    };
  },
  created() {

  },
  methods: {
    //弹出框 取消按钮操作
    cancel() {
      this.open = false;
    },
    // 弹出框 确定按钮操作
    submitForm() {

    },
    // 新增按钮操作
    handleAdd(){
      this.open = true;
      this.title = "添加图片";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.communityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
};
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>
