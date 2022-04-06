<template>
  <div id="charts-container" />
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "pieChart",
  data() {
    return {

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
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      myChart.setOption(option);
    }
  }
}
</script>

<style scoped>

</style>
