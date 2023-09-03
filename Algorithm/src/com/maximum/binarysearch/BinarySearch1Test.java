package com.maximum.binarysearch;

import org.junit.Test;

import static com.maximum.binarysearch.BinarySearch1.*;
import static org.junit.Assert.assertEquals;

public class BinarySearch1Test {
    @Test
    public void test1() {
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(0,binarySearchBasic(a,7));
        assertEquals(1,binarySearchBasic(a,13));
        assertEquals(2,binarySearchBasic(a,21));
        assertEquals(3,binarySearchBasic(a,30));
        assertEquals(4,binarySearchBasic(a,38));
        assertEquals(5,binarySearchBasic(a,44));
        assertEquals(6,binarySearchBasic(a,52));
        assertEquals(7,binarySearchBasic(a,53));
    }

    @Test
    public void test2(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(-1,binarySearchBasic(a,0));
        assertEquals(-1,binarySearchBasic(a,15));
        assertEquals(-1,binarySearchBasic(a,60));
    }

    @Test
    public void test3() {
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(0,binarySearchAlternative(a,7));
        assertEquals(1,binarySearchAlternative(a,13));
        assertEquals(2,binarySearchAlternative(a,21));
        assertEquals(3,binarySearchAlternative(a,30));
        assertEquals(4,binarySearchAlternative(a,38));
        assertEquals(5,binarySearchAlternative(a,44));
        assertEquals(6,binarySearchAlternative(a,52));
        assertEquals(7,binarySearchAlternative(a,53));
    }

    @Test
    public void test4(){
        int[] a = {7,13,21,30,38,44,52,53};
        assertEquals(-1,binarySearchAlternative(a,0));
        assertEquals(-1,binarySearchAlternative(a,15));
        assertEquals(-1,binarySearchAlternative(a,60));
    }

    @Test
    public void test5(){
        int[] a = {1,2,4,4,4,5,6,7};
        assertEquals(0,binarySearchLeftmost1(a,1));
        assertEquals(1,binarySearchLeftmost1(a,2));
        assertEquals(2,binarySearchLeftmost1(a,4));
        assertEquals(5,binarySearchLeftmost1(a,5));
        assertEquals(6,binarySearchLeftmost1(a,6));
        assertEquals(7,binarySearchLeftmost1(a,7));

        assertEquals(-1,binarySearchLeftmost1(a,0));
        assertEquals(-1,binarySearchLeftmost1(a,3));
        assertEquals(-1,binarySearchLeftmost1(a,8));
    }


    @Test
    public void test6(){
        int[] a = {1,2,4,4,4,5,6,7};
        assertEquals(0,binarySearchRightmost1(a,1));
        assertEquals(1,binarySearchRightmost1(a,2));
        assertEquals(4,binarySearchRightmost1(a,4));
        assertEquals(5,binarySearchRightmost1(a,5));
        assertEquals(6,binarySearchRightmost1(a,6));
        assertEquals(7,binarySearchRightmost1(a,7));

        assertEquals(-1,binarySearchRightmost1(a,0));
        assertEquals(-1,binarySearchRightmost1(a,3));
        assertEquals(-1,binarySearchRightmost1(a,8));
    }

    public static void main(String[] args) {
        int i = 0;
        int j = Integer.MAX_VALUE - 1;
        int m = (i + j) / 2;
        System.out.println(m);

        i = m + 1;
        m = (i + j) / 2;
        System.out.println(i);
        System.out.println(j);
        System.out.println(m);

        /*
        同一个二进制数
        1011_1111_1111_1111_1111_1111_1111_1110

        不把最高位视为符号位，代表 3221225470
        把最高位视为符号位，代表：-1073741826
         */

        m = (i + j) >>> 1;
        System.out.println(m);

    }
}
