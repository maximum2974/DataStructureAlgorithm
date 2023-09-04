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

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};

        System.out.print("排序前:");
        System.out.println(Arrays.toString(arr));

        insertSort(arr);

        System.out.print("排序后:");
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;

            //给 insertVal 找到插入位置
            //说明
            //1. insertIndex >= 0 保证在给 insertVal 找插入位置, 不越界
            //2. insertVal < arr[insertIndex] 待插入的数,还没有找到插入位置
            //3. 就需要将 arr[insertIndex] 后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
