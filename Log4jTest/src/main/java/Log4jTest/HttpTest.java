package Log4jTest;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * java模拟Http请求
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/10/8 0008
 * @Desc :java模拟HTTP请求，第一种是HttpUrlConnection发送post请求，第二种是使用HttpClient模拟post请求
 **/
public class HttpTest {

    public static void main(String[] args) {
        HttpTest ht = new HttpTest();
        try {
            Map<String, Object> data = new HashMap<>(1);
            data.put("userLoginName","admin");
            data.put("userPassword","111111");

            System.out.println("sendPost: " + ht.sendPost("http://8.11.0.11:8080/user/login", data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public String sendPost(String url, Map<String, Object> data) throws Exception {
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        String response = "";
        String params;
        System.out.println("url: " + url);
        System.out.println("data: " + data);
        try {
//            // 编码请求参数
//            if (data.size() == 1) {
//                for (String name : data.keySet()) {
//                    sb.append(name).append("=").append(
//                            java.net.URLEncoder.encode(data.get(name),
//                                    "UTF-8"));
//                }
//                params = sb.toString();
//            } else {
//                for (String name : data.keySet()) {
//                    sb.append(name).append("=").append(
//                            java.net.URLEncoder.encode(data.get(name),
//                                    "UTF-8")).append("&");
//                }
//                String temp_params = sb.toString();
//                params = temp_params.substring(0, temp_params.length() - 1);
//            }

            // http url类用这个类来创建连接
            URL httpUrl = null;
            // 创建url
            httpUrl = new URL(url);

            // 建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            // 参数格式，避免出现400参数错误。
            String requestInfo = JSONObject.toJSONString(data);
            // post请求
//            out = new DataOutputStream(conn.getOutputStream());
            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out.write(requestInfo);
            out.flush();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response += lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();
            System.out.println("response: " + response);
        } catch (MalformedURLException e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        // 使用finnally块来关闭输出流、输入流
        finally {
            if (out != null) {
                out.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
        return response;
    }


    /**
     * 将map转换成key1=value1&key2=value2的形式
     * @param map
     * @return
     * @throws Exception
     */
    private static String getStringFromOutput(Map<String, String> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (Map.Entry<String, String> entry:map.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(),"UTF-8"));

        }
        return sb.toString();
    }
}
