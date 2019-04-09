package LoginTest;

//import entity.User;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import service.UserService;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Date;

/**
 * 用户活跃状态判断
 * a.用户处于活跃状态则执行操作
 * b.用户处于不活跃状态则警告
 * @date 2018-03-02
 * @author 白帅雷
 */
public class SessionUtil extends Thread{

//    /**
//     * 普通类调用bean
//     */
//    private UserService userService = (UserService) new FileSystemXmlApplicationContext("classpath:Spring/spring-*.xml").getBean("userService");
//
//    ApplicationContext applicationContext;
//
//    /**
//     * 构造函数
//     */
//    public SessionUtil(){
//       this.applicationContext = new FileSystemXmlApplicationContext("classpath:Spring/spring-*.xml");
//       this.userService = (UserService) this.applicationContext.getBean("userService");
//    }
//
//    /**
//     * 获取当前请求request
//     * springMVC有把每次响应的request和response对象封装在线程变量里面，可以直接取
//     * @return
//     */
//    public static HttpServletRequest getHttpServletRequest(){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes())
//                .getRequest();
//        return request;
//    }
//
//    /**
//     * 获取当前请求session
//     *
//     * 需要注意的地方是request.getSession()等同于request.getSession(true)，除非我们确定session一定
//     * 存在或者session不存在时明确有创建session的需要，否则尽量使用request.getSession(false)。
//     * 在使用request.getSession()函数，通常在action中检查是否有某个变量/标记在session中。
//     * 这个场景中可能出现没有session存在的情况
//     * @return
//     */
//    public static HttpSession getHttpSession(){
//        return getHttpServletRequest().getSession(true);
//    }
//
//    /**
//     * request
//     */
//    private HttpServletRequest request = SessionUtil.getHttpServletRequest();
//
//    /**
//     * session
//     */
//    private HttpSession session = SessionUtil.getHttpSession();
//
//    /**
//     * 获得请求头中的token
//     *
//     */
//    public String token = request.getHeader("token");
//
//    /**
//     * 添加额外标记用于终止线程
//     * 其中将flag定义为volatile类型，保证flag的可见性，其他线程通过stopTask()修改了flag之后
//     * 本线程能看到修改后的flag的值
//     */
//    private volatile boolean flag = true;
//
//    /**
//     * 说明：线程中有一个flag标记，它的默认值是true，并且提供stopTask()来设置flag标记
//     * 当我们需要终止线程的时候，调用该方法就可以让线程退出while循环。
//     */
//    protected void stopTask(){
//        flag = false;
//    }
//
//    /**
//     * 对token解码判断是否过期
//     */
//    public void unSign() {
//        User unSign = Jwt.unSign(token,User.class);
//        if (unSign == null){
//            try {
//                /**
//                 * token已过期
//                 */
//                System.out.println("1:token已过期");
//                this.userService.changeToken(token);
//                stopTask();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            /**
//             * token未过期
//             */
//            System.out.println("2:token未过期");
//            if (session != null){
//                /**
//                 *  设置当前会话的的失效时间，不会整个web服务的。
//                 *  要注意这个session设置的时间是根据服务器来计算的，而不是客户端。
//                 *  所以如果是在调试程序，应该是修改服务器端时间来测试，而不是客户端。
//                 */
//                session.setMaxInactiveInterval(60);
//                System.out.println("session有效时间" + session.getMaxInactiveInterval());
//                System.out.println("session创建时间" + session.getCreationTime());
//                System.out.println("session上次活跃时间" + session.getLastAccessedTime());
//            } else {
//                try {
//                    this.userService.changeToken(token);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                stopTask();
//            }
//        }
//    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
//        unSign();
//        while (flag) {
//            try {
//                /**
//                 * 方式一：使用session过期时间判断
//                 * session的过期时间计算是从当前session的最后一次请求开始的。
//                 * Session生成后，只要用户继续访问，服务器就会更新Session的最后访问时间，并维护该Session。
//                 * 用户每访问服务器一次，无论是否读写Session，服务器都认为该用户的Session"活跃（active）"了一次。
//                 * 从session不活动的时候开始计算，如果session一直活动，session就总不会过期。
//                 * 从该Session未被访问,开始计时; 一旦Session被访问,计时清 0;
//                 */
//                Date now = new Date();
//                if (now.getTime() - session.getLastAccessedTime() > 60*1000){
//                    /**
//                     * 判断用户活跃状态是否在规定时间内；
//                     * 用户处于活跃状态则继续执行线程终止，否则用户处于不活跃状态将token置为null并终止线程
//                     * a.用户处于不活跃状态
//                     */
//                    System.out.println("5:session不为null，用户不活跃");
//                    this.userService.changeToken(token);
//                    stopTask();
//                } else {
//                    /**
//                     * b.用户处于活跃状态,设置sleep = 10000毫秒
//                     */
//                    System.out.println("6:用户活跃sleep5000" + this);
//                    System.out.println(session.getId());
//                    Thread.sleep(5000);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
