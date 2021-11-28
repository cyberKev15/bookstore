package com.group12.bookstore.repositories.author;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.exeptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    Logger logger = LoggerFactory.getLogger(AuthorRepositoryImpl.class);

    private static final String SQL_CREATE_AUTHOR = "INSERT INTO AUTHOR(AFIRSTNAME, ALASTNAME, PUBLISHER, BIOGRAPHY) VALUES(?, ?, ?, ?)";
    private static final String SQL_GET_BOOKS_BY_AUTHOR = "SELECT * " + "FROM BOOKS WHERE AUTHORNAME = ?";


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

    @Override
    public List<BookData> getBooks(String authorName) throws BadRequestException {
        List<BookData> books = jdbcTemplate.query(SQL_GET_BOOKS_BY_AUTHOR, new Object[]{authorName}, bookDataRowMapper);
        return books;
    }

    private RowMapper<BookData> bookDataRowMapper = ((rs, rowNum) -> {
        return new BookData(rs.getLong("isbn"),
                rs.getString("bname"),
                rs.getString("bdescription"),
                rs.getDouble("bprice"),
                rs.getString("authorname"),
                rs.getString("bgenre"),
                rs.getString("bpublisher"),
                rs.getString("byear"),
                rs.getString("soldbooks"),
                rs.getInt(("brating")));
    });
}