package com.maximum.search;

import java.util.Arrays;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.search
 * @Author: maximum
 * @CreateTime: 2023-09-10
 * @Description: TODO
 * @Version: 1.0
 */

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("index = " + index);
    }

    //编写插值查找算法
    //说明:插值查找算法,也要求数组是有序的
    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return 如果找到,返回对应的下标,如果没有找到,返回 -1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        //求出mid,自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
    }
}
