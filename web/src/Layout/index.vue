<script setup lang="ts">
import LayoutHeader from "@/Layout/LayoutHeader.vue";
import LayoutFooter from "@/Layout/LayoutFooter.vue";
</script>

<template>
  <div>
    <el-container>
      <el-header><LayoutHeader/></el-header>
      <el-main>
        <router-view/>
      </el-main>
      <el-footer><LayoutFooter/></el-footer>
    </el-container>
  </div>
</template>

<style scoped>
.el-header {
  padding: 0 !important;
}
</style>
