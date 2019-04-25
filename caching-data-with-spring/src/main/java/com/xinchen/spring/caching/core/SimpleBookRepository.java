package com.xinchen.spring.caching.core;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 25/04/2019 11:32
 */
@Component
public class SimpleBookRepository implements BookRepository{

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some Book");
    }

    /**
     * 模拟延迟
     */
    private void simulateSlowService(){
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
