package com.company;


import org.apache.logging.log4j.*;

public class logic {
    public static Logger log = LogManager.getLogger(logic.class);
    private void second(){
        System.out.println("Второй класс вызван");
        log.info("Second class executed");

    }
    public void first (){
        System.out.println("Первый класс вызван");
        second();
        log.info("First class executed");
    }

}