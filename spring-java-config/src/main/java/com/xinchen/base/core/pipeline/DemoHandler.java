package com.xinchen.base.core.pipeline;

import com.xinchen.base.core.vo.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/10 23:52
 */
@Component
@Slf4j
public class DemoHandler implements Handler{

    @Override
    public void filterTask(HandlerContext ctx, Task task) {
      log.info("触发pipeline -> 过滤任务");
      ctx.fireTaskFiltered(task);

    }
}
