package com.smalldemo.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Jim
 *
 * <pre>
 *
 * 1. 使用NIO完成网络通信的三个核心:
 *
 *      1. 通道Channel :负责连接
 *          java.nio.channels.Channel 接口:
 *              |-- SelectableChannel
 *                  |-- SocketChannel
 *                  |-- ServerSocketChannel
 *                  |-- DatagramChannel
 *
 *                  |-- Pipe.SinkChannel
 *                  |-- Pipe.SourceChannel
 *
 *      2. 缓冲区 Buffer :负责数据的存取
 *
 *      3. 选择器 Selector :是SelectableChannel 的多路复用器, 用于监控SelectableChannel的IO状况
 *
 * </pre>
 */
public class BlockingNIOTest {

    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        // 1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 3. 读取本地文件, 并发送到服务器
        FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        while (fileChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        // 4. 关闭
        socketChannel.close();
        fileChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    public void server() throws IOException {
        // 1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));

        // 3. 获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 4. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5. 接收客户端的数据, 并保存到本地
        FileChannel fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        while (socketChannel.read(buf) != -1) {
            buf.flip();
            fileChannel.write(buf);
            buf.clear();
        }

        // 6. 关闭通道
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }
}
