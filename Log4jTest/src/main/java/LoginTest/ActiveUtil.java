//package LoginTest;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import service.UserService;
//
//import java.util.Date;
//
///**
// * 单例线程模式
// * 用户活跃状态工具类
// * @date 2018-03-01
// * @author baishuailei
// */
//@Component
//public class ActiveUtil extends Thread{
//    @Autowired
//    UserService userService;
//
//    private static ActiveUtil instance;
//
//    private String token;
//
//    /**
//     * 最近活跃的时间
//     */
//    public Date lastActiveTime = new Date();
//    /**
//     * 10分钟未活跃则提示
//     */
//    public long remindTime = 1L * 60L * 1000L;
//
//    public ActiveUtil(){
//
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public static ActiveUtil getInstance(String token){
//        if (instance == null){
//            instance = new ActiveUtil();
//        }
//        instance.token = token;
//        return instance;
//    }
//
//    public static ActiveUtil getInstance(){
//        if (instance == null){
//            instance = new ActiveUtil();
//        }
//        return instance;
//    }
//    @Override
//    public void run(){
//        Date now = new Date();
//        long nowTime = now.getTime();
//        if (nowTime - lastActiveTime.getTime() > remindTime) {
//            // 用户在规定时间内不活跃，销毁token，即置为null
//            try {
//                System.out.println("用户不活跃！请重新登录！");
//                userService.deleteToken(token);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                // 设置sleep5000毫秒
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
