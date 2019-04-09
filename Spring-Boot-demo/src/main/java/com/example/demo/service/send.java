package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/8 0008
 * @Desc :
 **/
public class send {
    public static void main(String[] argv) throws IOException, TimeoutException {

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
        //5.通过channel指定一个队列
        channel.queueDeclare("test_json", false, false, false, null);

        InputStream is = new FileInputStream("D:\\sdkimage\\bue.jpg");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 12];
        int n;
        while ((n = is.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        String str = null;
        byte[] bytes = out.toByteArray();
        if (bytes == null) {
            System.out.println("===");
        } else {
            str = new String(bytes);
        }


        //发送的消息
        Map<String, Object> map = new HashMap<>(1);
        map.put("hotel_id", ReadFileUtil.readFile().get("hotelId"));
        map.put("hotel_name", ReadFileUtil.readFile().get("hotelName"));
        map.put("other", base64ToString(bytes));
//        map.put("age", "22");
//        map.put("checkinTime", "2019:03:08 15:40:26");
//        map.put("checkoutTime", "2019:03:09 12:00:00");
//        map.put("hotelId", "4201870005");
//        map.put("name", ReadFileUtil.readFile().get("hotelName"));
//        map.put("sex", "男");
//        map.put("idCard", "32622196108040096");
        String requestInfo = JSONObject.toJSONString(map);

        //6.通过channel向队列中添加消息，第一个参数是转发器，使用空的转发器（默认的转发器，类型是direct）
        channel.basicPublish("", "test_json", null, requestInfo.getBytes());
        System.out.println("添加了一条消息:" + map);
        //7.关闭频道
        channel.close();
        //8.关闭连接
        connection.close();
    }

    public static String getMapToString(Map<String, Object> map) {
        Set<String> keySet = map.keySet();
        // 将set集合转换成数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        // 给数组进行排序（升序）
        Arrays.sort(keyArray);
        // 因为String拼接效率会很低，所以转用StringBuilder。
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<keyArray.length;i++) {
            // 参数值为空，则不参与签名
            if (map.get(keyArray[i]).toString().trim().length() > 0) {
                stringBuilder.append(keyArray[i]).append("=").append(map.get(keyArray[i]).toString().trim());
            }
            if(i != keyArray.length-1){
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }


    public static String base64ToString(byte[] bytes) {
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(bytes);
    }

}
