package com.example.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/27 0027
 * @Desc :BlockingQueue是一个接口，也是Queue的接口。BlockingQueue具有一个特征：当生产者线程试图向BlockingQueue中放入元素时
 * 如果队列已满，则线程会被阻塞；但消费者线程试图从BlockingQueue中取出元素时，如果队列已空，则该线程会被阻塞。
 * 程序的两个线程通过交替向BlockingQueue中放入元素、取出元素，即可很好地控制线程的通信。
 *      BlockingQueue提供如下两个支持阻塞的方法：
 *          1、put(E e)：尝试把Eu元素放如BlockingQueue中，如果该队列的元素已满，则阻塞该线程。
 *          2、take()：尝试从BlockingQueue的头部取出元素，如果该队列的元素已空，则阻塞该线程。
 *      BlockingQueue继承了Queue接口，当然也可以使用Queue接口中的方法，这些方法归纳起来可以分为如下三组：
 *          1、在队列尾部插入元素，包括add(E e)、offer(E e)、put(E e)方法，当该队列已满时，这三个方法分别会抛出异常、返回false、阻塞队列。
 *          2、在队列头部删除并返回删除的元素。包括remove()、poll()和take()方法，当该队列已空时，这三个方法分别会抛出异常、返回false、阻塞队列。
 *          3、在队列头部取出但不删除元素。包括element()和peek()方法，当队列已空时，这两个方法会抛出异常、返回false。
 *      BlockingQueue接口保护如下5个实现类：
 *          ArrayBlockingQueue：基于数组实现的BlockingQueue队列
 *          LinkedBlockingQueue：基于链表实现的BlockingQueue队列
 *          PriorityBlockingQueue：它并不是保准的阻塞队列，该队列调用remove()、poll()、take()等方法提取出元素时，并不是取出
 *              队列中存在时间最长的元素，而且队列中最小的元素。它判断元素的大小即可根据元素（实现Comparable元素）的本身大小来自然排序，
 *              也可使用Comparator进行定制排序。
 *          SynchronousQueue：同步队列。对该队列的存、取操作必须交替进行。
 *          DelayQueue：它是一个特殊的BlockingQueue，底层基于PriorityBlockingQueue实现，不过，DelayQueue要求集合元素都实现Delay接口
 *              （该接口里只有一个long getDelay()方法），DelayQueue根据集合元素的getDelay()方法的返回值进行排序。
 *
 *
 *      线程池：
 *          合理利用线程池能够带来的三个好处：
 *              1、降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 *              2、提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
 *              3、提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
 *          1、使用Executors工厂类创建线程池
 *              Executor线程池框架的最大优点是：把任务的提交和执行解耦。客户端将要执行的任务封装成Task，然后提交即可。
 *              而Task如何执行客户端则是透明的。具体点讲，提交一个Callable对象给ExecutorService将得到一个Future对象，
 *              调用Future对象的get方法等待执行结果。
 *
 *              ExecutorService是java中对线程池定义的一个接口。接口的实现有两个：
 *                  ThreadPoolExecutor、ScheduledThreadPoolExecutor
 *                  除此之外，ExecutorService还继承了Executor接口（注意区分Executor接口和Executors工厂类）这个接口只有一个execute()，
 *                  使用Executors执行多线程任务的步骤如下：
 *                      *调用Executors类的静态工厂方法创建一个ExecutorService对象，该对象代表一个线程池；
 *                      *创建Runnable实现类或Callable实现类的实例，作为线程执行的任务；
 *                      *调用ExecutorService对象的submit()方法来提交Runnable实例或Callable实例；
 *                      *当不想提交任务时，调用ExecutorService的shutdown()方法来关闭线程池。
 *
 *                      使用Executors的静态工厂类创建线程池的方法如下：
 *                          1、newFixedThreadPool() ：
                                作用：该方法返回一个固定线程数量的线程池，该线程池中的线程数量始终不变，即不会再创建新的线程，也不会销毁已经创建好的线程，自始自终都是那几个固定的线程在工作，所以该线程池可以控制线程的最大并发数。
                                栗子：假如有一个新任务提交时，线程池中如果有空闲的线程则立即使用空闲线程来处理任务，如果没有，则会把这个新任务存在一个任务队列中，一旦有线程空闲了，则按FIFO方式处理任务队列中的任务。
                            2、newCachedThreadPool() ：
                                作用：该方法返回一个可以根据实际情况调整线程池中线程的数量的线程池。即该线程池中的线程数量不确定，是根据实际情况动态调整的。
                                栗子：假如该线程池中的所有线程都正在工作，而此时有新任务提交，那么将会创建新的线程去处理该任务，而此时假如之前有一些线程完成了任务，现在又有新任务提交，那么将不会创建新线程去处理，
                                而是复用空闲的线程去处理新任务。那么此时有人有疑问了，那这样来说该线程池的线程岂不是会越集越多？其实并不会，因为线程池中的线程都有一个“保持活动时间”的参数，通过配置它，
                                如果线程池中的空闲线程的空闲时间超过该“保存活动时间”则立刻停止该线程，而该线程池默认的“保持活动时间”为60s。
                            3、newSingleThreadExecutor() ：
                                作用：该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务。
                            4、newScheduledThreadPool() ：
                                作用：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。
                            5、newSingleThreadScheduledExecutor() ：
                                作用：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。只不过和上面的区别是该线程池大小为1，而上面的可以指定线程池的大小。
                            注：Executors只是一个工厂类，它所有的方法返回的都是ThreadPoolExecutor、ScheduledThreadPoolExecutor这两个类的实例。
 **/
public class BlockingQueueTest {
    public static void main(String[] args) {
        // 创建一个容量为1的BlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        // 启动3个生产者线程
        new Producer1(blockingQueue).start();
        new Producer1(blockingQueue).start();
        new Producer1(blockingQueue).start();

        // 启动一个消费者消费
        new Consumer1(blockingQueue).start();

    }
}

class Producer1 extends Thread {
    private BlockingQueue<String> b;
    public Producer1(BlockingQueue<String> b) {
        this.b = b;
    }
    @Override
    public synchronized void run() {
        String[] strings = new String[] {
                "JAVA",
                "STRUTS",
                "SPRING"
        };
        for (int i=0; i<9999999; i++) {
            System.out.println(getName() + "生产者准备生产集合元素!");
            try {
                b.put(strings[i%3]);
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产者完成：" + b);
        }
    }
}

class Consumer1 extends Thread {
    private BlockingQueue<String> b;
    public Consumer1(BlockingQueue<String> b) {
        this.b = b;
    }
    @Override
    public synchronized void run() {
        while (true) {
            System.out.println(getName() + "消费者准备消费集合元素!");
            try {
                sleep(1000);
                // 尝试取出元素，如果队列已空，则线程阻塞
                b.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费者消费完：" + b);
        }
    }
}