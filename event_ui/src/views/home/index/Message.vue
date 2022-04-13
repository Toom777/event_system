<template>
  <!--首页公告、资讯栏-->
  <div class="x-index-section">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-table
            :data="noticeData"
            stripe>
          <el-table-column
              prop="notice"
              label="最新公告"
              min-width="80%"
              >
            <template slot-scope="scope">
              <el-link @click="noticePush(scope.row.noticeId)">{{scope.row.noticeTitle}}</el-link>
              <p>发布：{{scope.row.editor}}</p>
            </template>
          </el-table-column>

          <el-table-column
              prop="createTime"
              min-width="15%"
              :formatter="dateFormat"
              >
            <template slot="header" slot-scope="scope">
              <a @click="handleNoticeMore">More<<</a>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="12">
        <el-table
            :data="newsData"
            stripe>
          <el-table-column
              prop="news"
              label="最新资讯"
              min-width="80%"
              >
            <template slot-scope="scope">
              <el-link @click="newsPush(scope.row.newsId)">{{scope.row.newsTitle}}</el-link>
              <p>发布：{{scope.row.editor}}</p>
            </template>
          </el-table-column>

          <el-table-column
              prop="createTime"
              min-width="15%"
              :formatter="dateFormat"
          >
            <template slot="header" slot-scope="scope">
              <a  @click="handleNewsMore">More<<</a>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import { listNotice } from '@/api/notice';
import {listNews} from "@/api/news";
export default {
  name: "Message",
  data() {
    return {

      noticeData: [],
      newsData: [],
      // 公告查询参数
      noticeParams: {
        pageCurrent: 1,
        pageSize: 4,
        editor: '',
        noticeTitle: '',
      },
      /*资讯查询参数*/
      newsParams: {
        pageCurrent: 1,
        pageSize: 4,
        newsTitle: '',
        newsType: ''
      }


    }
  },
  methods: {
    /*资讯跳转*/
    newsPush(row) {
      this.$router.push({
        path: '/newsContent',
        query: {
          newsId: row
        }
      })
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
    /*更多公告*/
    handleNoticeMore(){
      this.$router.push('/moreNotice')
    },
    /*更多资讯*/
    handleNewsMore(){
      this.$router.push('/moreNews')
    },
    /*日期格式化*/
    dateFormat(row, column){
      let data = row[column.property];
      if(data == null) {
        return null
      }
      let dt = new Date(data);
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate();

    },
    getNoticeList() {
      listNotice(this.noticeParams).then(res => {
        this.noticeData = res.data.rows;
      })
    },
    getNewsList() {
      listNews(this.newsParams).then(res => {
        this.newsData = res.data.rows;
      })
    },
  },
  created() {
    this.getNoticeList();
    this.getNewsList();
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
.x-index-section{
  margin-top: 50px;
  /*  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;*/
}


</style>
