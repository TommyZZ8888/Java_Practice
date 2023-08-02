package com.zz.sometest.spel;

/**
 * @Description User
 * @Author 张卫刚
 * @Date Created on 2023/4/24
 */
public class User {

    public String name;

    public int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
         public int hashCode(){
                    int nameHash =  name.toUpperCase().hashCode();
                     return nameHash ^ age;
                 }

                 /**
           * @desc 覆盖equals方法
           */
                 @Override
         public boolean equals(Object obj){
                     if(obj == null){
                             return false;
                         }

                     //如果是同一个对象返回true，反之返回false
                     if(this == obj){
                             return true;
                         }

                     //判断是否类型相同
                     if(this.getClass() != obj.getClass()){
                           return false;
                       }

                     User person = (User)obj;
                     return name.equals(person.name) && age==person.age;
                 }
     }


