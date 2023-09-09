package com.maximum.sort;

import java.util.Arrays;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.sort
 * @Author: maximum
 * @CreateTime: 2023-09-08
 * @Description: TODO
 * @Version: 1.0
 */

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while(l < r){
            //在pivot 的左边一直找,找到大于等于pivot值,才退出
            while(arr[l] < pivot){
                l += 1;
            }
            //在pivot 的右边一直找,找到小于等于pivot值,才退出
            while(arr[r] > pivot){
                r -= 1;
            }
            //如果l>=r说明pivot 的左右两的值,已经按照左边全部是
            //小于等于pivot值,右边全部是大于等于 pivot值
            if(l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后,发现这个arr[l] == pivot 值 相等 r--, 前移
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后,发现这个arr[r] == pivot 值 相等 l++, 后移
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果 l == r, 必须l++, r--, 否则会出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }

        //向右递归
        if(right > l){
            quickSort(arr, l, right);
        }
    }
}
