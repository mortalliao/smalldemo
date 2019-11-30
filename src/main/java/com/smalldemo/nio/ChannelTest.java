package com.smalldemo.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author Jim
 *
 * <pre>
 *
 *      Channel通道
 *
 *      由 java.nio.channels包定义的
 *      Channel表示IO源与目标打开的连接, 类似与传统的"流"
 *      只不过Channel本身不能直接访问数据, Channel只能与Buffer进行交互
 *
 *          物理磁盘    CPU     内存
 *
 * 1. 通道(Channel): 用于源节点与目标节点的连接.
 *      在Java NIO中负责缓冲区数据的传输, Channel本身不存储数据, 因此需要配合缓冲区进行传输
 *
 * 2. 通道的主要实现类
 *      java.nio.channels.Channel 接口:
 *          |-- FileChannel
 *          |-- SocketChannel
 *          |-- ServerSocketChannel
 *          |-- DatagramChannel
 *
 * 3. 获取通道
 *      1. Java 针对支持通道的类提供了 getChannel() 方法
 *          本地IO:
 *              FileInputStream/FileOutputStream
 *              RandomAccessFile
 *
 *          网络IO:
 *              Socket
 *              ServerSocket
 *              DatagramSocket
 *
 *      2. 在 JDK 1.7 中的NIO.2 针对各个通道提供了静态方法 open()
 *
 *      3. 在 JDK 1.7 中的NIO.2 的Files 工具类的 newByteChannel()
 *
 * 4. 通道之间的数据传输
 *      transferForm()
 *      transferTo()
 *
 *  5. 分散(Scatter)与聚集(Gather)
 *      分散读取(Scattering Reads) : 将通道中的数据分散到多个缓冲区中
 *      聚集写入(Gathering Writes) : 将多个缓冲区中的数据聚集到通道中
 *
 *  6. 字符集: Charset
 *      编码: 字符串 -> 字节数组
 *      解码: 字节数组 -> 字符串
 *
 * </pre>
 */
public class ChannelTest {

    /**
     * 1. 利用通道完成文件的复制(非直接缓冲区)
     */
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");

            // 1. 获取管道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 2. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 3. 将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                // 切换读取数据的模式
                buf.flip();
                // 将缓冲区中的数据写入通中
                outChannel.write(buf);
                // 清空缓冲区
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        // 内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间: " + (end - start));
    }

    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    /**
     * 分散和聚集
     */
    @Test
    public void test4() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        // 1. 获取通道
        FileChannel channel1 = randomAccessFile.getChannel();

        // 2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        // 3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        // 按顺序依次填满
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("---------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 4. 聚集写入
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = randomAccessFile1.getChannel();

        channel2.write(bufs);

        channel2.close();
        randomAccessFile1.close();
        channel1.close();
        randomAccessFile.close();
    }

    /**
     * 字符集
     */
    @Test
    public void test5() {
        SortedMap<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * 字符集
     */
    @Test
    public void test6() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");

        // 获取编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();

        // 获取解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("字符集");
        charBuffer.flip();

        // 编码
        ByteBuffer buf = charsetEncoder.encode(charBuffer);

        for (int i = 0; i < 6; i++) {
            System.out.println(buf.get());
        }

        // 解码
        buf.flip();
        CharBuffer cb = charsetDecoder.decode(buf);
        System.out.println(cb.toString());

        System.out.println("------------------------");

        buf.flip();
        Charset cs2 = Charset.forName("UTF-8");
        CharBuffer charBuffer1 = cs2.decode(buf);
        System.out.println(charBuffer1.toString());

    }

}
