package com.www.designpattern.chainofresponsibility;

import com.www.designpattern.chainofresponsibility.action.PasswordProcess;
import com.www.designpattern.chainofresponsibility.action.PhoneNumberProcess;
import com.www.designpattern.chainofresponsibility.action.UserNameProcess;
import com.www.designpattern.chainofresponsibility.model.UserAccount;

/**
 * @Description
 * 责任链模式 适用场景 : 一个 请求 的 处理 , 需 要多个对象 中的 一个或若干个对象 协作进行处理 ;
 * 责任链模式 优点 :
 * ① 解耦 : 请求的 发送者 和 接收者 解耦 ; 接收者 是 请求的处理者 ;
 * ② 动态组合 : 责任链 可以 动态组合 , 使用配置 设置责任链的 顺序及 是否出现 ; 可以随时对责任链排序 , 随时增加拆除责任链中的某个请求对象 ;

 * 责任链模式 缺点 :
 * ① 性能 : 如果 责任链 太长 , 或责任链中请求的 处理时间过长 , 可能会 影响性能 ;
 * ② 个数 : 责任链 可能过多 ;
 * @Author 张卫刚
 * @Date Created on 2023/7/6
 */
public class ProcessService {
    public static void main(String[] args) {
        UserNameProcess userNameProcess = new UserNameProcess();
        PasswordProcess passwordProcess = new PasswordProcess();
        PhoneNumberProcess phoneNumberProcess = new PhoneNumberProcess();

        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("username");
        userAccount.setPassword("123456");
        userAccount.setPhoneNumber("13888888888");

        userNameProcess.setNextProcess(passwordProcess);
        passwordProcess.setNextProcess(phoneNumberProcess);

        userNameProcess.process(userAccount);
    }
}
