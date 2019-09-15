package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер задания");
        int num = in.nextInt();
        in.nextLine();
        switch (num) {
            case 1: {
                String task = "Это строка для задания.Что то еще дописать.";
                System.out.println("Первый символ:" + task.charAt(0));
                System.out.println("Последний символ:" + task.charAt(task.length() - 1));
                if (task.length() % 2 != 0) {
                    int a = task.length() / 2;
                    System.out.println("Средний символ:" + task.charAt(a));
                }
                String[] strArray = task.split("(?<=\\.)");
                System.out.println(strArray[0]);
                String[] spaceArrray = strArray[0].split(" ");
                System.out.println("Количество пробелов: " + (spaceArrray.length - 1));
            }break;
            case 2: {
                in.reset();
                System.out.println("Введите строку");
                String originString = in.nextLine();
                System.out.println("Введите подстроку");
                String tempString = in.nextLine();
                System.out.println("Введите подстроку для замены");
                String newString = in.nextLine();
                originString=originString.replaceAll(tempString,newString);
                System.out.println("Новая строка : "+ originString);
            }break;
            case 3: {
                System.out.println("Введите строку слов");
                String str = in.nextLine();
                String[] strArray =str.split(" ");
                String max = strArray[0];
                for (int i=1; i < strArray.length; i++){
                    if (max.length() < strArray[i].length()){
                        max=strArray[i];
                    }

                }
                int count = 0;
                for (int a= 1; a<strArray.length;a++){
                    if(max.length() == strArray[a].length()){
                        count++;
                    }
                }
                if (count>1){
                    System.out.println("Нескольно слов максимальной длинны");
                }else
                    System.out.println(max);

            }break;
            default:
                System.out.println("От 1 до 3");
        }
    }

}
