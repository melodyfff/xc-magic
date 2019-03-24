package com.xinchen.web.server.servlet;

import com.xinchen.web.server.servlet.param.Request;
import com.xinchen.web.server.servlet.param.Response;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 18:09
 */
public abstract class AbstractServlet {

    private static final String GET = "GET";
    private static final String POST = "POST";

    protected abstract void doGet(Request request, Response response);

    protected abstract void doPost(Request request, Response response);

    public void service(Request request, Response response) throws NoSuchMethodException {
        if (POST.equalsIgnoreCase(request.getMethod())) {
            doPost(request, response);
        } else if (GET.equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            throw new NoSuchMethodException("not support");
        }
    }
}
