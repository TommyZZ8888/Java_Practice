package com.zz.sometest.spel;

/**
 * @Description Car
 * @Author 张卫刚
 * @Date Created on 2023/4/24
 */
public class Car {
    public String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
