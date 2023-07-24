package com.ttt.springframe.config;

/**
 * @Description BeanDefinition
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */
public class BeanDefinition {

    private Class<?> type;
    private String scope;
    private boolean isLazy;

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }
}
