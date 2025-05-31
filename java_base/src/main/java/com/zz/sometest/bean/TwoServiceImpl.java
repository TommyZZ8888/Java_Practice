package com.zz.sometest.bean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description OneServiceImpl
 * @Author 张卫刚
 * @Date Created on 2023/6/8
 */
@Component("TwoServiceImpl")
@Order(2)
public class TwoServiceImpl implements BaseService{
}
