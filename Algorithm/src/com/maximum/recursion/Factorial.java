package com.maximum.recursion;

public class Factorial {
    public static int f(int n){
        if(n == 1){
            return 1;
        }
        return n * f(n - 1);
    }

    public static void main(String[] args) {
        int f = f(5);
        System.out.println(f);
    }
}
