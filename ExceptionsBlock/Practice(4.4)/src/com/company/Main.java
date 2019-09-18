package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String originString = null;
        String tempString = null;
        String newString = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку");
        originString = in.nextLine();
        System.out.println("Введите подстроку");
        tempString = in.nextLine();
        try{
            int zero = 5/0;
        }catch (ArithmeticException e){
            System.out.println("You shall not fall!");
        }
        System.out.println("Введите подстроку для замены");
        newString = in.nextLine();
        originString=originString.replaceAll(tempString,newString);
        System.out.println("Новая строка : "+ originString);
    }
}