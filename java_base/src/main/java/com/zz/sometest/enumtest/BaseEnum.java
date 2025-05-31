package com.zz.sometest.enumtest;

import lombok.Getter;

/**
 * @Description BaseEnum
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
@Getter
public enum BaseEnum {

    SPRING(1,"spring") {
        public String getName() {
            return "春天";
        }
    },
    SUMMER(2,"summer") {
        public String getName() {
            return "夏天";
        }
    },
    AUTUMN(3,"autumn") {
        public String getName() {
            return "秋天";
        }
    },
    WINTER(4,"winter") {
        public String getName() {
            return "冬天";
        }
    },
    ;

    final Integer code;

    final String value;

    BaseEnum(Integer code,String value){
        this.code = code;
        this.value = value;
    }


    public abstract String getName();

    public static void main(String[] args) {
        System.out.println(BaseEnum.SUMMER.getName());

    }

}
