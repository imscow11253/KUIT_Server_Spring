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

    public String getEmailByLoginId(String login_Id){
        String sql = "SELECT email FROM `user` WHERE login_id=?";
        return this.jdbcTemplate.queryForObject(sql, String.class, login_Id);
    }

    public boolean isThereLoginId(String login_id){
        String sql = "SELECT EXISTS(SELECT login_id FROM `user` WHERE login_id=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, login_id));
    }

    public boolean matchPW(String login_id, String login_pw){
        String sql = "SELECT login_pw FROM `user` WHERE login_id=?";
        String user_pw = this.jdbcTemplate.queryForObject(sql, String.class, login_id);
        return user_pw.equals(login_pw);
    }

    public long updateUserNameField(long user_id, String responseName){
        String sql = "UPDATE `user` SET name=? WHERE user_id = ?";

        this.jdbcTemplate.update(sql, responseName, user_id);
        return user_id;
    }

    public void updateUserIdField(){
        String sql = "SELECT user_id FROM `user` ORDER BY user_id DESC LIMIT 1";
        this.userId = this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public long getUseIdByLoginId(String login_id){
        String sql = "SELECT user_id FROM `user` WHERE login_id = ?";
        return this.jdbcTemplate.queryForObject(sql, long.class, login_id);
    }


    public long getUserIdByEmail(String email){
        String sql = "SELECT user_id FROM `user` WHERE email = ?";
        return this.jdbcTemplate.queryForObject(sql, long.class, email);
    }
}
