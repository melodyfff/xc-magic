package com.xinchen.spring.data.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/22 17:47
 */
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;


    public int rowCount() {
        return jdbcTemplate.queryForObject("select count(*) from app_user", Integer.class);
    }

    public User getUserById(long id) {
        return jdbcTemplate.queryForObject("select * from app_user where id = ?", new UserMapper(), new Object[]{id});
    }


    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(Long.parseLong(resultSet.getString("id")));
            user.setUserName(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }
}
