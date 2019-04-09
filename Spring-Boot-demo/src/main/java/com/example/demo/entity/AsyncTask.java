package com.example.demo.entity;

import com.rabbitmq.client.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/12/13 0013
 * @Desc :
 **/
@Component
public class AsyncTask{
    private final static String QUEUE_NAME = "rabbitMQ.test";
    public AsyncTask() {

    }

    @Async
    void doReceive(Channel channel) throws InterruptedException, IOException {
        System.out.println("doReceive__" + channel.getChannelNumber());
        Consumer consumer =  new DefaultConsumer(channel)    {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("消费者：'" + message + "'");
            }
        };
        // 自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }

    @Async
    void doReceive1(QueueingConsumer consumer) throws InterruptedException, IOException {
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();

        String message = new String(delivery.getBody());
        System.out.println(Thread.currentThread().getName() + "收到消息'" + message + "'"  + Thread.currentThread().getId());
        System.out.println("getEnvelope." + delivery.getEnvelope());
        System.out.println("getProperties." + delivery.getProperties());
        System.out.println("-------------------------------------");
    }

    public void doit() throws IOException, TimeoutException, InterruptedException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置RabbitMQ属性
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("消费者处于等待中...");

        AsyncTask asyncTask = new AsyncTask();
//        asyncTask.doReceive(channel);

        /* 创建消费者对象，用于读取消息 */
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            /* 读取队列，并且阻塞，即在读到消息之前在这里阻塞，直到等到消息，完成消息的阅读后，继续阻塞循环 */
            System.out.println("true.");
            asyncTask.doReceive1(consumer);
        }
    }
//    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        // 创建连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        // 设置RabbitMQ属性
//        factory.setHost("localhost");
//        // 创建一个连接
//        Connection connection = factory.newConnection();
//        // 创建一个通道
//        Channel channel = connection.createChannel();
//        // 声明要关注的队列
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        System.out.println("消费者处于等待中...");
//
//        AsyncTask asyncTask = new AsyncTask();
////        asyncTask.doReceive(channel);
//
//        /* 创建消费者对象，用于读取消息 */
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//        while (true) {
//            /* 读取队列，并且阻塞，即在读到消息之前在这里阻塞，直到等到消息，完成消息的阅读后，继续阻塞循环 */
//            System.out.println("true.");
//            asyncTask.doReceive1(consumer);
//        }
//    }

}
