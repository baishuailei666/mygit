package com.example.demo.entity;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/12/12 0012
 * @Desc :消息生产者
 **/
public class Producer {
    private final static String QUEUE_NAME = "rabbitMQ.test";

    public void produce(String message) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置RabbitMQ相关信息
        factory.setHost("localhost");
        // 创建一个新连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();

        // 声明一个队列
        // queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
        // 第三个参数为是否独占队列（创建者可以使用的私有队列了，断开后自动删除）、第四个参数为当前所有消费者客户端
        // 连接断开时是否自动删除队列，第五个参数为队列的其他参数。
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 发送消息到队列中
        // basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、
        // 第三个参数为消息的其他属性、第四个参数为发送消息的主体
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
        //System.out.println("生产者：" + message);

        channel.close();
        connection.close();
    }
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Producer producer = new Producer();
        for (int i=0; i<10; i++) {
            producer.produce("Hello_" + i);
        }

    }
}
