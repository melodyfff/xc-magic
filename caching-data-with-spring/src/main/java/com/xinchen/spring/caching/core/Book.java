package com.xinchen.spring.caching.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xinchen
 * @version 1.0
 * @date 25/04/2019 11:28
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Book {

    private String isbn;
    private String title;

}
