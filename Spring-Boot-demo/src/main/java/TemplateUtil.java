import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/26 0026
 * @Desc :模拟酒店自助机用户入住和推送图片
 **/
public class TemplateUtil {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        pushPhoto("410329199512269633", "wubin_ID");
        System.out.println("------");
        pushUser("2019-03-21 16:00:30", "2019-03-23 18:00:00","白帅雷","410329199512269633");
    }

    private static final int CODE_OK = 200;

    /**
     * 模拟酒店自助机推送用户身份证图片
     *
     * @param id 用户身份证号码
     * @param photoName 图片路径（d:\\images\\test_1.jpg）
     */
    private static void pushPhoto(String id, String photoName) {
        RestTemplate restTemplate = new RestTemplate();
        // 运营云同步用户信息接口
        String cloudUrl = "http://139.159.142.50:3344/fdcservice/addCustomer";
        // 传入参数
        Map<String, String> postMap = new HashMap<>(1);
        postMap.put("id", id);
        InputStream is;
        try {
            is = new FileInputStream(photoName);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 12];
            int n;
            while ((n = is.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            byte[] bytes = out.toByteArray();
            postMap.put("photo", base64ToString(bytes));

            // Header设置
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            HttpEntity<Object> httpEntity = new HttpEntity<Object>(postMap, headers);

            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(cloudUrl,httpEntity, Map.class);
            if (responseEntity.getStatusCodeValue() == CODE_OK) {
                System.out.println(responseEntity.getBody());
                System.out.println("                      ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 模拟酒店自助机用户入住
     *
     * @param checkinTime 入住时间
     * @param checkoutTime 离店时间
     * @param name 用户姓名
     * @param idCard 用户身份证号码
     * @throws IOException
     * @throws TimeoutException
     */
    private static void pushUser(String checkinTime, String checkoutTime, String name, String idCard) throws IOException, TimeoutException {
        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
        connectionFactory.setHost("139.159.140.8");
        connectionFactory.setPort(45672);
        connectionFactory.setUsername("chh_checkin_person");
        connectionFactory.setPassword("chh_checkin_person@3008");
        connectionFactory.setVirtualHost("/chh_checkin_person");
        //3.通过connectionFactory创建一个连接connection
        Connection connection = connectionFactory.newConnection();
        //4.通过connection创建一个频道channel
        Channel channel = connection.createChannel();
        //5.通过channel指定一个队列
        channel.queueDeclare("chh_checkin_person", true, false, false, null);

        Map<String, Object> map1 = new HashMap<>(1);
//        File file = new File("D:\\朗澈科技\\白帅雷在贵阳出差\\img_crop_201\\liuxu_ID.jpg");
//        BufferedImage bufferedImage = ImageIO.read(file);
//        ByteArrayOutputStream buf = new ByteArrayOutputStream((int) file.length());
//        ImageIO.write(bufferedImage, "jpg", buf);
//        byte[] bytes = buf.toByteArray();
//        String imageTime = DateTimeUtil.getFormatTime(System.currentTimeMillis());
        map1.put("age", "22");
        map1.put("sex", "男");
        map1.put("checkinTime", checkinTime);
        map1.put("checkoutTime", checkoutTime);
        map1.put("hotelName", "宿Hotel酒店");
        map1.put("name", name);
        map1.put("idCard", idCard);

        String string = JSONObject.toJSONString(map1);

        //6.通过channel向队列中添加消息，第一个参数是转发器，使用空的转发器（默认的转发器，类型是direct）
        channel.basicPublish("chh_checkin_person", "chh_checkin_person", null, string.getBytes());
        System.out.println("发送了一条消息:" + map1);
        //7.关闭频道
        channel.close();
        //8.关闭连接
        connection.close();
    }

    public static String base64ToString(byte[] bytes) {
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(bytes);
    }
}
