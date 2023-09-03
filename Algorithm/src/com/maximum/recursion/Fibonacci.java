package com.maximum.recursion;

import java.util.Arrays;

public class Fibonacci {
    //f(3) => 5
    //f(4) => 9
    //f(5) => 15
    //2 * f(n+1) - 1

    public static int fibonacci(int n){
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return f(n, cache);
    }
    public static int f(int n, int[] cache){
        if(cache[n] != -1){
            return cache[n];
        }
        int x = f(n - 1, cache);
        int y = f(n - 2, cache);
        cache[n] = x + y;
        return cache[n];
    }
}
