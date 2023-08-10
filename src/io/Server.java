package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author qinwenlong
 * @Date 2021/11/14
 **/
public class Server {
    public static void main(String[] args) throws Exception {
        NIO();
    }

    private static void BIO() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("客户端来了,port"+socket.getPort());
            new Thread(
                    ()->{
                        try {
                            InputStream inputStream = socket.getInputStream();
                            byte[] buffer = new byte[8192];
                            int len;
                            while ((len= inputStream.read(buffer))!=-1) {
                                System.out.println(new String(buffer,0,len));
                                OutputStream outputStream = socket.getOutputStream();
                                byte[] bytes = "hello client".getBytes();
                                outputStream.write(bytes);
                                outputStream.flush();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
            ).start();
        }
    }

    public static void NIO() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8090));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.write(ByteBuffer.wrap("hello client".getBytes()));
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    byte[] array = byteBuffer.array();
                    System.out.println("服务端接收消息:"+new String(array));
                    channel.write(ByteBuffer.wrap(array));
                }
                iterator.remove();
            }
        }
    }
}
