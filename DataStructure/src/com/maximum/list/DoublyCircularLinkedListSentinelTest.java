package com.maximum.list;

import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyCircularLinkedListSentinelTest {
    @Test
    public void addFirst(){
        DoublyCircularLinkedListSentinel list = new DoublyCircularLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        assertIterableEquals(List.of(5,4,3,2,1),list);
    }

    @Test
    public void addLast(){
        DoublyCircularLinkedListSentinel list = new DoublyCircularLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        assertIterableEquals(List.of(1,2,3,4,5), list);
    }

    @Test
    public void removeFirst(){
        DoublyCircularLinkedListSentinel list = getLinkedList();
        list.removeFirst();
        assertIterableEquals(List.of(2,3,4,5), list);
        list.removeFirst();
        assertIterableEquals(List.of(3,4,5), list);
        list.removeFirst();
        assertIterableEquals(List.of(4,5), list);
        list.removeFirst();
        assertIterableEquals(List.of(5), list);
        list.removeFirst();
        assertThrows(IllegalArgumentException.class, list::removeFirst);
    }

    @Test
    public void removeLast(){
        DoublyCircularLinkedListSentinel list = getLinkedList();
        list.removeLast();
        assertIterableEquals(List.of(1,2,3,4),list);
        list.removeLast();
        assertIterableEquals(List.of(1,2,3),list);
        list.removeLast();
        assertIterableEquals(List.of(1,2), list);
        list.removeLast();
        assertIterableEquals(List.of(1), list);
        list.removeLast();
        assertThrows(IllegalArgumentException.class, list::removeLast);
    }

    @Test
    public void removeByValue(){
        DoublyCircularLinkedListSentinel list = getLinkedList();
        list.removeByValue(1);
        assertIterableEquals(List.of(2,3,4,5), list);
    }

    private DoublyCircularLinkedListSentinel getLinkedList() {
        DoublyCircularLinkedListSentinel list = new DoublyCircularLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        return list;
    }
}