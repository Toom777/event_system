<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-table
            :data="activityData"
            stripe>
          <el-table-column
              prop="activity"
              label="活动信息"
          >
            <template slot-scope="scope">
              <el-link @click="activityPush(scope.row.activityId)">{{scope.row.activityName}}</el-link>
              <p>地点：{{scope.row.activityLocation}}</p>
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
        :page-size="activityParams.pageSize"
        :current-page="activityParams.pageCurrent"
        @current-change="getPage"
    >
    </el-pagination>
  </div>
</template>

<script>
import {listActivity} from "@/api/activity";

export default {
  name: "MoreActivity",
  data() {
    return {
      activityData: [],
      /*资讯查询参数*/
      activityParams: {
        pageCurrent: 1,
        pageSize: 5,
        activityName: '',
        activityTag: '',
        beginTime: '',
        endTime: ''
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
    activityPush(row) {
      this.$router.push({
        path: '/activityContent',
        query: {
          activityId: row
        }
      })
    },
    getActivityList() {
      listActivity(this.activityParams).then(res => {
        console.log(res);
        this.activityData = res.data.rows;
        this.total = res.data.total;
      })
    },
    /*获取当前点击页*/
    getPage(currentPage){
      this.activityParams.pageCurrent = currentPage;
      this.getActivityList()
    },

  },
  created() {
    this.getActivityList();
  }
}
</script>

<style scoped>

</style>
