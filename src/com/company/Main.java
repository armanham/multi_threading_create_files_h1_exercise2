package com.company;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        //Single Thread
//        long start = System.currentTimeMillis();

//        FileCreator fileCreator = new FileCreator(countDownLatch);
//        fileCreator.start();
//        try {
//            fileCreator.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        long end = System.currentTimeMillis() - start;
//        System.out.println("1 thread : " + end);

        //Multi Threading
        long start1 = System.currentTimeMillis();

        List<Thread> threadList = List.of(
                new FileCreator(countDownLatch),
                new FileCreator(countDownLatch));
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end1 = System.currentTimeMillis() - start1;
        System.out.println("multi threading: " + end1);
    }
}
