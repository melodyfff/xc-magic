package com.xinchen.spring.beans.scope;

import org.springframework.stereotype.Service;

/**
 *
 * {@link RickService} 作为单例bean,接收Scope为prototype的{@link Rick}
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/25 23:00
 */
@Service
public class RickService {

    private Rick rick;

    public RickService(Rick rick) {
        this.rick = rick;
    }

    public void say() {
        System.out.println("Rick now is " + rick);
    }
}
