package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;
import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/8 0008
 * @Desc :
 **/
public class save {

    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {
        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
        connectionFactory.setHost("8.16.0.6");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        //3.通过connectionFactory创建一个连接connection
        Connection connection = connectionFactory.newConnection();
        //4.通过connection创建一个频道channel
        Channel channel = connection.createChannel();
        //5.通过channel指定队列
        channel.queueDeclare("fgs_fis_img", false, false, false, null);
        //与发送消息不同的地方
        //6.创建一个消费者队列consumer,并指定channel
        Consumer consumer =  new DefaultConsumer(channel)    {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                String message = new String(body, "UTF-8");
                System.out.println("消费者：'" + message);

                Map mapA = JSONObject.parseObject(message,Map.class);
                System.out.println(mapA);

//                System.out.println("map* " + getStringToMap(message));
//
//                String other = getStringToMap(message).get("other");
//
//                byte[] bytes = stringToByte(other);
//
//                try {
//                    // byte数组保存图片
//                    FileImageOutputStream imageOutput = new FileImageOutputStream(new File("D:\\sdkimage\\rabbitmq-" + System.currentTimeMillis() + ".jpg"));
//                    imageOutput.write(bytes, 0, bytes.length);
//                    imageOutput.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                //手动返回确认码
                channel.basicAck(envelope.getDeliveryTag(), true);
            }
        };
        //7.为channel指定消费者
        channel.basicConsume("fgs_fis_img", true, consumer);
    }

    public static Map<String, String> getStringToMap(String string) {
        if (string == null || "".equals(string)) {
            return null;
        }
        String[] strings = string.split("&");
        int mapLength = strings.length;
        if ((strings.length % 2) != 0) {
            mapLength = mapLength + 1;
        }

        Map<String, String> map = new HashMap<>(mapLength);
        for (int i=0; i<strings.length;i++) {
            String[] strArray = strings[i].split("=");
            map.put(strArray[0], strArray[1]);
        }
        return map;
    }

    /**
     * base64字符串转换成图片
     * @param imgStr		base64字符串
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static byte[] Base64ToImage(String imgStr) {
        // 对字节数组字符串进行Base64解码并生成图片
        byte[] b = new byte[0];
        if (isEmpty(imgStr)) {

            // 图像数据为空
            return null;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    // 调整异常数据
                    b[i] += 256;
                }
            }

//            OutputStream out = new FileOutputStream(imgFilePath);
//            out.write(b);
//            out.flush();
//            out.close();


        } catch (Exception e) {
            e.getCause();
        }
        return b;
    }

    public static byte[] stringToByte(String string) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(string);
    }

    /**
     * 字符串转二进制
     * @param str 要转换的字符串
     * @return  转换后的二进制数组
     */
    private static byte[] hex2byte(String str) {

        // 字符串转二进制
        if (str == null) {
            return null;
        }
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1) {
            return null;
        }
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证字符串是否为空
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        return input == null || input.equals("") || input.matches(null);
    }

}

