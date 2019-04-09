package com.example.demo.thread;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/27 0027
 * @Desc :
 **/
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        // 运行结果正常，生产者生成一个商品，紧接着消费者消费一个商品
        t1.start();
        t2.start();
    }
}

/**
 * 生产者和消费者都要操作的资源
 */
class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name) {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.name = name + "---" + count ++ ;
            System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
            flag = true;
            this.notify();
        }
    }

    public synchronized void out() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...消费者..." + this.name);
            flag = false;
            this.notify();
        }
    }
}


class Producer implements Runnable {
    private Resource resource;
    Producer(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while (true) {
            resource.set("商品");
        }
    }
}

class Consumer implements Runnable {
    private Resource resource;
    Consumer(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        while (true) {
            resource.out();
        }
    }
}