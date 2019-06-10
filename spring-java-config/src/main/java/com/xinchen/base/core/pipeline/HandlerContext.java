package com.xinchen.base.core.pipeline;

import com.xinchen.base.core.vo.Request;
import com.xinchen.base.core.vo.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/10 22:18
 */
@Component
@Scope("prototype")
public class HandlerContext {
    /** 前一节点 */
    HandlerContext pre;
    /** 后一节点 */
    HandlerContext next;
    /** 当前节点处理具体事务 */
    Handler handler;

    private Task task;


    public void fireTaskReceived(Request request) {
        invokeTaskReceived(next(), request);
    }
    public void fireTaskFiltered(Task task) {
        invokeTaskFiltered(next(), task);
    }

    public void fireTaskExecuted(Task task) {
        invokeTaskExecuted(next(), task);
    }

    public void fireAfterCompletion(HandlerContext ctx) {
        invokeAfterCompletion(next());
    }

    /**
     * 处理接收到任务的事件
     * @param ctx HandlerContext
     * @param request Request 业务请求封装
     */
    static void invokeTaskReceived(HandlerContext ctx, Request request){
        if (null!=ctx){
            try {
                ctx.handler().receiveTask(ctx,request);
            } catch (Throwable e){
                // 捕获处理异常
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    /**
     * 处理执行任务事件
     * @param ctx HandlerContext
     * @param task Task
     */
    static void invokeTaskExecuted(HandlerContext ctx, Task task) {
        if (null != ctx) {
            try {
                ctx.handler().executeTask(ctx, task);
            } catch (Exception e) {
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    /**
     * 处理任务过滤事件
     * @param ctx HandlerContext
     * @param task Task
     */
    static void invokeTaskFiltered(HandlerContext ctx, Task task) {
        if (null != ctx) {
            try {
                ctx.handler().filterTask(ctx, task);
            } catch (Throwable e) {
                ctx.handler().exceptionCaught(ctx, e);
            }
        }
    }

    static void invokeAfterCompletion(HandlerContext ctx) {
        if (null != ctx) {
            ctx.handler().afterCompletion(ctx);
        }
    }

    private HandlerContext next(){
        return next;
    }

    private Handler handler(){
        return handler;
    }
}
