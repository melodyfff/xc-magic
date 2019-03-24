package com.xinchen.web.server.server;

import com.xinchen.web.server.mapping.ServletMapping;
import com.xinchen.web.server.mapping.ServletMappingConfig;
import com.xinchen.web.server.servlet.AbstractServlet;
import com.xinchen.web.server.servlet.param.Request;
import com.xinchen.web.server.servlet.param.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 服务启动装置
 * 支持多线程处理
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 19:59
 */
@Slf4j
public class Server {
    private static final int PORT = 8080;

    private static Map<String, Class> urlServletMap = new ConcurrentHashMap<>(16);

    /** 支持多线程请求 */
    private static Executor pool = Executors.newFixedThreadPool(3);


    public static void start() throws IOException {
        initServletMapping();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log.info("--------------------------");
        log.info("Web Server is running...");
        log.info("--------------------------");
        while (true){
            Socket socket = serverSocket.accept();
            pool.execute(new DefaultHandler(socket));
        }

    }

    /** 初始化请求映射关系 */
    private static void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappings) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }


    /** 默认处理类 */
    private static class DefaultHandler implements Runnable{

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

                Request request = new Request(inputStream);
                Response response = new Response(outputStream);

                // 分发器分发请求
                dispatch(request,response);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 请求分发器
     * @param request request
     * @param response response
     */
    private static void dispatch(Request request, Response response){
        Class clazz = urlServletMap.get(request.getUrl());
        try {
            AbstractServlet servlet = (AbstractServlet) Class.forName(clazz.getName()).newInstance();
            servlet.service(request,response);
        } catch (IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
