package com.zz.sometest;

import com.zz.sometest.bean.BaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Phaser;

@SpringBootTest
class SometestApplicationTests {


    @Autowired
    public List<BaseService> baseServiceList;

    @Autowired
    private Map<String,BaseService> baseServiceMap;


    @Test
    void contextLoads() {

        System.out.println(test());
//        System.out.println(baseServiceList.size());
//        for (BaseService baseService : baseServiceList) {
//            System.out.println(baseServiceMap.get(baseService.getClass().getSimpleName()));
//        }
    }

    public int test(){
        Phaser phaser = new Phaser(5);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
//                try {
//                    Thread.sleep(1000);
                    System.out.println(" arrive");
                list.add(1);
                phaser.arriveAndDeregister();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }).start();
        }
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("end");
        System.out.println(list.size());
        list.forEach(System.out::println);
        return 0;
    }
}
