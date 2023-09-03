package com.maximum.list;

import org.w3c.dom.traversal.NodeIterator;

import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedListSentinel implements Iterable<Integer>{
    private Node head = new Node(666,null);

    @Override
    public Iterator<Integer> iterator() {
        //匿名内部类 -> 带名字内部类
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Integer> {
        Node p = head.next;

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

        public Node(int value,Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value){
        insert(0,value);
    }

    public void loop1(Consumer<Integer> consumer){
        Node p = head.next;
        while(p != null){
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer){
        for(Node p = head.next; p != null; p = p.next){
            consumer.accept(p.value);
        }
    }

    private Node findLast(){
        Node p;
        for(p = head;p.next != null; p = p.next){

        }
        return p;
    }

    public void addLast(int value){
        Node last = findLast();
        last.next = new Node(value,null);
    }

    /*public void test(){
        int i = 0;
        for(Node p = head;p != null; p = p.next, i++){
            System.out.println(p.value + " 索引是: " + i);
        }
    }*/

    private Node findNode(int index){
        int i = -1;
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
        remove(0);
    }

    public void remove(int index){
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
