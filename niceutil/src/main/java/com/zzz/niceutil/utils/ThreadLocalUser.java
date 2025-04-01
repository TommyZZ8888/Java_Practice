package com.zzz.niceutil.utils;



public class ThreadLocalUser {

    private ThreadLocalUser() {
    }


    private static final ThreadLocal<String> USER_INFO_DAO_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 清除用户信息
     */
    public static void clear() {
        USER_INFO_DAO_THREAD_LOCAL.remove();
    }

    /**
     * 存储用户信息
     */
    public static void set(String userInfoEntity) {
        USER_INFO_DAO_THREAD_LOCAL.set(userInfoEntity);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static String get() {
        return USER_INFO_DAO_THREAD_LOCAL.get();
    }

}
