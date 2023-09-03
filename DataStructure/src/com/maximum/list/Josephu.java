package com.maximum.list;

import javax.xml.transform.Source;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(10, 20, 125);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构成一个环形链表
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("没有任何小孩~~");
            return;
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    /*
    startNo: 表示从第几个小孩开始数数
    countNum: 表示数几下
    nums: 表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper = first;
        while(true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和 helper 移动 k - 1 次
        for(int j = 0; j < startNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让 first 和 helper 指针同时移动 m - 1 次，然后出圈
        while (true){
            if(helper == first){
                break;
            }
            for(int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}


//创建一个Boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next;//指向下一个节点，默认null
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
