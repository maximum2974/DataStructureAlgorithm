package com.maximum.listLeetCode;

public class E01Leetcode206 {
    //方法1
    public ListNode reverseList1(ListNode o1){
        ListNode n1 = null;
        ListNode p = o1;
        while(p != null){
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }

    //方法2
    public ListNode reverseList2(ListNode head){
        List list1 = new List(head);
        List list2 = new List(null);
        while(true){
            ListNode first = list1.removeFirst();
            if(first == null){
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    static class List{
        ListNode head;

        public List(ListNode head){
            this.head = head;
        }

        public void addFirst(ListNode first){
            first.next = head;
            head = first;
        }

        public ListNode removeFirst(){
            ListNode first = head;
            if(first != null){
                head = first.next;
            }
            return first;
        }
    }

    //方法3 recursion
    public ListNode reverseList3(ListNode p){
        if(p == null || p.next == null){
            return p; //最后节点
        }
        ListNode last = reverseList3(p.next);
        p.next.next = p;
        p.next = null;
        return last;
    }

    //方法4
    public ListNode reverseList4(ListNode o1){
        //1.空链表  2.一个元素
        if(o1 == null || o1.next == null){
            return o1;
        }
        ListNode o2 = o1.next;
        ListNode n1 = o1;
        while(o2 != null){
            o1.next = o2.next; //2. 将 o2 节点从链表断开，即 o1 节点指向第三节点
            o2.next = n1; //3. o2 节点链入链表头部
            n1 = o2; //4. n1 指向 o2
            o2 = o1.next; //5. o2 指向 o1 的下一个节点
        }
        return n1;
    }

    //方法5
    public ListNode reverseList5(ListNode o1){
        if(o1 == null || o1.next == null){
            return o1;
        }
        ListNode n1 = null;
        while(o1 != null){
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }
        return n1;
    }

    public static void main(String[] args){
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);

        System.out.println(o1);
        ListNode n1 = new E01Leetcode206().reverseList5(o1);
        System.out.println(n1);
    }
}
