package com.maximum.binarysearch;

public class BinarySearch2 {
    public static int binarySearch2(int[]a, int target){
        int i = 0, j = a.length;
        while(1 < j - i){
            int m = (i + j) >>> 1;
            if(target < a[m]){
                j = m;
            }else{
                i = m;
            }
        }
        if(a[i] == target){
            return i;
        }else{
            return -1;
        }
    }
}
