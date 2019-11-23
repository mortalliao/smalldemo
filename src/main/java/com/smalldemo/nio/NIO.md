
# NIO
支持面向缓存区, 基于通道的IO操作

## NIO 与 IO 的区别
IO | NIO
 --- | ---
 面向流(Stream Oriented) | 面向缓存区(Buffer Oriented)
 阻塞IO(Blocking IO) | 非阻塞IO(No Blocking IO)
 无 | 选择器(Selector)
 
```
文件, 磁盘, 网络 -> 输入流 -> 程序 
               <- 输出流 <-
```

NIO
```
文件, 磁盘, 网络 <-> 通道 <-> 程序
```
               
Channel负责传输, Buffer负责存储


## 缓存区(Buffer) 和 通道(Channel)


## 文件通道(FileChannel)


## NIO的非阻塞式网络通信
### 选择器(Selector)
### SocketChannel, ServerSocketChannel, DatagramChannel


## 管道


## JAVA NIO2(Path, Paths, Files)

