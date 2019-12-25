package com.example.demo.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/9 10:42
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class TestUtils {

    private static JsonParser parser = new JsonParser();

    /**
     * 判断返回结果的 业务处理是否正确
     * @param result
     * @return
     */
    public static boolean isSuccess(MvcResult result){
        try {
            String content = result.getResponse().getContentAsString();

            JsonObject jsonObject = parser.parse(content).getAsJsonObject();
            if(jsonObject.get("code").getAsInt()==200){
                System.out.println("\n请求处理成功---------------结果："+content);
                return true;
            }else{
                System.out.println("\n请求处理失败---------------提示："+jsonObject.get("message").getAsString());
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSuccess(MvcResult result, String data){
        try {
            String content = result.getResponse().getContentAsString();

            JsonObject jsonObject = parser.parse(content).getAsJsonObject();
            if(jsonObject.get("code").getAsInt()==200){
                System.out.println("\n请求处理成功---------------结果："+content);
                if(data.equals(jsonObject.get("data").getAsString())){
                    return true;
                }
                return false;
            }else{
                System.out.println("\n请求处理失败---------------提示："+jsonObject.get("message").getAsString());
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSuccessList(MvcResult result, int num){

        try {
            String content = result.getResponse().getContentAsString();

            JsonObject jsonObject = parser.parse(content).getAsJsonObject();
            if(jsonObject.get("code").getAsInt()==200){
                JsonObject data = jsonObject.get("data").getAsJsonObject();
                JsonArray arr = data.get("list").getAsJsonArray();
                System.out.println("\n----------------查询结果条数："+arr.size());
                boolean b = (arr.size() == num);
                System.out.println("请求处理成功----------------查询结果："+content);
                Assert.assertTrue(arr.size() == num);

                return true;
            }else{
                System.out.println("\n请求处理失败---------------提示："+jsonObject.get("message").getAsString());
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSuccessDataList(MvcResult result, int num){
        try {
            String content = result.getResponse().getContentAsString();
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(content).getAsJsonObject();
            System.out.println("----------------查询结果："+content);
            if(jsonObject.get("code").getAsInt()==200){
                JsonArray arr = jsonObject.get("data").getAsJsonArray();
                System.out.println("\n----------------查询结果条数："+arr.size());
                if(arr.size() == num){
                    return true;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static JsonObject parseBaseResponse(MvcResult result) {
        try {
            String content = result.getResponse().getContentAsString();

            JsonObject jsonObject = parser.parse(content).getAsJsonObject();
            if(jsonObject.get("code").getAsInt()==200){
                JsonObject data = jsonObject.get("data").getAsJsonObject();
                System.out.println("请求处理成功----------------查询结果："+content);

                return data;
            }else{
                System.out.println("\n请求处理失败---------------提示："+jsonObject.get("message").getAsString());
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理基本的GET、POST请求，并匹配请求结果的状态码为200
     * @param mockMvc
     * @param requestType
     * @param url
     * @param params
     * @return
     */
    public static MvcResult baseMockMVC(MockMvc mockMvc , RequestType requestType, String url, Map<String,String> params){

        MvcResult result = null;
        result = TestUtils.baseMockMVC(mockMvc,requestType,url,params,200);
        return result;
    }

    /**
     * 处理基本的GET、POST请求，并匹配请求结果的状态码为指定的状态码
     * @param mockMvc
     * @param requestType
     * @param url
     * @param params
     * @param status http状态码
     * @return
     */
    public static MvcResult baseMockMVC(MockMvc mockMvc , RequestType requestType, String url, Map<String,String> params, Integer status){

        MvcResult result = null;
        MockHttpServletRequestBuilder builder = TestUtils.createBuilder(requestType,url,params);

        try {
            result = mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(status)).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static MockHttpServletRequestBuilder createBuilder(RequestType requestType, String url, Map<String,String> params){
        MockHttpServletRequestBuilder builder = null;
        switch (requestType){
            case GET:
                builder = MockMvcRequestBuilders.get(url);
                break;
            case POST:
                builder = MockMvcRequestBuilders.post(url);
                break;
            default:
                break;
        }

        if(params != null){
            for(Map.Entry<String,String> item : params.entrySet()){
                builder.param(item.getKey(),item.getValue());
            }
        }

        return builder;
    }



}
