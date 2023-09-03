package com.maximum.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer>{
    private static int size = 0; //逻辑大小
    private int capacity = 8; //容量
    private int[] array = {};

    public void addLast(int element){
        /*array[size] = element;
        size++;*/
        add(size,element);
    }

    public void add(int index,int element){
        checkAndGrow();
        if(index >= 0 && index < size){
            System.arraycopy(array,index,array,index + 1,size - index);
        }
        array[index] = element;
        size++;
    }

    private void checkAndGrow() {
        //容量检查
        if(size == 0){
            array = new int[capacity];
        }else if(size == capacity){
            //进行扩容  1.5 1.618 2
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array,0,newArray,0,size);
            array = newArray;
        }
    }

    public int remove(int index){
        int removed = array[index];
        if(index < size - 1){
            System.arraycopy(array,index+1,array,index,size-index-1);
        }
        size--;
        return removed;
    }

    public int get(int index){
        return array[index];
    }

    public void foreach(Consumer<Integer> consumer){
        for (int i = 0; i < size; i++) {
            //System.out.println(array[i]);
            //提供 array[i]
            //返回 void
            consumer.accept(array[i]);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;
            @Override
            public boolean hasNext() { //有没有下一个元素
                return i < size;
            }

            @Override
            public Integer next() { //返回当前元素，并移动到下一个元素
                return array[i++];
            }
        };
    }

    public IntStream stream(){
        return IntStream.of(Arrays.copyOfRange(array,0,size));
    }
}