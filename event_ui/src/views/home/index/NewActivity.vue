<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-table
            :data="activityData"
            stripe>
          <el-table-column
              prop="notice"
              label="最新活动"
              min-width="80%"
          >
            <template slot-scope="scope">
              <el-link @click="activityPush(scope.row.activityId)">{{scope.row.activityName}}</el-link>
              <p>地点：{{scope.row.activitiyLocation}}</p>
            </template>
          </el-table-column>

          <el-table-column
              prop="createTime"
              min-width="8%"
              :formatter="dateFormat"
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
import {listActivity} from '@/api/activity';
export default {
  name: "NewActivity",
  data() {
    return {
      activityData:[],
      // 查询参数
      queryParams: {
        pageCurrent: 1,
        pageSize: 5,
        activityName: '',
        activityTag: '',
        beginTime: '',
        endTime: ''
      },
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
    getActivityList(){
      listActivity(this.queryParams).then(res => {
        this.activityData = res.data.rows;
      });
    },
    /*跳转至详情页*/
    activityPush(row) {
      this.$router.push({
        path: '/activityContent',
        query: {
          activityId: row
        }
      });

    },
  },
  created() {
    this.getActivityList();
  }
}
</script>

<style scoped>
.app-container{
  margin-top: 50px;
}
</style>
