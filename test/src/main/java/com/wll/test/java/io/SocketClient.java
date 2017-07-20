package com.wll.test.java.io;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by wll on 17-7-20.
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9500);
        new Chat(socket).start();
    }
}
