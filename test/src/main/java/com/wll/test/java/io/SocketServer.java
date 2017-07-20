package com.wll.test.java.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wll on 17-7-20.
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        int count = 0;
        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket ssk = new ServerSocket(9500);
        //实现多个连接
        while (true) {
            //2、调用accept()方法开始监听，等待客户端的连接
            Socket sk = ssk.accept();
            count++;
            System.out.println(sk.getRemoteSocketAddress() + "连接成功");
            System.out.println("客户端数量: " + count);

            new Chat(sk).start();
        }
//        System.out.println("停止服务器");
    }

}
