<template>
  <div class="home">
      1
  </div>
</template>

<script>
import HomeHeader from "@/components/HomeHeader";
export default {
  name: "home",
  components: {HomeHeader},
  data() {
    return {
      backgroundDiv: {
        backgroundImage : 'url(' + require('/src/assets/images/bg.jpg') + ')'
      }
    }
  }
}
</script>

<style scoped>
.home{
  background-color: #707070;
}
.banner{
  background-color: #42b983;
  height: 100%;
}
</style>
