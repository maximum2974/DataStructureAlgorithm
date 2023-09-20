package com.maximum.kmp;

import javax.swing.*;
import java.util.Arrays;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.kmp
 * @Author: maximum
 * @CreateTime: 2023-09-20
 * @Description: TODO
 * @Version: 1.0
 */

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        //System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }

    //写出kmp的搜索算法
    /**
     *
     * @param str1 源字符串
     * @param str2 字串
     * @param next 部分匹配表, 是字串对应的部分匹配表
     * @return 如果是-1就是没有匹配到, 否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        //遍历
        for(int i = 0, j = 0; i < str1.length(); i++){
            //需要处理 str1.charAt(i) != str2.chatAt(j), 去调整j的大小
            //KMP算法核心点
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }

            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串(字串) 的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串的长度是1 部分匹配值就是0
        for(int i = 1, j = 0; i < dest.length(); i++){
            //当dest.chatAt(i) != dest.charAt(j) , 我们需要从next[j-1]获取新的j
            //直到我们发现有 dest.charAt(i) == dest.chatAt(j)成立才推出
            //kmp算法的核心点
            while(j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            //dest.charAt(i) == dest.charAt(j) 满足时, 部分匹配值就是+1
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
