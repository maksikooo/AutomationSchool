package com.company;

import java.util.Scanner;

public class initializeArray {
    public int[] main() {
        int[] a = new int[20];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 20; i++) {
            System.out.println("Введите " + (i + 1) + " число массива");
            a[i] = in.nextInt();
        }
        System.out.println("Done");
        return a;
    }
}
