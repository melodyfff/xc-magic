package com.xinchen.spring.caching.core;

/**
 * @author xinchen
 * @version 1.0
 * @date 25/04/2019 11:29
 */
public interface BookRepository {
    Book getByIsbn(String isbn);
}
