
package com.company;

public class Main {

    public static void main(String[] args) {
        int q = 21;
        int w = 8;
        int i = q/w;
        int e = q%w;
        System.out.println("Задание 1:Целый число = "+i+", остаток = "+e) ;
        //1 задание
        int n = 73;
        int sum = n / 10 + n % 10;
        System.out.println("Задание 2: Сумма чисел : "+sum);
//        String s =Integer.toString(n);
//        char[] charArray = s.toCharArray();
//        int aValue = Character.getNumericValue(charArray[0]);
//        int bValue = Character.getNumericValue(charArray[1]);
//        System.out.println("2:" + (aValue+bValue)) ;
        //2 задание
        float k = (float) 23.55672;
        System.out.println("Задание 3: "+ Math.round(k));
        //3 задание
        int a = 0;
        int b = 2;
        int c = 5;
        int temp = b;
        b = c - a;
        a = a + temp;
        c = (a - temp) + temp + c;
        StringBuilder string = new StringBuilder();
        System.out.println(string.append("a=").append(a).append(" b=").append(b).append(" c=").append(c));
    }

}