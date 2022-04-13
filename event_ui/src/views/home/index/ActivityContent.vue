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
              <el-button @click="deleteCollect" class="collection" type="danger" icon="el-icon-check" v-if="isCollect" circle></el-button>
              <el-button @click="addCollect" class="collection" type="warning" icon="el-icon-star-off" v-else circle></el-button>
              <h2>{{activity.activityName}}</h2>
              <li class="list-group-item"><table><tbody><tr><td>活动编号</td><td>{{activity.activityId}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动标签</td><td>{{activity.activityTag}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>发布组织</td><td>{{activity.contactName}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动时间</td><td>{{activity.beginTime}} ~ {{activity.endTime}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>报名人数</td><td>{{activity.confirmCount}} / {{activity.allowCount}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>截止报名</td><td>{{activity.deadline}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动地点</td><td><span>{{activity.activitiyLocation}}</span></td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>联系人&emsp;</td><td>{{activity.contactName}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>联系电话</td><td>{{activity.contactPhone}}</td></tr></tbody></table></li>
              <li class="list-group-item"><table><tbody><tr><td>活动内容</td><td>{{activity.activityContent}}</td></tr></tbody></table></li>
            </ul>
            <el-button plain type="primary" style="width: 100%" @click="handleEnroll" v-if="!confirmBoolean">我要报名</el-button>
            <el-button style="width: 100%" disabled="disabled" v-else>已报名</el-button>
          </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {getActivity} from "@/api/activity";
import {addConfirmation, EnrollJuagement} from "@/api/confirmation";
import {addCollection, confirmCollect, delCollectionyAUId} from '@/api/collection';
export default {
  name: "ActivityContent",
  data() {
    return {
      /*是否收藏*/
      isCollect: false,
      /*选中的标签*/
      activeName:'letter',
      /*标签不可选*/
      contentVisible: true,
      /*活动内容*/
      activity: {},
      /*活动/收藏 确认*/
      confirmation: {
        userId: this.$store.getters.getUser.userId,
        activityId: parseInt(this.$route.query.activityId),
        activityName: '',
        userName: ''
      },
      addForm: {
        userId: this.$store.getters.getUser.userId,
        activityId: parseInt(this.$route.query.activityId),
        activityName: ''
      },
      /*是否报名*/
      confirmBoolean: false,
    }
  },
  methods: {
    /*日期格式转化*/
    dateFotmat(time){
      var date=new Date(time);
      var year=date.getFullYear();
      /* 在日期格式中，月份是从0开始的，因此要加0
       * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
       * */
      var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
      var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
      var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
      var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
      var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    /*取消收藏*/
    deleteCollect(){
      delCollectionyAUId(this.confirmation).then(res => {
        this.isCollect = false;
      });
    },
    /*添加收藏*/
    addCollect() {
      addCollection(this.addForm).then(res => {
        console.log(this.addForm);
        this.isCollect = true;
      });
    },
    /*查看是否已收藏*/
    confirmToCollect(){
      confirmCollect(this.confirmation).then(res => {
        if (res.data.data == 200) {
          this.isCollect = true;
        } else {
          this.isCollect = false;
        }
      });
    },
    /*查看用户是否已报名*/
    confirmEnroll() {
      EnrollJuagement(this.confirmation).then(res => {
        if (res.data.data == 200) {
          this.confirmBoolean = true;
        } else {
          this.confirmBoolean = false;
        }
      });
    },
    /*同意按钮*/
    handleConfirm() {
      this.contentVisible = false;
      this.activeName = 'content';
      this.confirmEnroll();
      this.confirmToCollect();
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
      if (this.activity.deadline > this.dateFotmat(new Date())){

        this.$confirm('是否报名' + this.activity.activityName, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          if (this.activity.allowCount > this.activity.confirmCount) {
            this.confirmation.activityName = this.addForm.activityName;
            this.confirmation.userName = this.$store.getters.getUser.name;
            addConfirmation(this.confirmation).then(res => {
              this.confirmBoolean = true;
              this.$message({
                type: 'success',
                message: '报名成功!'
              });
              this.getActivity();
            })
          } else {
            this.$message({
              type: 'error',
              message: '报名人数已满!'
            });
          }

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消报名'
          });
        });
      } else {
        this.$message({
          type: 'error',
          message: '已过报名时间！'
        });
      }

    },
    /*获取活动详情*/
    getActivity(){
      getActivity(this.$route.query.activityId).then(res => {
        this.activity = res.data.data;
        this.addForm.activityName = res.data.data.activityName;
      });
    },
  },
  created() {
    this.getActivity();

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
.collection{
  position: absolute;
  right: 40px;
  top: 30px;
}
</style>
