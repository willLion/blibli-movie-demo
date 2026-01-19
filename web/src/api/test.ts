import request from "@/utils/request.ts";

const baseUrl = import.meta.env.VITE_APP_BASE_URL


export function getMethodTest() {
  return request({
    url: baseUrl + "/getMethodTest",
    method: "get",
  });
}

// 向后端传递json数据
export function postMethodTest(params: any) {
  return request({
    url: baseUrl + "/postMethodTest",
    method: "post",
    data: params
  });
}

// 向后端传递x-www-form-urlencoded数据
export function postMethodTest1(params: any) {
  return request({
    url: baseUrl + "/postMethodTest1",
    method: "post",
    data: params,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
  });
}
