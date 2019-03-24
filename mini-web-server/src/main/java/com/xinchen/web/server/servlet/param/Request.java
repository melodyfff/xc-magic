package com.xinchen.web.server.servlet.param;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 17:20
 */
@Data
@Slf4j
public class Request {
    private String url;
    private String method;

    private final InputStream inputStream;

    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;

        StringBuilder httpRequest = new StringBuilder();

        // 存储inputStream中读取的数据
        byte[] httpRequestByte = new byte[1024];

        int length = 0;
        if ((length = inputStream.read(httpRequestByte)) > 0) {
            // may be: Base64.getEncoder().encodeToString(httpRequestByte)
            httpRequest.append(new String(httpRequestByte,0,length));

            log.info("original http request: {} ",httpRequest);

            String httpHeader = httpRequest.toString().split("\n")[0];
            this.url = httpHeader.split("\\s")[1];
            this.method = httpHeader.split("\\s")[0];
            log.info("wrapped http request: {}",this);

        } else {
            log.warn("non accept.");
        }
    }
}
