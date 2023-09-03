package com.maximum.list;

import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedList implements Iterable<Integer>{
    private Node head = null;

    @Override
    public Iterator<Integer> iterator() {
        //匿名内部类 -> 带名字内部类
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Integer> {
        Node p = head;

        @Override
        public boolean hasNext() { //是否有下一个元素
            return p != null;
        }

        @Override
        public Integer next() { //返回当前值，并指向下一个元素
            int v = p.value;
            p = p.next;
            return v;
        }
    }
    private static class Node{
        int value; //值
        Node next; //下一个节点指针

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value){
        //1、链表为空
        //head = new Node(value,null);
        //2、链表非空
        head = new Node(value, head);
    }

    public void loop1(Consumer<Integer> consumer){
        Node p = head;
        while(p != null){
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer){
        for(Node p = head; p != null; p = p.next){
            consumer.accept(p.value);
        }
    }

    public void loop3(Consumer<Integer> before,Consumer<Integer> after){
        recursion(head,before,after);
    }

    private void recursion(Node curr,
                           Consumer<Integer> before, Consumer<Integer> after){ //针对某个节点要进行的操作
        if(curr == null){
            return;
        }
        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);
    }

    private Node findLast(){
        if(head == null){
            return null;
        }
        Node p;
        for(p = head;p.next != null; p = p.next){

        }
        return p;
    }

    public void addLast(int value){
        Node last = findLast();
        if(last == null){
            addFirst(value);
            return;
        }
        last.next = new Node(value,null);
    }

    /*public void test(){
        int i = 0;
        for(Node p = head;p != null; p = p.next, i++){
            System.out.println(p.value + " 索引是: " + i);
        }
    }*/

    private Node findNode(int index){
        int i = 0;
        for(Node p = head; p != null; p = p.next, i++){
            if(i == index){
                return p;
            }
        }
        return null;
    }

    public int get(int index){
        Node node = findNode(index);
        if(node == null){
            throw illegalIndex(index);
        }
        return node.value;
    }

    public void insert(int index,int value){
        if(index == 0){
            addFirst(value);
            return;
        }
        Node prev = findNode(index - 1);
        if(prev == null){
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    private static IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    public void removeFirst(){
        if(head == null){
            throw illegalIndex(0);
        }
        head = head.next;
    }

    public void remove(int index){
        if(index == 0){
            removeFirst();
            return;
        }
        Node prev = findNode(index - 1);
        if(prev == null){
            throw illegalIndex(index);
        }
        Node removed = prev.next;
        if(removed == null){
            throw illegalIndex(index);
        }
        prev.next = removed.next;
    }
}
