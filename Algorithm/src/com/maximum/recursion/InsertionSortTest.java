package com.maximum.recursion;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static com.maximum.recursion.InsertionSort.sort;
import static org.junit.Assert.assertArrayEquals;

public class InsertionSortTest {
    @Test
    @DisplayName("测试递归插入")
    public void test1(){
        int[] expected = {1,2,3,4,5};
        int[] a1 = {5,4,3,2,1};
        sort(a1);
        assertArrayEquals(expected, a1);
    }
}
