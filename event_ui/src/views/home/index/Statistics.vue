<template>
  <div style="cursor: default; margin-top: 50px">
    <el-row :gutter="20">
      <el-col :xs="12" :sm="12" :lg="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-1">
            <i class="iconfont icon-group grid-con-icon"></i>
            <div class="grid-cont-right">
              <div class="grid-num">{{StatisticsGroup.userNum}}</div>
              <div>社区志愿者</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :xs="12" :sm="12" :lg="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-2">
            <i class="iconfont icon-bars grid-con-icon"></i>
            <div class="grid-cont-right">
              <div class="grid-num">{{this.StatisticsGroup.activityNum}}</div>
              <div>公益项目</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :xs="12" :sm="12" :lg="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}">
          <div class="grid-content grid-con-3">
            <i class="iconfont icon-clock grid-con-icon"></i>
            <div class="grid-cont-right">
              <div class="grid-num">{{this.StatisticsGroup.hoursNum}}</div>
              <div>服务时长</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :xs="12" :sm="12" :lg="6">
        <el-card shadow="hover" :body-style="{padding: '0px'}" @click.native="handleMore">
          <div class="grid-content grid-con-4">
            <i class="iconfont icon-sitemap grid-con-icon"></i>
            <div class="grid-cont-right grid-text">
              <div>更多</div>
              <div>数据统计</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import '@/assets/iconfont/iconfont.css'
import { getStatistics } from '@/api/statistics'

export default {
  name: "Statistics",
  data() {
    return {
      StatisticsGroup: {
        userNum: '',
        activityNum: '',
        hoursNum: ''
      }

    }
  },
  methods: {
    /*更多数据跳转*/
    handleMore() {
      this.$router.push("/charts")
    },
    /*获取数据*/
    getNum() {
      getStatistics().then(res => {
        this.StatisticsGroup.userNum = res.data.data.userNum;
        this.StatisticsGroup.hoursNum = res.data.data.hoursNum;
        this.StatisticsGroup.activityNum = res.data.data.activityNum;
        console.log(this.StatisticsGroup);
      })
    }

  },
  created() {
    this.getNum()
  }
}
</script>


<style scoped>
.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}
.grid-text {
  font-size: 15px;
  font-weight: bold;
}
.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(0, 166, 90);
}

.grid-con-2 .grid-num {
  color: rgb(0, 166, 90);
}

.grid-con-3 .grid-con-icon {
  background: rgb(243, 156, 18);
}

.grid-con-3 .grid-num {
  color: rgb(243, 156, 18);
}

.grid-con-4 .grid-con-icon {
  background: rgb(242, 94, 67);
}

.grid-con-4 .grid-num {
  color: rgb(242, 94, 67);
}
.iconfont{
  color: white;
}
</style>
