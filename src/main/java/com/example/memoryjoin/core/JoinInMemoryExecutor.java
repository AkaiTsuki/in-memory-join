package com.example.memoryjoin.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class JoinInMemoryExecutor {

    @Autowired
    private ApplicationContext applicationContext;

    public void fetch(Object root, Class<?> rootType) {

        Field[] fields = rootType.getDeclaredFields();

        for (Field field : fields) {
            JoinInMemory joinInMemory = field.getAnnotation(JoinInMemory.class);
            if (joinInMemory == null) {
                continue;
            }

            ExpressionParser expressionParser = new SpelExpressionParser();
            String loader = joinInMemory.loader();
            String converter = joinInMemory.converter();

            StandardEvaluationContext context = new StandardEvaluationContext(root);
            context.setBeanResolver(new BeanFactoryResolver(applicationContext));

            Expression expression = expressionParser.parseExpression(loader);
            Object result = expression.getValue(context);
            System.out.println(result);

            StandardEvaluationContext converterContext = new StandardEvaluationContext(result);
            converterContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
            Expression converterExpression = expressionParser.parseExpression(converter);
            Object converterResult = converterExpression.getValue(converterContext);
            System.out.println(converterResult);

            try {
                field.setAccessible(true);
                field.set(root, converterResult);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
