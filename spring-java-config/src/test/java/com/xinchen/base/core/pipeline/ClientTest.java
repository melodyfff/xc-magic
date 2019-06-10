package com.xinchen.base.core.pipeline;

import com.xinchen.base.ApplicationContextTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/11 0:04
 */
public class ClientTest extends ApplicationContextTests {

    @Autowired
    private Client client;

    @Test
    public void test(){
        client.mockedClient();
    }

}