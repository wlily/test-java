package com.wll.test.java.io;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by wll on 17-7-20.
 */
public class Chat {
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public Chat(Socket socket) throws IOException {
        this.socket = socket;
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }

    public void start(){
        new Thread(new ReadThread()).start();
        new Thread(new WriteThread()).start();
    }

    class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    //使用read方法读取 没问题
                    byte[] b = new byte[10240];
                    int len = is.read(b, 0, b.length);
                    String str3 = new String(b, 0, len);
                    System.out.println(str3);

                    //使用BufferedReader有问题
//                    BufferedReader buff = new BufferedReader(new InputStreamReader(is));
//                    String str = null;
//                    while ((str = buff.readLine()) != null) {
//                        System.out.println("---");
//                        System.out.println(str);
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class WriteThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Scanner sc = new Scanner(System.in);
                byte[] byt = sc.next().getBytes();
                try {
                    os.write(byt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
