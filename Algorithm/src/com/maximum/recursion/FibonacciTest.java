package com.maximum.recursion;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static com.maximum.recursion.Fibonacci.fibonacci;
import static org.junit.Assert.assertEquals;

public class FibonacciTest {
    @Test
    @DisplayName("测试斐波拉契数列")
    public void test1(){
        assertEquals(1, fibonacci(2));
        assertEquals(2, fibonacci(3));
        assertEquals(3, fibonacci(4));
        assertEquals(5, fibonacci(5));
    }
}
