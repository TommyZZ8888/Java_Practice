package com.ttt.springframe.config;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @Description DogApplicationContext
 * @Author 张卫刚
 * @Date Created on 2023/7/24
 */
public class DogApplicationContext {

    //配置类
    private Class<?> config;
    //初始的bean定义
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    //单例缓存池
    private Map<Object, Object> singleBean = new HashMap<>();


    public DogApplicationContext(Class<?> myDemoConfigClass) {
        this.config = myDemoConfigClass;

        scan();
    }


    public void scan() {
        ComponentScan declaredAnnotation = config.getDeclaredAnnotation(ComponentScan.class);
        String basePackages = declaredAnnotation.basePackages();
        doScan(basePackages);

        beanDefinitionMap.forEach((key, beanDefinition) -> {
            if (beanDefinition.isLazy() && "singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(key);
                singleBean.put(key, bean);
            }
        });

    }

    /**
     * 解析配置类
     * @param value
     */
    public void doScan(String value) {
        String path = value.replace(".", "/");
        //正常走文件解析
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            return;
        }

        File file = new File(resource.getFile());
        List<File> classFile = new ArrayList<>();
        //简单点直接双层解析即可
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                if (f.isDirectory()) {
                    for (File f1 : Objects.requireNonNull(f.listFiles())) {
                        if (!f1.isDirectory()) {
                            classFile.add(f1);
                        }
                    }
                } else {
                    classFile.add(f);
                }
            }
        }

        //遍历所有解析文件
        for (File cFile : classFile) {
            String absolutePath = cFile.getAbsolutePath();
            String className = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"))
                    .replace("\\", ".");

            try {
                Class<?> clazz = classLoader.loadClass(className);
                if (clazz.isAnnotationPresent(Component.class)) {
                    //将bean上的注解封装到bean定义中
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setType(clazz);
                    beanDefinition.setLazy(clazz.isAnnotationPresent(Lazy.class));
                    if (clazz.isAnnotationPresent(Scope.class)) {
                        beanDefinition.setScope(clazz.getAnnotation(Scope.class).value());
                    } else {
                        beanDefinition.setScope("singleton");
                    }

                    String beanName = clazz.getAnnotation(Component.class).value();
                    if (beanName.isEmpty()) {
                        //如果不设置beanName会默认生产唯一一个name，Spring底层也是这样做的
                        beanName = Introspector.decapitalize(clazz.getSimpleName());
                    }

                    beanDefinitionMap.put(beanName, beanDefinition);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Object createBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Class<?> type = beanDefinition.getType();

        try {
            Object instance = type.getDeclaredConstructor().newInstance();
            populateBean(instance);

            if (instance instanceof ApplicationContextAware) {
                ((ApplicationContextAware) instance).setApplicationContext(this);
            }
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            if (type.isAnnotationPresent(Transaction.class)) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(type);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("事务开启");
                        Object invoke = method.invoke(instance, objects);
                        System.out.println("事务提交");
                        return invoke;
                    }
                });
                return enhancer.create();
            }
            return instance;
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void populateBean(Object instance) {
        Field[] declaredFields = instance.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //寻找注入点
            if (declaredField.isAnnotationPresent(AutoWired.class)) {
                Object bean = getBean(declaredField.getName());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(instance, bean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }

        if ("singleton".equals(beanDefinitionMap.get(beanName).getScope())) {
            if (singleBean.containsKey(beanName)) {
                return singleBean.get(beanName);
            } else {
                Object bean = createBean(beanName);
                singleBean.put(beanName, bean);
                return bean;
            }
        }
        return createBean(beanName);
    }


}
