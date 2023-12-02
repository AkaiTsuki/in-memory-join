package com.example.memoryjoin.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            String keyFromSourceData = joinInMemory.keyFromSourceData();
            String loader = joinInMemory.loader();
            String keyFromJoinData = joinInMemory.keyFromJoinData();
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

    public void batchFetch(List<Object> roots, Class<?> rootType) {
        if (CollectionUtils.isEmpty(roots)) {
            return;
        }

        Field[] fields = rootType.getDeclaredFields();
        for (Field field : fields) {
            JoinInMemory joinInMemory = field.getAnnotation(JoinInMemory.class);
            if (joinInMemory == null) {
                continue;
            }

            ExpressionParser expressionParser = new SpelExpressionParser();
            String keyFromSourceData = joinInMemory.keyFromSourceData();
            String loader = joinInMemory.loader();
            String keyFromJoinData = joinInMemory.keyFromJoinData();
            String converter = joinInMemory.converter();

            // 第一步：获取所有待查询的keys
            List<Object> parsedKeysFromSourceData = roots.stream().map(root -> {
                StandardEvaluationContext context = new StandardEvaluationContext(root);
                context.setBeanResolver(new BeanFactoryResolver(applicationContext));
                Expression expression = expressionParser.parseExpression(keyFromSourceData);
                return expression.getValue(context);
            }).collect(Collectors.toList());

            // 第二步：调用loader指定的方法批量获取数据
            StandardEvaluationContext parsedKeysFromSourceDataContext = new StandardEvaluationContext(parsedKeysFromSourceData);
            parsedKeysFromSourceDataContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
            Expression parsedKeysFromSourceDataExpression = expressionParser.parseExpression(loader);
            List<Object> result = (List<Object>)parsedKeysFromSourceDataExpression.getValue(parsedKeysFromSourceDataContext);

            // 第三步：根据注解指定的key构建map
            Map<String, List<Object>> joinDataMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(result)) {
                for(Object data: result) {
                    StandardEvaluationContext context = new StandardEvaluationContext(data);
                    context.setBeanResolver(new BeanFactoryResolver(applicationContext));
                    Expression expression = expressionParser.parseExpression(keyFromJoinData);
                    String joinKey = String.valueOf(expression.getValue(context));
                    if(!joinDataMap.containsKey(joinKey)) {
                        joinDataMap.put(joinKey, new ArrayList<>());
                    }
                    joinDataMap.get(joinKey).add(data);
                }
            }

            // 第四步：将获取的到的joinData转化成VO,并set到被补全对象中
            for(Object root: roots) {
                StandardEvaluationContext context = new StandardEvaluationContext(root);
                context.setBeanResolver(new BeanFactoryResolver(applicationContext));
                Expression expression = expressionParser.parseExpression(keyFromSourceData);
                Object keyInSourceData = expression.getValue(context);
                List<Object> joinData = joinDataMap.get(String.valueOf(keyInSourceData));
                List<Object> convertedData = joinData.stream().map(data-> {
                    StandardEvaluationContext converterContext = new StandardEvaluationContext(data);
                    converterContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
                    Expression converterExpression = expressionParser.parseExpression(converter);
                    return converterExpression.getValue(converterContext);
                }).toList();

                try {
                    field.setAccessible(true);
                    if(List.class.isAssignableFrom(field.getType())) {
                        field.set(root, convertedData);
                    } else {
                        field.set(root, convertedData.get(0));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
