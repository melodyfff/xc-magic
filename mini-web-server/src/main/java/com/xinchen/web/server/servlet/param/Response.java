package com.xinchen.web.server.servlet.param;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 17:59
 */
public class Response {

    private final OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuffer sb = new StringBuffer()
                .append("HTTP/1.1 200 OK\n")
                .append("Content-type:text/html\n")
                .append("\r\n")
                .append("<html><head><title>Hello Web SimpleServer</title></head><body>Hello World!<br>")
                .append(content)
                .append("</body></html>");
        outputStream.write(sb.toString().getBytes());
        outputStream.close();
    }
}
