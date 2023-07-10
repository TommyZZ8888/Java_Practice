package com.www.designpattern.chainofresponsibility.action;

import com.www.designpattern.chainofresponsibility.BusinessProcess;
import com.www.designpattern.chainofresponsibility.model.UserAccount;

/**
 * @Description PhoneNumberProcess
 * @Author 张卫刚
 * @Date Created on 2023/7/6
 */
public class PhoneNumberProcess extends BusinessProcess {
    @Override
    public void process(UserAccount userAccount) {
        if (userAccount.getPhoneNumber()!=null){
            System.out.println("phone verify success");
            if (businessProcess!=null){
                businessProcess.process(userAccount);
            }
        }else {
            System.out.println("phone verify fail");
        }
    }
}
