package com.dwd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 描述：
 *
 * @author duanweidong
 * @version 1.0
 * @date 2019/10/31 下午3:26
 */
public class Server {
  private Selector selector;
  private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
  private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
  String str;
  public void start() throws IOException {
    //打开服务器套接字通道
    ServerSocketChannel ssc = ServerSocketChannel.open();
    //服务器配置为非阻塞
    ssc.configureBlocking(false);
    //进行服务器的绑定
    ssc.bind(new InetSocketAddress("172.18.188.53", 8001));

    //通过open()方法找到Selector.
    selector = Selector.open();
    //注册到selector，等待链接
    ssc.register(selector, SelectionKey.OP_ACCEPT);

    while (!Thread.currentThread().isInterrupted()) {
      selector.select();
      Set<SelectionKey> keys = selector.selectedKeys();
      Iterator<SelectionKey> keyIterator = keys.iterator();
      while (keyIterator.hasNext()) {
        SelectionKey key = keyIterator.next();
        if (!key.isValid()) {
          continue;
        }
        if (key.isAcceptable()) {
          accept(key);
        } else if (key.isReadable()) {
          read(key);
        } else if (key.isWritable()) {
          write(key);
        }
        keyIterator.remove();//该事件已经处理，可以丢弃
      }
    }
  }

  private void write(SelectionKey key) throws IOException {
    SocketChannel writeChannel = (SocketChannel)key.channel();
    sendBuffer.clear();
    sendBuffer.put(str == null ? "".getBytes() : str.getBytes());
    sendBuffer.flip();
    writeChannel.write(sendBuffer);
//    writeChannel.register(selector, SelectionKey.OP_READ);
  }

  private void read(SelectionKey key) throws IOException {
    SocketChannel socketChannel = (SocketChannel)key.channel();
    readBuffer.clear();
    int numReads = 0;
    try {
      numReads = socketChannel.read(readBuffer);
    } catch (Exception e) {
      System.out.println("close");
      socketChannel.close();
      return;
    }
    if(numReads>0)
      str = new String(readBuffer.array(), 0, numReads);
    else
    {socketChannel.close();return;}
    System.out.println(str);
  }

  private void accept(SelectionKey key) throws IOException {
    ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
    SocketChannel clientChannel = ssc.accept();
    clientChannel.configureBlocking(false);
    clientChannel.register(selector, SelectionKey.OP_READ);
    System.out.println("a new client connected" + clientChannel.getRemoteAddress());
  }

  public static void main(String[] args) throws IOException {
    System.out.println("server started ....");
    new Server().start();
  }
}
