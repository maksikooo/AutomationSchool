package com.welcome;

import java.util.Scanner;

public class Hello{
    private static String name ;
    public static void setupName(String username){
        name=username;
    }
    public static void  welcome(String name){
        System.out.println("Hello, " + name);
    }
    public static void byeBye(String name){
        System.out.println("Bye, " + name);
    }
}