package com.www.designpattern.chainofresponsibility;

import com.www.designpattern.chainofresponsibility.action.PasswordProcess;
import com.www.designpattern.chainofresponsibility.action.PhoneNumberProcess;
import com.www.designpattern.chainofresponsibility.action.UserNameProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describtion: PipelineConfig
 * @Author: 张卫刚
 * @Date: 2025/6/27 11:33
 */
@Configuration
public class PipelineConfig {

	@Autowired
	private UserNameProcess userNameProcess;
	@Autowired
	private PasswordProcess passwordProcess;
	@Autowired
	private PhoneNumberProcess phoneNumberProcess;

	@Bean("commonProcessTemplate")
	public ProcessTemplate commonSendTemplate() {
		ProcessTemplate processTemplate = new ProcessTemplate();

		processTemplate.setProcessList(Arrays.asList(userNameProcess, passwordProcess, phoneNumberProcess));
		return processTemplate;
	}


	/**
	 * pipeline流程控制器
	 * 目前暂定只有普通发送的流程
	 * 后续扩展则增加BusinessCode和ProcessTemplate
	 */
	@Bean
	public ProcessHandler processHandle() {
		ProcessHandler processHandler = new ProcessHandler();
		Map<String, ProcessTemplate> map = new HashMap<>();
		map.put("send", commonSendTemplate());
		processHandler.setTemplateConfig(map);
		return processHandler;
	}
}
