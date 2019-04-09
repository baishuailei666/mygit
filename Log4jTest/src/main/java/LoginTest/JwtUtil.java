package LoginTest;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;


/**
 * 使用auth0 3.1.0编码、解码token
 * @author Administrator
 */
public class JwtUtil {
//    /**
//     * 公用密钥，保存在服务器，客户端是不会知道密钥的，以防止被攻击
//     */
//    public static String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
//
//    /**
//     * 编码token
//     * @return
//     * @throws Exception
//     */
//    public static String createToken() throws Exception{
//        // 签发时间
//        Date iatDate = new Date();
//        //  过期时间 1分钟过期
//        Calendar nowTime = Calendar.getInstance();
//        nowTime.add(Calendar.MINUTE,1);
//        Date expiresDate = nowTime.getTime();
//
//        Map<String , Object> map = new HashMap<String, Object>();
//        map.put("alg","HS256");
//        map.put("typ","JWT");
//        String token = JWT.create()
//                .withHeader(map)
//                .withClaim("name","payload")
//                .withExpiresAt(expiresDate)
//                .withIssuedAt(iatDate)
//                .sign(Algorithm.HMAC256(SECRET));
//        return token;
//    }
//
//
//    /**
//     * 解码token
//     * @param token
//     * @return
//     * @throws Exception
//     */
//    public static Map<String , Claim> verifyToken(String token) throws Exception{
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
//                .build();
//        DecodedJWT decodedJWT = null;
//        try {
//            decodedJWT = verifier.verify(token);
//    } catch (Exception e) {
//        throw new RuntimeException("身份过期,请重新登录！");
//    }
//        return decodedJWT.getClaims();
//    }
}
