<template>
  <div class="noticeContent">

    <el-col :xs="24" :sm="24">
      <el-card style="background-color: rgba(255, 255, 255,1)" class="first-card">
        <div slot="header" class="total blog-info">

          <div class="user-info">
            <i class="el-icon-user"></i>
            <span class="header">  {{this.notice.createBy}}</span>
          </div>
          <div class="blog-date">
            <i class="el-icon-date"></i>
            <span>  {{this.notice.createTime}}</span>
          </div>

        </div>

        <h2 class="blog-title header">{{this.notice.noticeTitle}}</h2>
        <div class="typo m-padded-lr-responsive m-padded-tb-large ql-editor"
             v-html="this.notice.noticeContent">
        </div>
        <el-divider/>
        <div>

        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import {getNotice} from '@/api/notice';
export default {
  name: "NoticeContent",
  data() {
    return {
      notice:[]
    }
  },
  methods: {
    /*获取公告详情*/
    getNotice(){
      getNotice(this.$route.query.noticeId).then(res => {
        this.notice = res.data.data;
        console.log(this.notice);
      });
    },
    returnHome(){
      this.$router.push("/home");
    }
  },
  created() {
    this.getNotice();

  }

}
</script>

<style scoped>
.noticeContent{
  padding: 30px 15% 30px 15%;
}
.loginOut {
  position: absolute;
  color: #e6a23c;
  font-weight: 600;
  font-size: 14px;
}

.el-card {
  width: 100%;
}

.el-popper /deep/ {
  box-shadow: 0 2px 4px 0 rgb(34 36 38 / 12%),
}

.first-card {
  border-radius: 10px 10px 10px 10px;

  padding-bottom: 10px;
  /*text-align: center;*/
  font: 300 1em/1.8 PingFang SC, Lantinghei SC, Microsoft Yahei, Hiragino Sans GB, Microsoft Sans Serif, WenQuanYi Micro Hei, sans-serif;

}

hr.style-one {
  width: 100%;
  background-image: linear-gradient(to right, rgba(64, 158, 255, 0), rgba(64, 158, 255, 0.75), rgba(64, 158, 255, 0));
}

.appreciate {
  text-align: center;
}

.tags {
  display: flex;
  align-items: center;
  margin-left: 50px;
}

.tag-item {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-left: 10px;
  margin-bottom: 20px;
}

.tag {
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 5px;
  background-color: #ecf5ff;
  border: 1px solid #409eff;
  color: #409eff;
  display: flex;
}

.sjx-outer {
  width: 0;
  height: 0;
  border-top: 7px solid transparent;
  border-bottom: 7px solid transparent;
  border-right: 7px solid #409eff;
  position: relative;
}

.sjx-inner {
  border-top: 7px solid transparent;
  border-bottom: 7px solid transparent;
  border-right: 7px solid #ecf5ff;
  top: -7px;
  left: 1px;
  position: absolute;
}

.author {
  text-align: left;
  background-color: #fcfff5;
  box-shadow: 0 0 0 1px #a3c293 inset;
  color: #2c662d;
  width: 100%;
  position: absolute;
  left: 0;
  margin: 20px 0;
  padding: 20px 0;
  font-size: small;
  font-family: PingFang SC, Lantinghei SC, Microsoft Yahei, Hiragino Sans GB, Microsoft Sans Serif, WenQuanYi Micro Hei, sans-serif;
}

.comments {
  margin-top: 150px;
  box-shadow: 0 1px 2px 0 rgb(34 36 38 / 15%);
  border: 1px solid rgba(34, 36, 38, .15);
  border-top: 2px solid #409EFF;
  text-align: left;
}
.blog-title {
  text-align: center;
}

.blog-info {
  display: flex;
  align-items: center;
  color: rgba(0, 0, 0, .4);
  font-size: 13px;
}
.blog-date {
  margin-right: 5px;
  float: left;
}

.blog-views {
  margin-right: 5px;
  float: right;
}

.user-info {
  justify-content: space-around;
  align-items: center;
  margin-right: 15px;
  float: left;
}

.back {
  justify-content: space-around;
  align-items: center;
  float: left;
  margin-left: 50px;
  padding: 0;
}

.header {
  text-decoration: none;
  color: #3a8ee6;
  font-weight: bold;
}

@media screen and (max-width: 768px) {
  .tags {
    margin-left: 0;
  }

  hr {
    display: none;
  }

  .comment-content {
    font-size: 12px !important;
  }
}

@media only screen and (max-width: 480px) {
  h2 {
    font-weight: normal;
  }

  code, pre {
    font-size: 13px !important;
  }
}

</style>
