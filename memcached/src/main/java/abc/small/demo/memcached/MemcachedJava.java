package abc.small.demo.memcached;

import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

/**
 * @author Jim
 */
public class MemcachedJava {

    public static void main(String[] args) {
        // 本地连接 Memcached 服务
        try {
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("10.79.0.53", 11211));
            System.out.println("Connection to server successful.");

            // 添加数据
            Future fo = memcachedClient.set("runoob", 900, "Free Education");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 使用 get 方法获取数据
            System.out.println("runoob value in cache - " + memcachedClient.get("runoob"));

            System.out.println(memcachedClient.get("configruation_sysCode_izybg"));

            // 关闭连接
            memcachedClient.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
