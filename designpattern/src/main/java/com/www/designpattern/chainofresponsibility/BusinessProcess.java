package com.www.designpattern.chainofresponsibility;

import com.www.designpattern.chainofresponsibility.model.UserAccount;

/**
 * @Description BusinessProcess
 * @Author 张卫刚
 * @Date Created on 2023/7/6
 */
public abstract class BusinessProcess {

    /**
     * 使用protected是为了让子类可以获取到此类
     */
    protected BusinessProcess businessProcess;

    public void setNextProcess(BusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
    }

    /**
     * 处理 需子类实现
     * @param userAccount
     */
    public abstract void process(UserAccount userAccount);
}
