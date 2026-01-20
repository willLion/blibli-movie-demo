<script setup lang="ts">
import HeaderSort from "@/Layout/LayoutHeaderCom/HeaderSort.vue";
import HeaderSearch from "@/Layout/LayoutHeaderCom/HeaderSearch.vue";
import HeaderSortIcon from "@/Layout/LayoutHeaderCom/HeaderSortIcon.vue";


</script>

<template>
<div class="lay-h-container">
  <HeaderSort/>
  <HeaderSearch/>
  <HeaderSortIcon/>
</div>
</template>

<style scoped lang="less">
.lay-h-container {
  .container;
  .flex;
  width: 100vw;
  min-width: 1100px;
  height: 200px;
  max-height: 200px;
  min-height: 200px;
  background-image: url("@/assets/img/header_bg.avif");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center top;
  padding: 0 30px;
}
</style>
