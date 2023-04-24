package com.zz.sometest.spel;

/**
 * @Description User
 * @Author 张卫刚
 * @Date Created on 2023/4/24
 */
public class User {

    public String name;

    public Car car;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "car=" + car +
                '}';
    }
}
