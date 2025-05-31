package com.zz.sometest.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @Description SpelTest
 * @Author 张卫刚
 * @Date Created on 2023/4/24
 */
public class SpelExpressionTest {
    public static void main(String[] args) {

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('hello ' + 'world' ).concat(#end)");

        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("end", "!");
        System.out.println(expression.getValue(ctx));

        //表达式赋值
        //user为root对象
        User user = new User();
        StandardEvaluationContext context = new StandardEvaluationContext(user);
        parser.parseExpression("#root.name").setValue(context,"andy");
        System.out.println(parser.parseExpression("#root").getValue(context, user.getClass()));

        //user为变量
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("user",user);
        parser.parseExpression("#user.name").setValue(evaluationContext,"emily");
        System.out.println(parser.parseExpression("#user").getValue(evaluationContext, user.getClass()));

    }
}
