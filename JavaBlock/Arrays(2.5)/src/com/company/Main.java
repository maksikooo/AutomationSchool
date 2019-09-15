package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Номер задания от 1 до 5");
        int q = in.nextInt();
        in.nextLine();
        switch (q) {
            case 1: {
                int[] a= new initializeArray().main();
                int max = new max().main(a);
                System.out.println(max) ;
            }break;
            case 2:{
                int[] a = new int[10];
                for(int i=0 ;i <8; i++){
                    System.out.println("Введите "+(i+1)+"число");
                    a[i] = in.nextInt();
                }
                for (int j=9;j>=0; j--){
                    System.out.println(a[j]);
                }
            }break;
            case 3:{
                String[] str = new String[10];
                int[] strLength =new int[10];
                for (int i = 0 ; i <10 ; i++){
                    System.out.println("Введите строку "+(i+1));
                    str[i]= in.nextLine();
                    strLength[i]=str[i].length();
                }
                for (int j=0; j < 10; j++) {
                    System.out.println(strLength[j]);
                }
                }break;
            case 4:{
                int[] a = new int[10] ;
                for (int i = 0 ; i<10 ; i++){
                    System.out.println("Введите число №"+(i+1));
                    a[i]= in.nextInt();
                }
                for (int j = 0 ; j< 5 ;j++) {
                    a[a.length - 1 - j] ^= a[j] ^= a[a.length - 1 - j];
                    a[j] ^= a[a.length - 1 - j];
                }
                for (int y = 0 ; y<10 ; y++) {
                    System.out.println(a[y]);
                }
            }break;
            case 5:{
                int[] b = new int[20] ;
                for (int i = 0 ; i<20 ; i++){
                    System.out.println("Введите число №"+(i+1));
                    b[i]= in.nextInt();
                }
                int[] c = new int[10],d = new int[10];
                System.arraycopy(b,0,c,0,10);
                System.arraycopy(b,10,d,0,10);
                for(Integer i:d)
                System.out.println(i);

            }break;
            default:
                System.out.println("от 1 до 5");

        }

    }
}
