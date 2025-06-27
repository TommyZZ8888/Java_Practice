package com.www.designpattern.chainofresponsibility.action;

import com.www.designpattern.chainofresponsibility.BusinessProcess;
import com.www.designpattern.chainofresponsibility.model.UserAccount;
import org.springframework.stereotype.Service;

/**
 * @Description UserNameProcess
 * @Author 张卫刚
 * @Date Created on 2023/7/6
 */
@Service
public class UserNameProcess extends BusinessProcess {
    @Override
    public void process(UserAccount userAccount) {
        if (userAccount.getUserName()!=null){
            System.out.println("username verify success");

            if (businessProcess!=null){
                businessProcess.process(userAccount);
            }
        }else {
            System.out.println("username verify fail");
        }
    }
}
