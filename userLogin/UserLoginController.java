package web;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import util.userlogin.Jwt;
import util.userlogin.SessionManageListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录模块
 * @date 2018-03-07
 * @author 白帅雷
 */
@Controller
@RequestMapping(value = "/cms/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    /**
     * 添加一个记录器
     */
    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 用户登录验证
     * @param request
     * @param response
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestBody Map<String,String> userForLogin , HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,String> responseMap = new HashMap<>(1);

        /**
         * 传进来的user登录信息
         */
        String userLoginName = userForLogin.get("userLoginName");
        String userPassWord = userForLogin.get("userPassWord");

        /**
         * 数据库中的user登录信息
         */
        User user = null;
        try {
            user = this.userService.getUserLogin(userLoginName);
        } catch (Exception e) {
            logger.error("Reading the database failure");
        }
        String dbUserLoginName = user.getUserLoginName();
        String dbUserPassWord = user.getUserPwd();

        /**
         * 判断用户是否存在
         */
        if (dbUserLoginName.equals(userLoginName) && dbUserPassWord.equals(userPassWord)){
            /**
             * 基于Jwt的token,并添加token过期时间12*60分钟
             */
            long expiredTimeAt = 12L * 60L * 60L * 1000L;
            String token = Jwt.sign(responseMap, expiredTimeAt);
            if (token != null) {
                responseMap.put("token", token);
                user.setToken(token);
                System.out.println(user.getToken());
            }

            /**
             * 判断是否登录 注：使用User而不是String，是因为会出现
             * java.lang.String cannot be cast to entity.User异常
             */
            User userLogin = new User();
            userLogin.setUserLoginName(userLoginName);
            Boolean hasLogin = SessionManageListener.checkIfHasLogin(userLogin);
            if (hasLogin) {
                String confirm = request.getHeader("continue");
                if (confirm != null){
                    System.out.println(userLoginName + "已经登录了");
                    responseMap.put("202","当前用户已经登录了！是否继续登录？");
                    response.setStatus(202);
                    SessionManageListener.removeUserSession(userLogin.getUserLoginName());
                    SessionManageListener.removeSession(request.getSession().getId());
                    request.getSession().setAttribute("user",userLogin);
                    //把当前用户封装的session按sessionID和session进行键值封装，添加到静态变量map中。
                    SessionManageListener.addUserSession(request.getSession());
                    user.setUserCount(user.getUserCount()+1);
                    this.userService.updateUserLogin(user);
                }

            } else {
                // 如果没有重复登录，则将该登录的用户信息添加入session中
                request.getSession().setAttribute("user",userLogin);
                // 比较保存所有用户session的静态变量中，是否含有当前session的键值映射，如果含有就删除
                if (SessionManageListener.containsKey(request.getSession().getId())){
                    SessionManageListener.removeSession(request.getSession().getId());
                }
                //把当前用户封装的session按sessionID和session进行键值封装，添加到静态变量map中。
                SessionManageListener.addUserSession(request.getSession());
                user.setUserCount(user.getUserCount()+1);
                this.userService.updateUserLogin(user);
                responseMap.put("201", userLoginName + "当前用户登录成功！");
                response.setStatus(201);
            }

            System.out.println(SessionManageListener.getSessionMap());
            System.out.println(SessionManageListener.getUserSessionMap());
            System.out.println("----------***----------");
        } else {
            responseMap.put("401","输入不正确！请重新登录！");
            response.setStatus(401);
        }
        return responseMap;
    }

    /**
     * 用户退出登录session失效
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public void logout(@RequestBody Map<String,String> userForLogout ,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(userForLogout.get("userLoginName") + "退出了");
        request.getSession().invalidate();
    }

/**
     * 同一浏览器用户再次打开登录页面,验证token session是否有效并默认用户进入系统
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public void check(@RequestBody Map<String,String> userForCheck, HttpServletRequest request, HttpServletResponse response) throws SQLException{
        String sendToken = userForCheck.get("token");
        String sendUserLoginName = userForCheck.get("userLoginName");
        String dbToken = null;
        try {
            dbToken = userService.getToken(sendUserLoginName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User unSign = Jwt.unSign(sendToken, User.class);
        /**
         * 验证sendToken
         */
        if (unSign != null){
            /**
             * sendToken和dbToken对比
             */
            if (sendToken.equals(dbToken)) {
                /**
                 * session没有过期
                 */
                if (request.getSession(false) != null) {
                    response.setStatus(201);
                    System.out.println("1");
                }
                else {
                    userService.deleteToken(sendToken);
                    response.setStatus(400);
                    System.out.println("2");
                }
            }
            else {
                response.setStatus(400);
                System.out.println("3");
            }
        } else {
            userService.deleteToken(sendToken);
            response.setStatus(400);
            System.out.println("4");
        }
    }
}
