import axios from 'axios'
import {ElMessage} from "element-plus";

const service = axios.create({
  // 公共接口--这里注意后面会讲
  // baseURL: process.env.VUE_APP_BASE_PATH,
  baseURL: import.meta.env.VITE_APP_BASE_URL,
  // timeout: 3 * 1000
})

service.interceptors.request.use(config => {
  config.headers.token = localStorage.getItem('token');
  console.log('网络请求地址:', config.url, config.data)
  return config
}, error => {
  Promise.reject(error)
})

service.interceptors.response.use(response => {
  //接收到响应数据并成功后的一些共有的处理，关闭loading等

  return response.data
}, error => {
  /***** 接收到异常响应的处理开始 *****/
  if (error && error.response) {
    // 1.公共错误处理
    // 2.根据响应码具体处理
    switch (error.response.status) {
      case 400:
        error.message = '错误请求'
        break;
      case 401:
        error.message = '未授权，请重新登录'
        break;
      case 403:
        error.message = '拒绝访问'
        break;
      case 404:
        error.message = '请求错误,未找到该资源'
        // window.location.href = "/error"
        break;
      case 405:
        error.message = '请求方法未允许'
        break;
      case 408:
        error.message = '请求超时'
        break;
      case 500:
        error.message = '服务器端出错'
        break;
      case 501:
        error.message = '网络未实现'
        break;
      case 502:
        error.message = '网络错误'
        break;
      case 503:
        error.message = '服务不可用'
        break;
      case 504:
        error.message = '网络超时'
        break;
      case 505:
        error.message = 'http版本不支持该请求'
        break;
      default:
        error.message = `连接错误${error.response.status}`
    }
  } else {
    // 超时处理
    // if (JSON.stringify(error).includes('timeout')) {
    //     Message.error('服务器响应超时，请刷新当前页')
    // }
    error.message = '连接服务器失败'
  }

  ElMessage({
    message: error.message,
    type: 'error',
  })
  /***** 处理结束 *****/
  //如果不需要错误处理，以上的处理过程都可省略
  return Promise.resolve(error.response)
})
//4.导入文件
export default service
