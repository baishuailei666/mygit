//package com.example.demo.demo.rabbitmq;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
///**
// * @Auther :baisl
// * @Email :baishuailei@xlauncher.io
// * @Date :2018/12/18 0018
// * @Desc :
// **/
//@Configuration
//public class rabbitmqConfig {
//    @Value("${mq.rabbit.address}")
//    String address;
//    @Value("${mq.rabbit.username}")
//    String username;
//    @Value("${mq.rabbit.password}")
//    String password;
//    @Value("${mq.rabbit.virtualHost}")
//    String mqRabbitVirtualHost;
//    @Value("${mq.rabbit.exchange.name}")
//    String exchangeName;
//    @Value("${mq.rabbit.size}")
//    int queueSize;
//    @Value("${mq.concurrent.consumers}")
//    int concurrentConsumers;
//    @Value("${mq.prefetch.count}")
//    int prefetchCount;
//
//    /**
//     * 创建mq连接
//     * @return
//     */
//    @Bean(name = "connectionFactory")
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost(mqRabbitVirtualHost);
//        connectionFactory.setPublisherConfirms(true);
//        //该方法配置多个host，在当前连接host down掉的时候会自动去重连后面的host
//        connectionFactory.setAddresses(address);
//        return connectionFactory;
//    }
//
//
//    /**
//     * 创建监听器，监听队列
//     * @param handleService
//     * @return
//     * @throws AmqpException
//     * @throws IOException
//     */
//    @Bean
//    public SimpleMessageListenerContainer mqMessageContainer(HandleService handleService) throws AmqpException, IOException {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//        container.setQueueNames("hello");
//        container.setExposeListenerChannel(true);
//        //设置每个消费者获取的最大的消息数量
//        container.setPrefetchCount(prefetchCount);
//        //消费者个数
//        container.setConcurrentConsumers(concurrentConsumers);
//        //设置确认模式为手工确认
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        //监听处理类
//        container.setMessageListener(handleService);
//        return container;
//    }
//
//}
