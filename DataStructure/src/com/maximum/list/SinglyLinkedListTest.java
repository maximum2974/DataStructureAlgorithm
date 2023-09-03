package com.maximum.list;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {

    @Test
    public void test1(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.loop1(value-> {
            System.out.println(value);
        });

        list.loop2(value->{
            System.out.println(value);
        });
    }

    @Test
    public void test2(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        for(Integer value : list){
            System.out.println(value);
        }
    }

    @Test
    public void test3(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        Assertions.assertIterableEquals(List.of(1,2,3,4),list);
    }

    @Test
    public void test4(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.insert(2,5);
        for(Integer value : list){
            System.out.println(value);
        }
    }

    @Test
    public void test5(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeFirst();
        for(Integer value : list){
            System.out.println(value);
        }

        System.out.println("===================");

        list.removeFirst();
        for(Integer value : list){
            System.out.println(value);
        }
    }

    @Test
    public void test6(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.remove(2);
        for(Integer value : list){
            System.out.println(value);
        }
    }

    @Test
    @DisplayName("测试递归遍历")
    public void test7(){
        SinglyLinkedList list = getLinkedList();
        list.loop3(value->{
            System.out.println("before: " + value);
        }, value->{
            System.out.println("after: " + value);
        });
    }

    private SinglyLinkedList getLinkedList(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        return list;
    }
}