package com.xinchen.web.server.servlet;

import com.xinchen.web.server.servlet.param.Request;
import com.xinchen.web.server.servlet.param.Response;

import java.io.IOException;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 19:46
 */
public class DefaultServlet extends AbstractServlet{
    @Override
    protected void doGet(Request request, Response response) {
        try {
            response.write("DefaultServlet return.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(Request request, Response response) {
        try {
            response.write("DefaultServlet return.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
