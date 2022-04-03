<template>
  <section id="editorTinymce"></section>
</template>

<script>
import './tinymce.min.js';
import './themes/silver/theme.min.js';
import './icons/default/icons.min.js';
import './langs/zh_CN.js';
export default {
  name: "index",
  mounted() {
    tinymce.init({
      selector: '#editorTinymce',
      language_url: './langs/zh_CN.js',
      language: 'zh_CN',
      //inline: true
    });
  },
  created() {

  }
}
</script>

<style scoped>
@import "skins/ui/oxide/skin.min.css";
</style>
