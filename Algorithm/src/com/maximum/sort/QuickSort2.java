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

public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low + 1;
        int j = high;
        int temp = 0;
        while(true){
            while(i <= j && arr[i] < pivot){
                i++;
            }
            while(i <= j && arr[j] > pivot){
                j--;
            }
            if(i > j){
                break;
            }

            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }

    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }
}
