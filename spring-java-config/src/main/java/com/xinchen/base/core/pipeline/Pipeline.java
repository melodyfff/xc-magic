package com.xinchen.base.core.pipeline;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/10 23:14
 */
public interface Pipeline {
    /** 接收 */
    Pipeline fireTaskReceived();
    /** 过滤 */
    Pipeline fireTaskFiltered();
    /** 执行 */
    Pipeline fireTaskExecuted();
    /** 结束 */
    Pipeline fireAfterCompletion();
}
