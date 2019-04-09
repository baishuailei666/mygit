package com.example.demo.demo;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/12/12 0012
 * @Desc :
 **/
public class Consumer extends EndPoint implements Runnable, com.rabbitmq.client.Consumer{
    public Consumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    @Override
    public void run() {
        try {
            channel.basicConsume(endPointName,true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String s) {
        System.out.println("Consumer." + s + "registered");
    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope
            , AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);
        assert map != null;
        System.out.println("Message Number." + map.get("message number") + "received.");
    }
}
