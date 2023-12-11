package com.zz.sometest.test;

import cn.hutool.crypto.SecureUtil;
import com.zz.sometest.spel.User;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description TestService
 * @Author 张卫刚
 * @Date Created on 2023/11/28
 */
public class TestService {

    private User user;

    public static void main(String[] args) {
        String s = SecureUtil.md5("123456");
        System.out.println(s);
        String md5 =new String(SecureUtil.decode(s));
        System.out.println(md5);
    }


    /**
     * 解密
     *
     * @param password       要验证的密码（未加密）
     * @param securePassword 数据库中的加了盐值的密码
     * @return
     */
    public static boolean decrypt(String password, String securePassword) {
        boolean result = false;
        if (StringUtils.hasLength(password) && StringUtils.hasLength(securePassword)) {
            if (securePassword.length() == 65 && securePassword.contains("$")) {
                String[] securePasswordArr = securePassword.split("\\$");
                // 盐值
                String slat = securePasswordArr[0];
                String finalPassword = securePasswordArr[1];
                // 使用同样的加密算法和随机盐值生成最终加密的密码
                password = SecureUtil.md5(slat + password);
                if (finalPassword.equals(password)) {
                    result = true;
                }
            }
        }
        return result;
    }

}
