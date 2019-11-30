package com.smalldemo.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author Jim
 */
public class PipeTest {

    @Test
    public void test1() throws IOException {
        // 1. 获取管道
        Pipe pipe = Pipe.open();

        // 2. 将缓冲区中的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sink = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());
        buf.flip();
        sink.write(buf);

        // 3. 读取缓冲区中的数据
        Pipe.SourceChannel source = pipe.source();
        buf.flip();
        int len = source.read(buf);
        System.out.println(new String(buf.array(), 0, len));

        source.close();
        sink.close();
    }
}
