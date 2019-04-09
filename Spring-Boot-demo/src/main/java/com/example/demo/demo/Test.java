package com.example.demo.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/12/12 0012
 * @Desc :
 **/
public class Test {
    public Test() throws IOException, TimeoutException {
        Consumer consumer = new Consumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");

        for (int i=0; i<100; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message number " + i + "send.");
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        new Test();
    }
}
