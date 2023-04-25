package com.zz.sometest.spel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

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
    void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        Car car = new Car();
        car.setName("porsche");
        User user = new User();
        user.setCar(car);
        factory.registerSingleton("user", user);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(factory));

        SpelExpressionParser parser = new SpelExpressionParser();
        User value = parser.parseExpression("@user").getValue(context, user.getClass());
        System.out.println(value);
        System.out.println(value == factory.getBean("user"));
    }


    //内联list
    @Test
    public void test2() {
        SpelExpressionParser parser = new SpelExpressionParser();
        //将返回不可修改的list空集合
        List<Integer> result1 = parser.parseExpression("{}").getValue(List.class);

        //对于字面量列表也返回不可修改的list列表
        List<Integer> result2 = parser.parseExpression("{1,2,3}").getValue(List.class);

        try {
            assert result2 != null;
            result2.set(0, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //对于列表中只要有一个不是字面量表达式，将只返回原始List，
        //不会进行不可修改处理
        String expression3 = "{{1+2,2+4},{3,4+4}}";
        List<List<Integer>> result3 = parser.parseExpression(expression3).getValue(List.class);
        result3.get(0).set(0, 1);
        System.out.println(result3);
        //声明二维数组并初始化
        int[] result4 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);
        System.out.println(result4[1]);
        //定义一维数组并初始化
        int[] result5 = parser.parseExpression("new int[1]").getValue(int[].class);
        System.out.println(result5[0]);
    }



    //表达式模板
    @Test
    public void test3(){
        //创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        //创建解析器上下文
        ParserContext context = new TemplateParserContext("%{", "}");
        Expression expression = parser.parseExpression("你好：%{#name},我们正在学习：%{#lesson}", context);

        //创建表达式计算上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name","zzzz");
        evaluationContext.setVariable("lesson","java入门到入土");
        //获取值
        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);
    }



}
