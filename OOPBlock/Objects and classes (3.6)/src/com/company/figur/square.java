package com.company.figur;

import java.util.Random;

public class square implements figur{
    private String name;
    private int area;
    @Override
    public void setArea(){
        Random random = new Random();
        area=random.nextInt(50)+1;
    }
    @Override
    public int getArea() {
        return area;
    }

    @Override
    public void setCoordinate(int coord) {

    }
    @Override
    public int getCoordinate(){
        return  0;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }
}