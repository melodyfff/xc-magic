package com.xinchen.jwt.api;

import com.alibaba.fastjson.JSONObject;
import com.xinchen.jwt.annotation.UserLoginToken;
import com.xinchen.jwt.entity.User;
import com.xinchen.jwt.service.TokenService;
import com.xinchen.jwt.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/5/29 0:19
 */
@RestController
@RequestMapping("/api")
public class UserApi {

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        final JSONObject jsonObject = new JSONObject();
        User u = UserService.getUser(user).call(user.isEmpty() ? null : user);
        if (null == u) {
            jsonObject.put("msg", "登录失败，用户不存在");
            return jsonObject;
        } else {
            if (!u.getPassword().equals(user.getPassword())) {
                jsonObject.put("msg", "登录失败，密码错误");
                return jsonObject;
            } else {
                String token = TokenService.getToken().call(u);

                jsonObject.put("token", token);
                jsonObject.put("user", u);
                return jsonObject;
            }
        }
    }


    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "Hello World!";
    }
}
