package com.zz.sometest.spel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @Description SpelTest
 * @Author 张卫刚
 * @Date Created on 2023/4/24
 */
@SpringBootTest
public class SpelTest {

    @Test
    void contextLoads() {
        User user = new User();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", user);

        SpelExpressionParser parser = new SpelExpressionParser();

        //对象属性存取及安全导航表达式
        //对象属性获取是非常简单的，即使是用"a.property.property"这种点缀式获取，spel对于属性名首字母是不区分大小写的
        //spel还引入了安全导航表达式“（对象|属性）?.属性”前面的表达式为空时不是抛出空指针异常，而是返回null
        //修改对象属性可以通过赋值表达式或者使用expression的setValue方法修改
        try {
            System.out.println(parser.parseExpression("#user.car.name").getValue(context, String.class));
        } catch (EvaluationException | ParseException e) {
            System.out.println("error");
        }

        System.out.println(parser.parseExpression("#user?.car?.name").getValue(context, String.class));

        Car car = new Car();
        car.setName("porsche");
        user.setCar(car);
        System.out.println(parser.parseExpression("#user?.car?.toString()").getValue(context, String.class));
    }



    //Bean引用
    //spel支持用“@”符号来引用bean，在引用bean时需要使用BeanResolver来查找bean，spring提供beanFactoryResolver实现
    @Test
    void test1(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        Car car = new Car();
        car.setName("porsche");
        User user = new User();
        user.setCar(car);
        factory.registerSingleton("user",user);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(factory));

        SpelExpressionParser parser = new SpelExpressionParser();
        User value = parser.parseExpression("@user").getValue(context, user.getClass());
        System.out.println(value);
        System.out.println(value==factory.getBean("user"));
    }
}
