package com.user.org.testDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSortLinkedList {

    public static void main(String[] args) {
        Node h1 = new Node(2);
        Node h2 = new Node(3);
        Node h3 = new Node(10);
        h1.next= h2;h2.next=h3;
        Node o1 = new Node(1);
        Node o2 = new Node(7);
        Node o3 = new Node(100);
        o1.next=o2;o2.next=o3;
        List<Node> node = new ArrayList<>();
        node.add(h1);node.add(o1);
         Node merge = merge(node);
        while(merge!=null){
            System.out.print(merge.val+",");
            merge=merge.next;
        }
        System.out.println();
    }


    /**
     * 合并n个有序链表：
     *  优先级队列就是一个堆，通过重写它的Comparator方法可以来实现大根堆或者小根堆
     * @param arr
     * @return
     */
    public static Node merge(List<Node> arr){
        PriorityQueue<Node> heap = new PriorityQueue<>((o1,o2)->o1.val-o2.val);
        for(Node node : arr){
            if(node!=null){
                heap.add(node);
            }
        }
        if(heap.isEmpty()){
            return null;
        }
        Node h = heap.poll();
        Node cur = h;
        heap.add(h.next);
        while(!heap.isEmpty()){
            Node poll = heap.poll();
            cur.next = poll;
            cur=cur.next;
            if(poll.next!=null){
                heap.add(poll.next);
            }
        }
        return h;
    }


    public static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
        public Node(){}
    }
}
