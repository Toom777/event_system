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
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="carouselList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资讯ID" align="center" prop="newsId" />
      <el-table-column label="图片名称" align="center" prop="newsTitle" />
      <el-table-column label="图片路径" align="center" prop="picture" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!-- 添加或修改图片 弹出框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="formData"  label-width="80px" @keyup.enter.native="submitForm">

        <el-table :data="newsList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="资讯标题" align="center" prop="newsTitle" />
          <el-table-column label="资讯状态" align="center" prop="status" >
            <template slot-scope="scope">
              <el-tag :type="scope.row.status == '0' ? 'success': 'danger'">{{scope.row.status == '0' ? '正常' : '禁用'}}</el-tag>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            :page-size="newsParams.pageSize"
            :current-page="newsParams.pageCurrent"
            @current-change="getNewsPage"
        >
        </el-pagination>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {carouselNews, delCarousel, listCarouselNews, listNews} from "@/api/news";

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
      // 轮播图列表
      carouselList: [],
      //弹出框表单参数
      formData: {},

      // 资讯表格数据
      newsList: [],
      // 资讯总数
      newTotal: 0,

      // 资讯查询参数
      newsParams: {
        pageCurrent: 1,
        pageSize: 5,
        newsTitle: '',
        newsType: '',
      },

    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      listCarouselNews().then(res => {
        console.log(res);
        this.carouselList = res.data.data;
        this.loading = false;
      });
    },
    //弹出框 取消按钮操作
    cancel() {
      this.open = false;
    },
    // 弹出框 确定按钮操作
    submitForm() {
      const newsIds = this.ids;
      console.log(newsIds);
      carouselNews(newsIds).then(() => {
        this.open = false;
        this.$message({
          message: '添加成功',
          type: 'success'
        });
        this.getList();
      });

    },
    // 新增按钮操作
    handleAdd(){
      this.open = true;
      this.title = "添加图片";
      this.getNewsList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.newsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      console.log(this.ids);
    },
    /*获取资讯当前点击页*/
    getNewsPage(currentPage){
      this.newsParams.pageCurrent = currentPage;
      this.getNewsList()
    },

    /** 查询资讯列表 */
    getNewsList() {
      listNews(this.newsParams).then(response => {
        this.newsList = response.data.rows;
        this.newTotal = response.data.total;
        console.log(this.newsList);
      });
    },
    /*删除轮播图*/
    handleDelete(row) {
      const newsIds = row.newsId || this.ids;
      console.log(newsIds);
      this.$confirm('是否删除此内容？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delCarousel(newsIds);
      }).then(() => {
        this.getList();
        this.$message({
          message: '删除成功',
          type: 'success'
        });
      });
    }
  }
};
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>
