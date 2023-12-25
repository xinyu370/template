package com.user.org.testDemo;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MaxNumHalf {
    //最大数减半需要的次数
    public static void main(String[] args) {
        final List<Double> doubles = Arrays.asList(100.0, 300.0, 700.0);
        System.out.println(maxNumHalf(doubles));
    }

    public static int maxNumHalf(List<Double> arr){
        PriorityQueue<Double> heap = new PriorityQueue<>((a,b)-> (int) (b-a));
        double sum = 0;
        int res = 0;
        for(Double i:arr){
            sum+=i;
            heap.add(i);
        }
        double half = sum/2;
        double minnNum=0;
        while(!heap.isEmpty() && minnNum<half){
            Double poll = heap.poll();
            minnNum += poll/2;
            heap.add(poll/2);
            res+=1;
        }
        return res;
    }
}
