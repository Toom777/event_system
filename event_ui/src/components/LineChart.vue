<template>
  <div id="charts-container" />
</template>

<script>
// 引入基本模板
import * as echarts from "echarts";
export default {
  name: "LineChart",
  data() {
    return{

    }
  },
  props: {
    options: {
      required: false,
      type: [Object],
      default: () => {},
    },
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {

      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('charts-container'));
      var option = {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [120, 200, 150, 80, 70, 110, 130],
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }]
      };
      myChart.setOption(option);
    }
  }
}
</script>

<style scoped>
#charts-container {
  width: 100%;
  height: 410px;
}
</style>
