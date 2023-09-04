package com.maximum.sort;

import java.util.Arrays;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.sort
 * @Author: maximum
 * @CreateTime: 2023-09-04
 * @Description: TODO
 * @Version: 1.0
 */

public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1, -1, 90, 123};

        System.out.print("排序前:");
        System.out.println(Arrays.toString(arr));

        selectSort(arr);

        System.out.print("排序后:");
        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    public static void selectSort(int[] arr){
        //选择排序的时间复杂度是O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j < arr.length; j++){
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }

            //将最小值,放在arr[0],即交换
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
