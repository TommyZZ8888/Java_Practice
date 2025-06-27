package com.www.designpattern;

import com.www.designpattern.chainofresponsibility.ProcessHandler;
import com.www.designpattern.chainofresponsibility.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignpatternApplicationTests {
	@Autowired
	private ProcessHandler processHandler;

	@Test
	void contextLoads() {
		processHandler.process(new UserAccount());
	}

}
