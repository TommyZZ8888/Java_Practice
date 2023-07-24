package com.ttt.springframe;

import com.ttt.springframe.config.DogApplicationContext;
import com.ttt.springframe.config.MyDemoConfig;
import com.ttt.springframe.service.TestService;


public class SpringframeApplication {

    public static void main(String[] args) {
        DogApplicationContext dogApplicationContext = new DogApplicationContext(MyDemoConfig.class);
        TestService userService = (TestService) dogApplicationContext.getBean("testService");
        userService.test();
        userService.test();
    }

}
