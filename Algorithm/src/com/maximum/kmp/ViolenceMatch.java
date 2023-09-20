package com.maximum.kmp;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.kmp
 * @Author: maximum
 * @CreateTime: 2023-09-20
 * @Description: TODO
 * @Version: 1.0
 */

public class ViolenceMatch {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1 = "你好世界 你好世界 你好世界";
        String str2 = "你好世界";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; //i索引指向s1
        int j = 0; //j索引指向s2
        while(i < s1Len && j < s2Len){ //保证匹配时, 不越界
            if(s1[i] == s2[j]){ //匹配成功
                i++;
                j++;
            }else{ //没有匹配成功
                //如果失配(即str1[i] != str2[j]), 令 i = i - (j - 1), j = 0
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if(j == s2Len){
            return i - j;
        }else{
            return -1;
        }
    }
}
