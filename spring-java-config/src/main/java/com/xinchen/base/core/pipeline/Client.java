package com.xinchen.base.core.pipeline;

import com.xinchen.base.core.vo.Request;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/10 23:55
 */
@Service
public class Client {
    @Resource
    private ApplicationContext context;


    public void mockedClient() {
        // request一般是通过外部调用获取
        Request request = new Request("测试");
        Pipeline pipeline = newPipeline(request);
        try {
            pipeline.fireTaskReceived();
            pipeline.fireTaskFiltered();
            pipeline.fireTaskExecuted();
        } finally {
            pipeline.fireAfterCompletion();
        }
    }


    private Pipeline newPipeline(Request request) {
        return context.getBean(DefaultPipeline.class, request);
    }

}
