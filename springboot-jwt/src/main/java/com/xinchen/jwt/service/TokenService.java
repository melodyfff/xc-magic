package com.xinchen.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xinchen.jwt.entity.User;

import java.util.Objects;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/5/28 23:54
 */
@FunctionalInterface
public interface TokenService {

    String call(User u);

    static TokenService getToken(){
        return u -> JWT
                .create()
                // 将 user id 保存到 token 里面
                .withAudience(u.getId())
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(u.getPassword()));
    }
}
