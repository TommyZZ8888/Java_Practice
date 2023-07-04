package com.zz.sometest.enumtest;

import lombok.Getter;
/**
 * @Description BaseEnum
 * @Author 张卫刚
 * @Date Created on 2023/7/4
 */
@Getter
public enum ExtendEnum {

    SPRING("1", () -> "春天"),
    SUMMER("2", () -> "夏天"),
    AUTUMN("3", () -> "秋天"),
    WINTER("4", () -> "冬天"),
    ;

    final String code;

    final EnumTest value;

    ExtendEnum(String code, EnumTest value) {
        this.code = code;
        this.value = value;
    }


    public String getName() {
        return value.getChnName();
    }

    public static void main(String[] args) {
        System.out.println(ExtendEnum.SUMMER.getName());

    }

    @FunctionalInterface
    interface EnumTest{
        String getChnName();
    }

}
