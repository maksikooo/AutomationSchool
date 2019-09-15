package com.company;
import com.welcome.Hello;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Hello.setupName(s);
        Hello.welcome(s);
        System.out.println("Hello, world!");
        Hello.byeBye(s);
    }


}