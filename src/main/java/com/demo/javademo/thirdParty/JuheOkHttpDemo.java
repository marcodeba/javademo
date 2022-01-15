package com.demo.javademo.thirdParty;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.URLEncoder;
import java.security.acl.LastOwnerException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JuheOkHttpDemo {
    //配置您申请的KEY
    public static final String APPKEY = "3cf2b7d030964697b52610cefe24b954";
    //请求接口地址
    private static final String REQUEST_URL = "http://op.juhe.cn/onebox/weather/query";

    public static void main(String[] args) throws IOException {
        JuheOkHttpDemo example = new JuheOkHttpDemo();
        example.doRequestCityWeather();
    }

    //1.根据城市查询天气
    public void doRequestCityWeather() {
        Map params = new HashMap();//请求参数
        params.put("cityname", "上海");//要查询的城市，如：温州、上海、北京
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype", "");//返回数据的格式,xml或json，默认json

        try {
            String result = this.getRequestURL(REQUEST_URL, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                log.info("weather result {}", object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public String getRequestURL(String strUrl, Map params, String method) throws Exception {
        if (method == null || method.equals("GET")) {
            strUrl = strUrl + "?" + urlencode(params);
        }

        return this.doOkHttpRequest(strUrl);
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : data.entrySet()) {
            try {
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String doOkHttpRequest(String strUrl) throws IOException {
        log.info("request url: {}", strUrl);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strUrl).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
