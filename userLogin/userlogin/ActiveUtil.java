package util.userlogin;

import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Iterator;

import static util.userlogin.SessionManageListener.getSessionMapKeySetIt;
import static util.userlogin.SessionManageListener.getUserSessionMap;


/**
 * 用户活跃状态判断
 * a.用户处于活跃状态则执行操作
 * b.用户处于不活跃状态则警告
 * @date 2018-03-02
 * @author 白帅雷
 */
public class ActiveUtil {

    /**
     * 普通类调用bean
     */
    private static UserService userService = (UserService) new FileSystemXmlApplicationContext("classpath:Spring/spring-*.xml").getBean("userService");

    ApplicationContext applicationContext;

    /**
     * 构造函数
     */
    public ActiveUtil(){
       this.applicationContext = new FileSystemXmlApplicationContext("classpath:Spring/spring-*.xml");
       userService = (UserService) this.applicationContext.getBean("userService");
    }

    /**
     * 获取当前请求request
     * springMVC有把每次响应的request和response对象封装在线程变量里面，可以直接取
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 获取当前请求response
     * springMVC有把每次响应的request和response对象封装在线程变量里面，可以直接取
     * @return
     */
    public static HttpServletResponse getHttpServletResponse(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getResponse();
        return response;
    }

    /**
     * request
     */
    private static HttpServletRequest request = ActiveUtil.getHttpServletRequest();

    /**
     * response
     */
    private static HttpServletResponse response = ActiveUtil.getHttpServletResponse();

    /**
     * session
     */
    private static HttpSession session = request.getSession(false);

    /**
     * 获得请求头中的token
     */
    public static String token = request.getHeader("token");

    /**
     * 对token解码判断是否过期
     */
    public static void check() {
        User unSign = Jwt.unSign(token, User.class);
        try {
            if (unSign == null) {
                /**
                 * token已过期
                 */
                userService.deleteToken(token);
                response.setStatus(415);
            } else {
                /**
                 * token未过期
                 */
                if (request.getSession(false) == null){
                    // session 已过期
                    userService.deleteToken(token);
                    response.setStatus(415);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


//    /**
//     * 用于判断并返回状态码
//     * @param response
//     * @return
//     */
//    public static HttpServletResponse check(HttpServletResponse response){
//        int reStatus = 2;
//        ActiveUtil activeUtil = new ActiveUtil();
//        int unRet = activeUtil.unSign();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("- - -");
//                if (unRet != 0){
//                    if (unRet == 1){
//                        // 1：token已过期或错误！
//                        response.setStatus(410);
//                        System.out.println("410");
//                    }
//                    if (unRet == 2){
//                        // 2：session已过期！
//                        response.setStatus(408);
//                        System.out.println("408");
//                    }
//                }
//            }
//        };
//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        // 第二个参数为首次执行任务的延时时间，第三个参数为定时执行的间隔时间
//        service.scheduleAtFixedRate(runnable,30,30, TimeUnit.SECONDS);
//
//        return response;
//    }

}
