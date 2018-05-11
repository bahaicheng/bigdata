package com.bahc.spring.applicationcontext;

import com.bahc.spring.entity.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainAppContext {
    public static void main(String[] args) {
        runApp3();
    }

    public static void runApp3(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        context.registerShutdownHook();
    }

    public static void runApp2(){
        //scope="prototype","singleton"
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
        objA.getMessage();
        objA.setMessage("I'm object A");
        objA.getMessage();
        HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
        objB.getMessage();
    }

    public static void runApp1(){
        ApplicationContext context = new FileSystemXmlApplicationContext("E:\\scala\\bigdata\\src\\resources\\applicationContext.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
    }
}
