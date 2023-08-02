package com.zzz.niceutil.utils;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description NullPointSorted
 * @Author 张卫刚
 * @Date Created on 2023/8/2
 */
public class NullPointSorted {

    public static void main(String[] args) {
        sorted();

    }


    public static void sorted() {
        List<Student> list = new ArrayList<>();
        for (int i = 3; i < 5; i++) {
            Student student = new Student(i, "name" + i);
            list.add(student);
        }

        for (int i = 0; i < 3; i++) {
            Student student = new Student(i, "name" + i);
            list.add(student);
        }

        for (int i = 5; i < 10; i++) {
            Student student = new Student("name" + i);
            list.add(student);
        }

        list = list.stream().sorted(Comparator.nullsLast(Comparator.comparing(Student::getAge, Comparator.nullsLast(Integer::compareTo))))
                .collect(Collectors.toList());

        list.forEach(item -> {
            System.out.println(item.getName() + " " + item.getAge());
        });
    }
}

@Data
@Getter
class Student {
    private Integer age;

    private String name;

    public Student(Integer age) {
        this.age = age;
    }

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
