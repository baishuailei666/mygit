package com.example.demo.entity;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/12/12 0012
 * @Desc :
 **/
public class Consumers {
    private final static String QUEUE_NAME = "rabbitMQ.test";

    public void consume()throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置RabbitMQ属性
        factory.setHost("localhost");
        ExecutorService es = Executors.newFixedThreadPool(5);
        // 创建一个连接
        Connection connection = factory.newConnection(es);
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("消费者处于等待中...");

        //这个是每次只处理一条数据，只有接收到ack确认码，才去拿取下一条消息
        channel.basicQos(0,1,false);
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer =  new DefaultConsumer(channel)    {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                System.out.println(Thread.currentThread().getName() + "----id." + Thread.currentThread().getId());
                String routingKey = envelope.getRoutingKey();
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("routingKey." + routingKey + ",deliveryTag." + deliveryTag);
                String message = new String(body, "UTF-8");
                System.out.println("消费者：'" + message + "'");

                //手动返回确认码
                channel.basicAck(envelope.getDeliveryTag(), true);
            }
        };
        // true自动回复队列应答, false手動確認 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME,false,consumer);

    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Consumers consumers = new Consumers();
        consumers.consume();
//        consumers.consume1();
    }

    public void consume1() throws IOException, TimeoutException, InterruptedException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置RabbitMQ属性
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明要关注的队列 主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare("rabbitMQ.test",false,false,false,null);
        System.out.println("消费者处于等待中...");

        // 创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定消费队列 自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume("rabbitMQ.test",true,consumer);
        while (true) {
            //消费者程序运行开着 如果生产者新增了数据会自动获取

            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Received " + message );
        }

    }
}
