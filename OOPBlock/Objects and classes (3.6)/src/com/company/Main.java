package com.company;

import com.company.figur.* ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //figur rectangle = new rectangle();
        int i=0;
        int r;
        Random random = new Random();;
        figur[] figurs = new figur[10];
        while (i<10){
            r= (random.nextInt(3)+1);
            if (r==1) {
                figurs[i] = new triangle();
                figurs[i].setName("Фигура "+i+" треугольник ");
                //System.out.println(r+"треугольник");
            }else
            if (r==2){
                figurs[i] = new square();
                figurs[i].setName("Фигура "+i+" квадрат  ");
                //	System.out.println(r+"квадрат");
            }else{
                figurs[i] = new rectangle();
                figurs[i].setName("Фигура "+i+" круг");
                //	System.out.println(r+"круг");
            }
            figurs[i].setArea();
            i++;

        }
        for (int l = 0;l < 10;l++){
            for (int j =0;j <9;j++){
                if (figurs[j].getArea() > figurs[j+1].getArea()){
                    figur tmp = figurs[j];
                    figurs[j]=figurs[j+1];
                    figurs[j+1]=tmp;
                }
            }
        }


        int m=0;
        while (m<10){
            System.out.println(figurs[m].getName()+" S = "+figurs[m].getArea());
            m++;
        }


    }
}