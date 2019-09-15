package com.company;

public class max {
    public int main(int[] a){
        int max = a[0];
        for (int i=1; i<20;i++){
            if (max < a[i]){
                max = a[i];
            }
        }
        return max;
    }
}
