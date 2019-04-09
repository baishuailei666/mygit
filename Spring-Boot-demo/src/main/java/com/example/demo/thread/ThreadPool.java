package com.example.demo.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/4/1 0001
 * @Desc :
 **/
public class ThreadPool {
    private static ArrayList<String> mapArrayList = new ArrayList<>();
    private static ThreadPool threadPool = null;
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
    private static ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public ThreadPool(){

    }
    public synchronized static ThreadPool getInstance() {
        if (threadPool == null) {
            threadPool = new ThreadPool();
        }
        return threadPool;
    }
    public synchronized void intoList(boolean isPut, String string) {
        if (isPut) {
            for (int i=0; i<5;i++) {
                mapArrayList.add(string + new Random().nextInt() + "+" + i);
            }
        } else {
            dealT();
        }
    }

    private static void doit(String iterator) {
        System.out.println("--do--");
        System.out.println(iterator);
        System.out.println(Thread.currentThread().getId());
        System.out.println("--it--");
    }

    public static void dealT() {
        System.out.println("1." + Thread.currentThread().getId());

        System.out.println("A," + mapArrayList.size());
        System.out.println("iterator.hasNext()");

//            iterator.remove();
//            iterator = mapArrayList.iterator();
//            System.out.println("B," + mapArrayList.size());

    }

    public static void main(String[] args) {
//        TArrayList tArrayList = new TArrayList();
//        Thread thread = new Thread(tArrayList);
//        thread.start();
        Iterator<String> iterator = mapArrayList.iterator();
        threadPool = ThreadPool.getInstance();
        threadPool.intoList(true, "ArrayList-");
        while (iterator.hasNext()) {
            singleThreadPool.execute(()->{
                threadPool = ThreadPool.getInstance();
                threadPool.intoList(false, null);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}


class TArrayList implements Runnable{
    private void task() {
        while (true) {
            ThreadPool threadPool = ThreadPool.getInstance();
            threadPool.intoList(true, "ArrayList-");
            threadPool.intoList(false, null);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        task();
    }
}
