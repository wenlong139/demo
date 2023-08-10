package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author qinwenlong
 * @Date 2021/11/14
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8090);
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = "hello word".getBytes();
        outputStream.write(bytes);
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[8192];
        int len;
        while ((len= inputStream.read(buffer))!=-1) {
            System.out.println(new String(buffer,0,len));
        }
    }


}
