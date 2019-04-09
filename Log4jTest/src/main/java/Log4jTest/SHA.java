package Log4jTest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA(Secure Hash Algorithm,安全散列算法)，数字签名等密码学应用中重要的工具，
 * 被广泛地应用于电子商务等信息安全领域。虽然，SHA与MD5通过碰撞法都被破解了，
 * 但是SHA任然是公认的安全加密算法，较之MD5安全。
 */
public class SHA {
    public static final String KEY_SHA = "SHA";
    public static String getResult(String inputStr) {
        BigInteger sha = null;
        System.out.println("======加密前====== " + inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            System.out.println("sha加密后：" + sha.toString(32));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }

    public static void main(String[] args) {
        String inputStr = "加密算法测试";
        System.out.println(getResult(inputStr));
    }
}
