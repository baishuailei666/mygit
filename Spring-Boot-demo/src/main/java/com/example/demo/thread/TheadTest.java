package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/27 0027
 * @Desc :
 **/
public class TheadTest {
    public static void main(String[] args) {
        // 创建Callable对象
        Callable<Integer> callable = new MyCallable();
        // 使用FutureTask来包装Callable对象
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        for (int i=0; i<100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                System.out.println("start");
                // FutureTask对象作为Thread对象的target创建新的线程
                Thread thread = new Thread(futureTask);
                // 线程进入就绪状态
                thread.start();
            }
        }

        System.out.println("主线程循环完毕!");

        try {
            // 取得新创建的新线程中的call()方法返回的结果
            int sum = futureTask.get();
            System.out.println("sum." + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyCallable implements Callable<Integer> {
    private int i=0;
    /**
     * 与run()方法不同的是，call()放具有返回值
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (; i<100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            sum += i;
        }
        return sum;
    }
}
