package com.maximum.listLeetCode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode p = this;
        while(p != null){
            sb.append(p.val);
            if(p.next != null){
                sb.append(",");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
