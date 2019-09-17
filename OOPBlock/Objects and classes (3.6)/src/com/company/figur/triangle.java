package com.company.figur;

import java.util.Random;

public class triangle implements figur {
    private String name;
    private int area;
    @Override
    public void setArea(){
        Random random = new Random();
        area=random.nextInt(50)+1;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void setCoordinate(int coord) {

    }

    @Override
    public int getCoordinate() {
        return 0;
    }

    @Override
    public int getArea() {
        return area;
    }
}