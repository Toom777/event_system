<template>
  <div>
    <h3>统计</h3>

    <line-chart/>
  </div>
</template>

<script>
import PieChart from "@/views/home/echarts/pieChart";
import LineChart from "@/components/LineChart";
export default {
  name: "charts",
  components: {LineChart, PieChart}
}
</script>

<style scoped>

</style>
