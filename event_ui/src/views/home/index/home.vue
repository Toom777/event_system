<template>
  <div class="app-container">
    <router-view/>
  </div>

</template>

<script>
import CommonTag from "@/components/CommonTag";
import MainContent from "./MainContent"
import Tags from "@/components/Tags";
export default {
  name: "home",
  components: {CommonTag, MainContent, Tags},
  data() {
    return {

    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px;

}

</style>
