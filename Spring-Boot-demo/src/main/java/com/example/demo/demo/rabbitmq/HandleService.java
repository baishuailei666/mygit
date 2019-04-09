//package com.example.demo.demo.rabbitmq;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.QueueingConsumer;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.stereotype.Service;
//
//
///**
// * @Auther :baisl
// * @Email :baishuailei@xlauncher.io
// * @Date :2018/12/18 0018
// * @Desc :
// **/
//@Service
//public class HandleService implements ChannelAwareMessageListener {
//
//    @Override
//    public void onMessage(Message message, Channel channel) throws Exception {
//        /* 创建消费者对象，用于读取消息 */
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//
//        String msg = new String(delivery.getBody());
//        System.out.println("收到消息___" + msg + "'");
//    }
//
////    private ExecutorService executor = Executors.newFixedThreadPool(8);
////    @Override
////    public void onMessage1(Message message, Channel channel) throws Exception {
////
////        executor.execute(new Runnable() {
////            @Override
////            public void run() {
////                // 耗时且复杂的消息处理逻辑
////                complicateHanlde(message);
////            }
////        });
////
////
////    }
////    private void complicateHanlde(Object message) {
////
////    }
//
//}