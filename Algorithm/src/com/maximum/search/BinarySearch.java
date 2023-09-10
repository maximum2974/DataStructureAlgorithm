package com.maximum.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.search
 * @Author: maximum
 * @CreateTime: 2023-09-10
 * @Description: TODO
 * @Version: 1.0
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        /*int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex=" + resIndex);*/

        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        if(left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){
            return binarySearch(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){
            return binarySearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
    }

    //有多个相同的数值时,如何将所有的数值都查得到,比如这里的1000
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        if(left > right){
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){ //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){ //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        }else{
            List<Integer> resIndexList = new ArrayList<Integer>();
            //向 mid 索引值的左边扫描,将所有满足 1000 的元素的下标, 加入到集合 ArrayList
            int temp = mid - 1;
            while(true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }

            resIndexList.add(mid);

            //向 mid 索引值的右边扫描,将所有满足 1000 的元素的下标, 加入到集合 ArrayList
            temp = mid + 1;
            while(true){
                if(temp > arr.length - 1 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
