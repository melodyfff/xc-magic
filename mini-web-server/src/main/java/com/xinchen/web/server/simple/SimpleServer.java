package com.xinchen.web.server.simple;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 简单web服务器例子
 *
 * 使用方式: SimpleServer.start()
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 16:34
 */
@Slf4j
public class SimpleServer {

    private static Executor pool = Executors.newFixedThreadPool(3);

    public static void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        log.info("web server starting...");

        for (;;) {
            // accept socket
            Socket socket = serverSocket.accept();
            pool.execute(new DefaultHandler(socket));
        }
    }

    private static class DefaultHandler implements Runnable {
        Socket socket;

        public DefaultHandler(Socket socket) throws IOException {
            if (null == socket){
                throw new IOException("Socket is null.");
            }
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = this.socket.getInputStream();
                outputStream = this.socket.getOutputStream();

                // http request
                StringBuilder httpRequest = new StringBuilder();
                // 存储inputStream中读取的数据
                byte[] httpRequestByte = new byte[1024];
                int length ;
                if ((length = inputStream.read(httpRequestByte))>0){
                    httpRequest.append(new String(httpRequestByte, 0, length));
                }
                log.info("accept http request: \n{}",httpRequest.toString());


                // http response
                StringBuffer sb = new StringBuffer()
                        .append("HTTP/1.1 200 OK\n")
                        .append("Content-type:text/html\n")
                        .append("\r\n")
                        .append("<html><head><title>Hello Web SimpleServer</title></head><body>Hello World!</body></html>");
                outputStream.write(sb.toString().getBytes());

            } catch (IOException e) {
                log.error("SimpleServer Error :", e);
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    log.error("SimpleServer Close Error :", e);
                }
            }

        }
    }

}
