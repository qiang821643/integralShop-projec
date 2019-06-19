package com.integral.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.util
 * @date:2019/6/19
 **/
public class BeanUtil implements ApplicationContextAware {


    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.context = applicationContext;
    }

    public static void checkApplicationContaxt() {
        if (context == null) {
            throw new IllegalStateException("applicaitonContext未注入");
        }

    }

    /**
     * 通过类获取bean
     *
     * @param clasz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clasz) {
        checkApplicationContaxt();
        return context.getBean(clasz);
    }

    /**
     * 通过名称获取bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {

        return context.getBean(name);
    }
}
