package com.group12.bookstore.repositories;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.exeptions.AuthException;
import com.group12.bookstore.exeptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements  UserRepository {

    private static final String SQL_CREATE = "INSERT INTO USERS(USERID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ADDRESS) VALUES(NEXTVAL('USERS_SEQ'), ?, ?, ?, ?, ?)";
    private static final String SQL_EMAIL_COUNT = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USERID, FIRSTNAME, LASTNAME, EMAIL, ADDRESS, PASSWORD " + "FROM USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD " + "FROM ET_USERS WHERE USER_ID = ?";
    private static final String SQL_UPDATE = "UPDATE USERS SET ADDRESS = ? WHERE USERID = ? ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password, String address) throws AuthException {
        try {

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName );
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, address);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USERID");
        } catch (Exception ex) {
            throw new AuthException("Invalid Details. Account creation has failed.");
        }
    }

    @Override
    public void update(Integer userId, User user) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{user.getAddress(), userId});
        } catch (Exception ex) {
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public User findByUserId(Integer userId) throws AuthException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    @Override
    public User findByUsername(String email) throws AuthException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_EMAIL_COUNT, new Object[]{email}, Integer.class);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("userId"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString(("address")));
    });
}
