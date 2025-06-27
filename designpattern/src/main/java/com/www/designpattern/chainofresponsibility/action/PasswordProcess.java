package com.www.designpattern.chainofresponsibility.action;

import com.www.designpattern.chainofresponsibility.BusinessProcess;
import com.www.designpattern.chainofresponsibility.model.UserAccount;
import org.springframework.stereotype.Service;

/**
 * @Description PasswordProcess
 * @Author 张卫刚
 * @Date Created on 2023/7/6
 */
@Service
public class PasswordProcess extends BusinessProcess {
    @Override
    public void process(UserAccount userAccount) {
        if (userAccount.getPassword()!=null){
            System.out.println("password verify success");
            if (businessProcess!=null){
                businessProcess.process(userAccount);
            }
        }else {
            System.out.println("password verify fail");
        }
    }
}
