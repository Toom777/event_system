<template>
  <div class="activityContent">
    <el-tabs type="border-card" :active-name="activeName" @tab-click="clickActive">
      <el-tab-pane name="letter">
        <span slot="label"><i class="el-icon-view"></i>活动须知</span>
        <el-row>
          <h2>社区公益志愿服务承诺书</h2>
          <p style="line-height: 18pt;text-align: left;">
            <span style="font-size: 15pt; font-family: 仿宋_GB2312;">
              1、认真履行志愿服务承诺；<br/>
              2、自愿成为 社区志愿者服务岗志愿者，参加相关的社会公益服务活动；<br/>
              3、我愿意在 社区所要求的服务时间以及所提供的服务岗位上，为受助对象提供志愿服务；<br/>
              4、我同意在志愿服务过程中能够确保自身安全，对自己的行为负责；<br/>
              5、我同意在志愿服务过程中，对受助对象进行安全、规范、有序的服务；<br/>
              6、我同意服务期内所拍的照片、录象及相关资料可以在相关的'报道中使用；<br/>
              7、绝对不以志愿者身份从事任何以赢利为目的或违背国家法律、社会公德，损害志愿者形象的活动；
            </span>
          </p>
        </el-row>
        <el-divider></el-divider>
        <el-divider></el-divider>
        <el-row  type="flex" justify="center">
          <el-button type="primary" @click="handleConfirm">我同意</el-button>
          <el-button type="info" @click="handleReject">我拒绝</el-button>
        </el-row>
      </el-tab-pane>

      <el-tab-pane
          :disabled="contentVisible"
          name="content"
          label="活动信息"
      >
          <div>
            <ul class="list-group">
              <!--TODO  考虑上面放个图或者logo-->
              <li class="list-group-item"><table><tbody><tr><td>活动编号</td><td>{{activity.activityId}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动名称</td><td>{{activity.activityName}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动标签</td><td>{{activity.activityTag}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>发布组织</td><td>{{activity.contactName}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动时间</td><td>{{activity.beginTime}} ~ {{activity.endTime}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>报名人数</td><td>{{activity.allowCount}} / {{activity.confirmCount}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>截止报名</td><td>{{activity.deadTime}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动地点</td><td><span>{{activity.activityLocation}}</span></td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>联系人&emsp;</td><td>{{activity.contactName}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>联系电话</td><td>{{activity.contactPhone}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动内容</td><td>{{activity.content}}</td></tr></tbody></table></li>
            </ul>
            <el-button plain type="primary" style="width: 100%" @click="handleEnroll">我要报名</el-button>
          </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "ActivityContent",
  data() {
    return {
      /*选中的标签*/
      activeName:'letter',
      /*标签不可选*/
      contentVisible: true,
      /*活动内容*/
      activity: {
        activityId: 1001,
        activityName: "一个不错的活动",
        activityTag: "活动标签",
        content: "这是本次活动的全部内容",
        requirement: "报名要求",
        allowCount: 9,
        confirmCount: 10,
        beginTime: "2020-03-04 10:00",
        endTime: "2020-03-04 12:00",
        deadTime: "2020-03-03 23:00",
        contactName: "张三",
        contactPhone: "13212345678",
        activityLocation: "活动地点"
      }
    }
  },
  methods: {
    /*同意按钮*/
    handleConfirm() {
      this.contentVisible = false;
      this.activeName = 'content'
    },

    /*拒绝按钮*/
    handleReject() {
      this.$router.push("/home")
    },
    /*tab被选中*/
    clickActive(row) {
      this.activeName = row.paneName;
    },
    /*报名活动*/
    handleEnroll(){
      this.$confirm('是否报名' + this.activity.activityName, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '报名成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消报名'
        });
      });
    }
  },
  created() {
  }
}
</script>

<style scoped>
.activityContent{
  padding: 30px 15% 30px 15%;
}

.list-group-striped > .list-group-item {
  border-left: 0;
  border-right: 0;
  border-radius: 0;
  padding-left: 0;
  padding-right: 0;
}

.list-group {
  padding-left: 0px;
  list-style: none;
}
.list-group-item {
  border-bottom: 1px solid #e7eaec;
  border-top: 1px solid #e7eaec;
  margin-bottom: -1px;
  padding: 11px 0px;
  font-size: 13px;
}

</style>
