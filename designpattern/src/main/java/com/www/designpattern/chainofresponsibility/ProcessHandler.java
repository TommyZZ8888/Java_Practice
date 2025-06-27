package com.www.designpattern.chainofresponsibility;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.www.designpattern.chainofresponsibility.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Describtion: ProcessHandle
 * @Author: 张卫刚
 * @Date: 2025/6/27 11:34
 */
@Service
public class ProcessHandler {
	/**
	 * 模板映射
	 */
	private Map<String, ProcessTemplate> templateConfig = null;

	public Map<String, ProcessTemplate> getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(Map<String, ProcessTemplate> templateConfig) {
		this.templateConfig = templateConfig;
	}

	public UserAccount process(UserAccount context) {
			preCheck(context);

		/**
		 * 遍历流程节点
		 */
		List<BusinessProcess> processList = templateConfig.get("send").getProcessList();
		for (BusinessProcess businessProcess : processList) {
			businessProcess.process(context);

		}
		return context;
	}


	private Boolean preCheck(UserAccount context) {
		if (context == null) {

		}

		//业务代码
		String businessCode = context.getUserName();
		if (StrUtil.isBlank(businessCode)) {

		}

		//执行模板
		ProcessTemplate processTemplate = templateConfig.get(businessCode);
		if (processTemplate == null) {

		}

		//执行模板列表
		List<BusinessProcess> processList = processTemplate.getProcessList();
		if (CollUtil.isEmpty(processList)) {

		}
		return true;
	}
}
