package abc.small.demo.encrypt.test;

import abc.small.demo.encrypt.BCrypt;
import abc.small.demo.encrypt.MD5Util;
import org.apache.commons.codec.digest.Sha2Crypt;
import org.junit.Test;

/**
 * @author Jim
 */
public class EncryptTest {

    @Test
    public void MD5Test() {
        String string = "我是一句话";
        String byteArrayToHexString = MD5Util.byteArrayToHexString(string.getBytes());
        System.out.println(byteArrayToHexString);//e68891e698afe4b880e58fa5e8af9d
    }

    @Test
    public void ShaTest() {
        String string = "我是一句话";
        String sha256Crypt = Sha2Crypt.sha256Crypt(string.getBytes());
        System.out.println(sha256Crypt);
    }

    @Test
    public void BCryptTest() {
        String password = "123456";
        String candidate = "123";
        // 第一次哈希一个password
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());//密文

        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10
        String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(hashed);
        System.out.println(hashed2);
        //密码密文匹配检测
        if (BCrypt.checkpw(candidate, hashed)) {
            System.out.println("It matches");
        } else {
            System.out.println("It does not match");
        }
    }

}
