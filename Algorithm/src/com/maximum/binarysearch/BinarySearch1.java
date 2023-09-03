package com.maximum.binarysearch;

public class BinarySearch1 {
    public static int binarySearchBasic(int[] a,int target){
        int i = 0, j = a.length - 1;
        while(i <= j){
            int m = (i + j) >>> 1;
            if(target < a[m]){
                j = m - 1;
            }else if(a[m] < target){
                i = m + 1;
            }else{
                return m;
            }
        }
        return -1;
    }

    /*
    问题1：为什么是 i <= j 意味着区间内有未比较的元素，而不是 i < j ?
    i == j 意味着 i,j 它们指向的元素也会参与比较‘
    i < j 只意味着 m 指向的元素参与比较

    问题2: (i + j) / 2 有没有问题？
    问题3: 都写成小于号有啥好处？
     */

    public static int binarySearchAlternative(int[] a,int target){
        int i = 0, j = a.length; //第一处
        while(i < j){ //第二处
            int m = (i + j) >>> 1;
            if(target < a[m]){
                j = m; //第三处
            }else if(a[m] < target){
                i = m + 1;
            }else{
                return m;
            }
        }
        return -1;
    }

    public static int binarySearchLeftmost1(int[] a,int target){
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while(i <= j){
            int m = (i + j) >>> 1;
            if(target < a[m]){
                j = m - 1;
            }else if(a[m] < target){
                i = m + 1;
            }else{
                //记录侯选位置
                candidate = m;
                j = m - 1;
            }
        }
        return candidate;
    }

    public static int binarySearchLeftmost2(int[] a,int target){
        int i = 0, j = a.length - 1;
        while(i <= j){
            int m = (i + j) >>> 1;
            if(target <= a[m]){
                j = m - 1;
            }else{
                i = m + 1;
            }
        }
        return i;
    }

    public static int binarySearchRightmost1(int[] a,int target){
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while(i <= j){
            int m = (i + j) >>> 1;
            if(target < a[m]){
                j = m - 1;
            }else if(a[m] < target){
                i = m + 1;
            }else{
                //记录侯选位置
                candidate = m;
                i = m + 1;
            }
        }
        return candidate;
    }

    public static int binarySearchRightmost2(int[] a,int target){
        int i = 0, j = a.length - 1;
        while(i <= j){
            int m = (i + j) >>> 1;
            if(target < a[m]){
                j = m - 1;
            }else{
                i = m + 1;
            }
        }
        return i - 1;
    }
}