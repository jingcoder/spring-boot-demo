package com.example.demo.base;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/8 23:04
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // 事务回滚的注解，默认不回滚，@Rollback(true)已取消
public class BaseTest {

    @Autowired
    public MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;

    private String token = "";
    private Map<String,String> baseParamMap = new HashMap<>();

    @Autowired
    private Filter springSecurityFilterChain;

   /* @Autowired
    private Filter invoiceContextFilter;*/

    @Before
    public void init() throws Exception{
        mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain) // 必须得加这个Filter
                .build();

        token = this.getToken();

        Assert.assertNotNull(token);

        baseParamMap.put("access_token",token);
    }


   @After
    public void finishTest(){
       System.out.println("----------------------测试完成--------------------");
   }

   private String getToken(){
     MockHttpServletRequestBuilder builder =  MockMvcRequestBuilders.post("/oauth/token").param("grant_type","password").param("username","admin").param("password","admin2018").header("Authorization","Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0");
     String token = null;
       try {
           MvcResult result = mockMvc.perform(builder).andReturn();
           String content = result.getResponse().getContentAsString();
           if(content.contains("access_token")){
               JsonParser parser = new JsonParser();
               JsonObject obj = parser.parse(content).getAsJsonObject();
               //ib
               token = obj.get("access_token").getAsString();
           }else{
               System.out.println("---------------请求错误："+content);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }


      return token;
    }

    protected MvcResult executeBaseRequest(RequestType requestType, String url){


        return TestUtils.baseMockMVC(mockMvc,requestType,url,baseParamMap);
    }

    protected MvcResult executeBaseRequest(RequestType requestType, String url, Map<String,String> params){

        if(params == null){
            params = baseParamMap;
        }else{
            params.put("access_token",token);
        }

        return TestUtils.baseMockMVC(mockMvc,requestType,url,params);
    }

    protected MvcResult executeBaseRequest(RequestType requestType, String url, Map<String,String> params, Integer status){

        if(params == null){
            params = baseParamMap;
        }else{
            params.put("access_token",token);
        }
        return TestUtils.baseMockMVC(mockMvc,requestType,url,params,status);
    }

    @Test
    public void donothing(){

    }
}
