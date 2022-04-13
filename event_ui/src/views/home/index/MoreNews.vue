<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-table
            :data="newsData"
            stripe>
          <el-table-column
              prop="news"
              label="资讯信息"
          >
            <template slot-scope="scope">
              <el-link @click="newsPush(scope.row.newsId)">{{scope.row.newsTitle}}</el-link>
              <p>发布：{{scope.row.editor}}</p>
            </template>
          </el-table-column>

          <el-table-column
              prop="createTime"
              :formatter="dateFormat"
              align="right"

          >
          </el-table-column>
        </el-table>
      </el-col>


    </el-row>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="newsParams.pageSize"
        :current-page="newsParams.pageCurrent"
        @current-change="getPage"
    >
    </el-pagination>
  </div>
</template>

<script>
import {listNews} from "@/api/news";

export default {
  name: "MoreNews",
  data() {
    return {
      newsData: [],
      /*资讯查询参数*/
      newsParams: {
        pageCurrent: 1,
        pageSize: 6,
        newsTitle: '',
        newsType: ''
      },
      total: 0,

    }
  },
  methods: {
    /*日期格式化*/
    dateFormat(row, column){
      let data = row[column.property];
      if(data == null) {
        return null
      }
      let dt = new Date(data);
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate();
    },
    /*资讯跳转*/
    newsPush(row) {
      this.$router.push({
        path: '/newsContent',
        query: {
          newsId: row
        }
      })
    },
    getNewsList() {
      listNews(this.newsParams).then(res => {
        console.log(res);
        this.newsData = res.data.rows;
        this.total = res.data.total;
      })
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.newsParams.pageCurrent = currentPage;
      this.getNewsList()
    },

  },
  created() {
    this.getNewsList();
  }
}
</script>

<style scoped>

</style>
