package juc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @Author qinwenlong
 * @Date 2021/12/10
 **/
public class ReentrantLockDemo {
    Comparator<Integer[]> c = new Comparator<Integer[]>(){
        public int compare(Integer[] e, Integer[] f){
            return e[1]-f[1];
        }
    };
    int[][] s ;
    PriorityQueue<Integer[]> queue = new PriorityQueue<>((a,b)->b[1]-a[1]);


    public static int count = 0;
    static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {

        Deque<Integer> list = new LinkedList();
        list.add(1);
        list.add(3);
//        int[][] s= new int[1][1];
//        list.re
//        list.sort((o1, o2) -> {
//            if(o1>o2){
//                return 1;
//            }else if(o1==o2){
//                return 0;
//            }else {
//                return -1;
//            }
//        });
        list.parallelStream().map(s -> {
            inCrease();
            return s;

        }).collect(Collectors.toSet());
        Thread.sleep(1000);
    }
    public static void inCrease() {

        reentrantLock.lock();
        count++;
        System.out.println(count);
        reentrantLock.unlock();
    }
}
