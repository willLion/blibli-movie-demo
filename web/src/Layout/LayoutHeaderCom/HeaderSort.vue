<script setup lang="ts">
  import logSvg from './com/logSvg.vue'
  import LayoutHeaderJson from '@/json/LayoutHeaderJson.json'

  const sortList = ref<{
    name: string,
    icon?: string,
  }[]>(LayoutHeaderJson.sort)
</script>

<template>
<div class="header-sort-container">
  <div class="hsc-log">
    <logSvg/>
  </div>
  <div class="hsc-lis">
    <div v-for="(sortItem, index) in sortList" :key="index">
      {{sortItem.name}}
      <template v-if="!!sortItem.icon">{{sortItem.icon}}</template>
    </div>
  </div>
</div>
</template>

<style scoped lang="less">
.header-sort-container {
  display: flex;
  width: 100%;
  height: 64px;
  margin-top: 0;
  .hsc-lis {
    display: flex;

  }
}
</style>
