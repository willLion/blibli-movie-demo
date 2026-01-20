<script setup lang="ts">
import SearchSvg from '@/Layout/LayoutHeaderCom/com/SearchSvg.vue'

const context = ref('111') // 搜索
const plaContext = ref('两个笨蛋')
const isFlag = ref(false)
const searchContext = () => {}

const changeFlag = () => {
  isFlag.value = false
  context.value = plaContext.value
}

const changeFlag1 = () => {
  // isFlag.value = true
}
</script>

<template>
  <div
    class="container w-[285px] h-[140px] mt-[12px] bg-[#e1e0dd] cursor-default relative rounded-lg header-search-container hover:bg-[#ffffff]"
    tabindex="-1"
    @click="changeFlag"
    @blur="changeFlag1"
  >
    <div
      class="container line-[40px] m-0 p-0 w-full h-[40px] leading-[40px] bg-[#ffffff] rounded-lg hsc-heder relative"
    >
      <div class="w-full h-full text-left indent-8 color-[#81858b]" v-if="isFlag">
        {{ plaContext }}11
      </div>
      <input class="hsc-input" v-else type="text" v-model="context" />
      <div class="w-[34px] h-[34px] text-center absolute top-[4px] right-[5px]
       rounded-[6px] cursor-pointer hover:bg-[#e6e7e9] line-[34px] "
       >
        <SearchSvg  style="width: 17px; height: 17px; margin-left: 10px; margin-top: 8px" />
      </div>
    </div>
    <div class="hsc-body">
      <div
        class="hsc-body-his h-[50px] line-[50px] flex justify-between items-center"
      >
        <span style="font-size: 16px; margin-left: 20px">搜索历史</span>
        <span
          style=" color: #ccc; cursor: pointer; margin-right: 20px; font-size: 12px"
          class="change-color "
          >清空</span
        >
      </div>
      <div
        class="hsc-body-tag"
        style="
          display: flex;
          height: 50px;
          line-height: 50px;
          justify-content: flex-start;
          align-items: center;
        "
      >
        <span style="margin-left: 25px">
          <el-tag closable effect="light"> 全款买菜 </el-tag>
        </span>
        <span style="margin-left: 25px"> <el-tag closable effect="light"> 全款 </el-tag></span>
      </div>
      <div class="hsc-body-title" style="height: 50px; text-align: left; line-height: 50px">
        <span class="ml-[20px]" style="font-size: 16px;">blibli热搜</span>
      </div>
      <div class="hsc-body-lis">
        <span>1 blibli热搜</span>
        <span>1 blibli热搜</span>
        <span>1 blibli热搜</span>
        <span>1 blibli热搜</span>
        <span>1 blibli热搜</span>
        <span>1 blibli热搜</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.header-search-container {
  &:hover {
    background-color: #ffffff;
  }

  .hsc-heder {
    .hsc-input {
      width: calc(100% - 50px);
      height: 34px;
      line-height: 34px;
      color: #696c72;
      background-color: #e6e7e9;
      border-radius: 6px;
      border: 0.5px solid #ccc;
      text-indent: 1em;
      position: absolute;
      left: 5px;
      top: 4px;

      &:hover {
        background-color: #e3e5e7;
      }
    }
  }

  .hsc-body {
    width: 100%;
    height: 600px;
    background-color: white;
    z-index: 999;
    border: 0.5px solid #ccc;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    border-top: none;
  }
}

.change-color {
  &:hover {
    color: #00aeec !important;
  }
}
</style>
