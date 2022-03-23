<template>
  <div class="tabs">
    <el-tag
        size="small"
        v-for="(tag, index) in tags"
        :key="tag.name"
        :closable="tag.name !== 'home'"
        :effect="$route.name === tag.name ? 'dark' : 'plain'"
        @click="changeMenu(tag)"
        @close="handleClose(tag, index)"
    >
      {{ tag.label }}
    </el-tag>
  </div>
</template>

<script>
import { mapState } from  'vuex'
export default {
  name: "CommonTag",
  data() {
    return {

    }
  },
  methods: {
    changeMenu() {

    },
    handleClose() {

    }
  }
  ,
  computed: {
    ...mapState({
      tags: state => state.tab.tabsList
    })
  }
}
</script>

<style scoped>

</style>
