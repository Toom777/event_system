<template>
  <div class="x-index-section">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-table
            :data="messageData"
            stripe>
          <el-table-column
              prop="notice"
              label="最新公告"
              width="450">
            <template slot-scope="scope">
              <el-link href="scope.row.notice">{{scope.row.notice}}</el-link>
              <p>发布：</p>
            </template>
          </el-table-column>

          <el-table-column
              prop="messageDate"
              width="100">
            <template slot="header" slot-scope="scope">
              <a href="" target="_blank">More<<</a>
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
              width="450">
            <template slot-scope="scope">
              <el-link href="scope.row.news">{{scope.row.news}}</el-link>
              <p>发布：</p>
            </template>
          </el-table-column>

          <el-table-column
              prop="newsDate"
              width="100"
          >
            <template slot="header" slot-scope="scope">
              <a href="" target="_blank">More<<</a>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>


  </div>
</template>

<script>
export default {
  name: "Message",
  data() {
    return {

      messageData: [{
        notice: '校内天梯选拔赛开始报名啦！',
        messageDate :'2020-3-6'
      }, {
        notice: '校内天梯选拔赛开始报名啦！',
        messageDate :'2020-3-6'
      }, {
        notice: '校内天梯选拔赛开始报名啦！',
        messageDate :'2020-3-6'
      }, {
        notice: '校内天梯选拔赛开始报名啦！',
        messageDate :'2020-3-6'
      }],
      newsData: [{
        news: '校内天梯选拔赛开始报名啦！',
        newsDate :'2020-3-6'
      }, {
        news: '校内天梯选拔赛开始报名啦！',
        newsDate :'2020-3-6'
      }, {
        news: '校内天梯选拔赛开始报名啦！',
        newsDate :'2020-3-6'
      }, {
        news: '校内天梯选拔赛开始报名啦！',
        newsDate :'2020-3-5'
      }]
    }
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
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}


</style>
