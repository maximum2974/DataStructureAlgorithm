package com.maximum.array;

import org.junit.jupiter.api.Test;

import javax.swing.text.Element;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("Dynamic Array Test")
class DynamicArrayTest {

    @Test
    @DisplayName("Test 1")
    void test1() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.add(2, 5);
        for (int i = 0; i < 5; i++) {
            System.out.println(dynamicArray.get(i));
        }
    }

    @Test
    @DisplayName("Test 2")
    void test2() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        dynamicArray.forEach((element) -> {
            System.out.println(element);
        });
    }

    @Test
    @DisplayName("Test 3")
    void test3() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        for (Integer element : dynamicArray) {
            // hasNext() next()
            System.out.println(element);
        }
    }

    @Test
    @DisplayName("Test 4")
    void test4() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        dynamicArray.stream().forEach(element -> {
            System.out.println(element);
        });
    }

    @Test
    @DisplayName("Test 5")
    void test5() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        int removed = dynamicArray.remove(2);
        assertEquals(3, removed);
        assertIterableEquals(List.of(1, 2, 4, 5), dynamicArray);
    }

    @Test
    @DisplayName("测试扩容")
    public void test6(){
        DynamicArray dynamicArray = new DynamicArray();
        for (int i = 0; i < 9; i++) {
            dynamicArray.addLast(i + 1);
        }

        assertIterableEquals(
                List.of(1,2,3,4,5,6,7,8,9),
                dynamicArray
        );
    }
}

