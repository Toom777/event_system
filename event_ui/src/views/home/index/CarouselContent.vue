<template>
  <div class="block">
    <el-carousel
        height="440px"
    >
      <el-carousel-item v-for="item in imgList" :key="item">
        <img :src="item" alt="" style="width: 100%; height: 100%">

      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import {listCarouselNews} from "@/api/news";

export default {
  name: "CarouselContent",
  data() {
    return {
      imgList: [],
      carouserlList: []
    }
  },
  methods: {
    getNewsImg() {
      listCarouselNews().then(res => {

        this.carouserlList = res.data.data;

        for (let i = 0; i < this.carouserlList.length; i++) {
          this.imgList.push(this.carouserlList[i].picture);
        }

      });
    }
  },
  created() {
    this.getNewsImg();
  }
}
</script>

<style scoped>
.block {
  margin-top: 20px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
