package com.powernode.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//服务器启动和销毁的监听器
//还需要取web.xml注册监听器
public class LoginListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("服务器启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("服务器销毁了");
    }
}
