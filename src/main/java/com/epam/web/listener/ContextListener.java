package com.epam.web.listener;

import com.epam.dao.DBManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ContextListener starts...");

        ServletContext context = sce.getServletContext();

        initLogger(context);
        DBManager.getInstance();
        initCommandContainer();
        initI18N(context);

        System.out.println("ContextListener finished");
    }

    private void initLogger (ServletContext ctx){
        System.out.println("init logger");

        String path = ctx.getRealPath("WEB-INF/periodicals.log");
        System.out.println("path => "+ path);
        System.setProperty("fileName",path);

        System.out.println("init logger finished");
    }

    private void initI18N(ServletContext context) {

        String localesFileName = context.getInitParameter("locales");
        String localeFilePath = context.getRealPath(localesFileName);
        Properties locales = new Properties();

        try {
            locales.load(Files.newInputStream(Paths.get(localeFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        context.setAttribute("locales",locales);
        locales.list(System.out);

    }

    private void initCommandContainer(){
        System.out.println("Command container initialization started");

        try {
            Class.forName("com.epam.web.command.CommandContainer");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Command container initialization finished");
    }
}
