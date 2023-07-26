package com.zz.juc.demos.singleton;


/**
 * @Description HungryManSingleton
 * @Author 张卫刚
 * @Date Created on 2023/7/26
 */
public class HungryManSingleton {

    public HungryManSingleton(){}

    public static HungryManSingleton hungryManSingleton = new HungryManSingleton();

    public static HungryManSingleton getSingleton(){
        return hungryManSingleton;
    }

    public static void main(String[] args) {
        HungryManSingleton hungryManSingleton1 = HungryManSingleton.getSingleton();
        HungryManSingleton hungryManSingleton2 = HungryManSingleton.getSingleton();
        System.out.println(hungryManSingleton1.hashCode());
        System.out.println(hungryManSingleton2.hashCode());
    }
}
