package kuit2.server.ServerApplication.dao;

import kuit2.server.ServerApplication.dio.PostUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.spec.NamedParameterSpec;
import java.util.Objects;

@Slf4j
@Repository
public class UserDao {
    
    private final JdbcTemplate jdbcTemplate;
    private int userId;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public long createUser(PostUserRequest postUserRequest) {
        this.updateUserIdField();
        String sql = "INSERT INTO `user` (user_id, login_id, login_pw, name, email, phone_number) VALUES (?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, ++this.userId, postUserRequest.getLogin_id(), postUserRequest.getLogin_pw(), postUserRequest.getName(), postUserRequest.getEmail(), postUserRequest.getPhone_number());

        return this.userId;
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "SELECT EXISTS(SELECT email FROM `user` WHERE email=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, email));
    }

    public boolean hasDuplicateName(String name) {
        String sql = "SELECT EXISTS(SELECT name FROM `user` WHERE name=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, name));
    }

    public void updateUserIdField(){
        String sql = "SELECT user_id FROM `user` ORDER BY user_id DESC LIMIT 1";
        this.userId = this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
