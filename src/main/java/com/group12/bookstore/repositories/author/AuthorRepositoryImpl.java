package com.group12.bookstore.repositories.author;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.exeptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    Logger logger = LoggerFactory.getLogger(AuthorRepositoryImpl.class);

    private static final String SQL_CREATE_AUTHOR = "INSERT INTO AUTHOR(AFIRSTNAME, ALASTNAME, PUBLISHER, BIOGRAPHY) VALUES(?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String methodName = "addAuthor";


    @Override
    public Author addAuthor(String authorFirstName, String authorLastName, String publisher, String biography) throws BadRequestException {
        logger.info("In {} method, processing author update", methodName);

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_AUTHOR, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, authorFirstName);
                ps.setString(2, authorLastName);
                ps.setString(3, publisher);
                ps.setString(4, biography);
                return ps;
            });
            logger.info("In {} method. Author details successfully added.", methodName);
        } catch (Exception ex) {
            logger.error("ERROR in {} method.", methodName);
            throw new BadRequestException("Author details failed to insert.");
        }
        return null;
    }
}