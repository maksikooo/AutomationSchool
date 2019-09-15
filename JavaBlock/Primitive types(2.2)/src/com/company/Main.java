
package com.company;
import java.util.Scanner; // импорт сканера


public class Main {

    public static String printHelloName(){
        String name;
        Scanner in = new Scanner(System.in);
        name=in.nextLine();
        return(name);
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        System.out.println("Hello," + printHelloName());

    }

}