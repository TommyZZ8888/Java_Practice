package com.zz.sometest;

import com.zz.sometest.bean.BaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SometestApplicationTests {


    @Autowired
    public List<BaseService> baseServiceList;

    @Autowired
    private Map<String,BaseService> baseServiceMap;

    @Test
    void contextLoads() {
        System.out.println(baseServiceList.size());
        for (BaseService baseService : baseServiceList) {
            System.out.println(baseServiceMap.get(baseService.getClass().getSimpleName()));
        }
    }

}
