package abc.small.demo.memcached;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author Jim
 */
public class MemcachedJava {

    public static void main(String[] args) {
        // 本地连接 Memcached 服务
        try {
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            System.out.println("Connection to server successful.");

            Object obj = memcachedClient.get("");
            System.out.println(obj);

            // 关闭连接
            memcachedClient.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
