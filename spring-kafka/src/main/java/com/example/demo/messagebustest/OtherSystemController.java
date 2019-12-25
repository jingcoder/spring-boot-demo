package com.example.demo.messagebustest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/28 14:14
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
@RequestMapping("/other")
public class OtherSystemController {

    @PostMapping("/receive1")
    public void recive1(String message){

        JsonParser parser = new BasicJsonParser();

            List<Object> list = parser.parseList(message);
            for(Object obj : list){
                Map<String,String> item = (Map<String, String>) obj;
                System.out.println("method: receive1 , message from topic：" + item.get("topic") +  " , message content is : " + item.get("content"));
            }
//        return "111";
    }

    @PostMapping("/receive2")
    public String recive2(String message){

        JsonParser parser = new BasicJsonParser();

            List<Object> list = parser.parseList(message);
            for(Object obj : list){
                Map<String,String> item = (Map<String, String>) obj;
                System.out.println("method: receive2 , message from topic：" + item.get("topic") +  " , message content is : " + item.get("content"));
        }
        return "111";
    }

    @PostMapping("/receive3")
    public String recive3(String message){

        JsonParser parser = new BasicJsonParser();
            List<Object> list = parser.parseList(message);
            for(Object obj : list){
                Map<String,String> item = (Map<String, String>) obj;
                System.out.println("method: receive2 , message from topic：" + item.get("topic") +  " , message content is : " + item.get("content"));
        }
        return "111";
    }



    public static void main(String[] args) {
        JsonParser parser = new BasicJsonParser();
        String json = "[{topic:1,content:1}]";
        List<Object> result = parser.parseList(json);
        for(Object obj : result){
            System.out.println(obj);
        }

        JsonObject o = new JsonObject();
        o.addProperty("topic",1);
        o.addProperty("content",1);
        JsonArray arr = new JsonArray();
        arr.add(o);
        System.out.println(arr);
    }
}
