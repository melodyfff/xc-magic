package com.xinchen.spring.beans.entiry;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/1/5 0:55
 */
@Setter
@Getter
public class Coffee {

    private long id;

    private String name;

    private Date creatDate;

    private List<String> types;

    private Map<String, Double> prices;
}
