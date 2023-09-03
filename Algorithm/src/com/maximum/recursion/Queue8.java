package com.maximum.recursion;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.recursion
 * @Author: maximum
 * @CreateTime: 2023-09-03
 * @Description: TODO
 * @Version: 1.0
 */

public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];

    public static void main(String[] args) {

    }
    //写一个方法,可以将皇后摆放的位置输出
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array + " ");
        }
        System.out.println();
    }
}
