package com.www.designpattern.chainofresponsibility;

import java.util.List;

/**
 * @Describtion: ProcessTemplate
 * @Author: 张卫刚
 * @Date: 2025/6/27 11:35
 */
public class ProcessTemplate {

	private List<BusinessProcess> processList;

	public void setProcessList(List<BusinessProcess> list){
		this.processList = list;
	}

	public List<BusinessProcess> getProcessList(){
		return processList;
	}
}