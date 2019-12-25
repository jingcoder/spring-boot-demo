package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringConfigApplicationTests {

    @Test
    public void contextLoads() {


    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("aa");
        set.add("bb");
        for(String item : set){
          boolean aa =   set.add("aa");
            System.out.println(aa);
        }

        for(String item : set){
            System.out.println(item);
        }


    }

}
