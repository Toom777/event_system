<template>
  <div>
    <h3>留言管理</h3>
    <!--<quill-editor/>-->
    <line-chart/>
  </div>

</template>

<script>
import quillEditor from '@/components/quillEditor/index'
import LineChart from "@/components/LineChart";
export default {
  name: "index",
  components: {
    LineChart,
    quillEditor
  },
  data() {
    return {

    }
  },
  created() {

  }
}
</script>

<style scoped>

</style>
