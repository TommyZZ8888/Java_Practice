package com.zz.sometest.test;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Describtion: ListTest
 * @Author: 张卫刚
 * @Date: 2024/3/30 16:25
 */
@Slf4j
public class ListTest {
    public static void main(String[] args) {
       test(null);


    }

    public static void test(String param){
      String s = "hello";
      String b = "he"+new String("llo");

        System.out.println(s.equals(b));



    }


}
