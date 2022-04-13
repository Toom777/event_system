<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-table
            :data="noticeData"
            stripe>
          <el-table-column
              prop="notice"
              label="公告信息"
          >
            <template slot-scope="scope">
              <el-link @click="noticePush(scope.row.noticeId)">{{scope.row.noticeTitle}}</el-link>
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
        :page-size="noticeParams.pageSize"
        :current-page="noticeParams.pageCurrent"
        @current-change="getPage"
    >
    </el-pagination>
  </div>
</template>

<script>
import {listNotice} from "@/api/notice";

export default {
  name: "MoreNotice",
  data() {
    return {
      noticeData: [],
      // 公告查询参数
      noticeParams: {
        pageCurrent: 1,
        pageSize: 6,
        editor: '',
        noticeTitle: '',
      },
      total: 0,
      search: '',

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
    /*公告跳转*/
    noticePush(row) {
      this.$router.push({
        path: '/noticeContent',
        query: {
          noticeId: row
        }
      })
    },
    getNoticeList() {
      listNotice(this.noticeParams).then(res => {
        this.noticeData = res.data.rows;
        this.total = res.data.total;
      })
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.noticeParams.pageCurrent = currentPage;
      this.getNoticeList()
    },
  },
  created() {
    this.getNoticeList();
  }
}
</script>

<style scoped>
a{
  color: #7ac804;
}
p{
  margin: 0;
  color: #b2b8bb;
}
.app-container{
  margin-top: 50px;
}
</style>
