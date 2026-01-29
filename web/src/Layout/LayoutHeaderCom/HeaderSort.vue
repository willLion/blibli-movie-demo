<script setup lang="ts">
  import LogSvg from './com/LogSvg.vue'
  import LayoutHeaderJson from '@/json/LayoutHeaderJson.json'
  import ShouYeSvg from '@/assets/svg/LayoutHeaderSvg/ShouYeSvg.vue'
  import XiaZaiSvg from '@/assets/svg/LayoutHeaderSvg/XiaZaiSvg.vue'

  const sortList = ref<{
    name: string,
    icon?: string,
  }[]>(LayoutHeaderJson.sort)

  const sortClick = (sortItem: any) => {
    console.log('sortItem', sortItem)
  }
</script>

<template>
<div class="header-sort-container">
  <div class="hsc-log">
    <LogSvg/>
  </div>
  <div class="hsc-lis">
    <div class="hsc-li" v-for="(sortItem, index) in sortList" :key="index"
    @click="sortClick(sortItem)">
      <template v-if="sortItem.name!='下载客户端'">{{sortItem.name}}</template>
      <template v-if="!!sortItem.icon">
        <template v-if="sortItem.icon ==='ShouYeSvg'">
          <ShouYeSvg style="height: 10px; width: 10px;margin-left: 5px"/>
        </template>
        <template v-else-if="sortItem.icon ==='XiaZaiSvg'">
          <xia-zai-svg  style="width: 16px; height: 16px; transform: translateY(25px)" />
          <div style="transform: translateY(-15px); margin-left: 25px">{{sortItem.name}}</div>
        </template>
      </template>
    </div>
  </div>
</div>
</template>

<style scoped lang="less">
.header-sort-container {
  display: flex;
  width: auto;
  height: 64px;
  margin-top: 0;
  .hsc-lis {
    display: flex;
    height: 64px;
    line-height: 64px;
    vertical-align: bottom;
    .hsc-li {
      height: inherit;
      color:#ffffff;
      margin-right: 30px;
      font-weight: 600;
      &:nth-child(1) {
        margin-left: 10px;
      }
    }
  }
}

//设置1099.9px避免样式断点的临界值冲突
@media (max-width: 1099.9px) {
  .hsc-li {
    margin-right: 10px !important;
  }
}

@media (min-width: 1100px) and (max-width: 1366.9px){
  .hsc-li {
    margin-right: 10px !important;
  }
}
</style>
