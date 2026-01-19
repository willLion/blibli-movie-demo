import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

export default defineConfig({
  build: {
    // 指定打包文件的输出目录
    outDir: 'build-demo.txt',
    // 指定生成静态资源的存放目录
    assetsDir: 'static-assets',
    // 图片转 base64 编码的阈值
    assetsInlineLimit: 4096,
    chunkSizeWarningLimit: 2000,
    minify: 'terser',
    terserOptions: {
      //移除log
      // compress: {
      //     drop_console: true,
      //     drop_debugger: true,
      //     pure_funcs: ['console.log'] // 只移除 console.log
      // }
    },
  },
  css: {
    preprocessorOptions: {
      scss: {
        // less全局变量
        additionalData: '@import "@/assets/style/common.scss";',
      },
      less: {
        // 配置全局引入的 Less 文件（支持数组，可引入多个）
        additionalData: `
          @import "@/assets/style/variables.less";
          @import "@/assets/style/mixins.less";
        `,
        // 解决 Less 版本兼容问题（如 Less 4.x 可能需要）
        javascriptEnabled: true
      }
    },
  },
  plugins: [
    vue(),
    vueJsx(),
    vueDevTools(),
    AutoImport({
      imports: ['vue'],
      dts: 'src/auto-import.d.ts',
      resolvers: [ElementPlusResolver()] // 将 resolvers 合并到同一个配置对象
    }),
    Components({
      resolvers: [ElementPlusResolver()],
      dirs: ['src/components'], // 将 dirs 合并到同一个配置对象
      dts: 'src/components.d.ts', // 将 dts 合并到同一个配置对象
    }),
  ],

  // 定义别名
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
    extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
  },
  server: {
    port: 8080,
    open: false,
    proxy: {
      '/dev-api': {
        target: 'http://localhost:3308',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/dev-api/, '')
      },
      '/prod-api': {
        target: 'http://localhost:3308',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/prod-api/, '')
      },
    }
  }
})
