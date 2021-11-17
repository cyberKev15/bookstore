package com.group12.bookstore.repositories.book;

import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.exeptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class BookDataRepositoryImpl implements BookDataRepository {

    Logger logger = LoggerFactory.getLogger(BookDataRepositoryImpl.class);

    private static final String SQL_CREATE_BOOK = "INSERT INTO BOOKS(ISBN, BNAME, BDESCRIPTION, BPRICE, AUTHORNAME, BGENRE, BPUBLISHER, BYEAR, SOLDBOOKS, BRATING) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String methodName = "createBook";

    /***
     * This method---
     * @param isbn
     * @param bookName
     * @param bookDescription
     * @param price
     * @param author
     * @param genre
     * @param publisher
     * @param yearPublished
     * @param copiesSold
     * @param rating
     * @throws BadRequestException
     * @return
     */
    @Override
    public BookData createBook(Long isbn, String bookName, String bookDescription, Double price, String author, String genre, String publisher, String yearPublished, String copiesSold, Integer rating) throws BadRequestException {
        logger.info("In {} method, processing Book Data update", methodName);

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, isbn);
                ps.setString(2, bookName);
                ps.setString(3, bookDescription);
                ps.setDouble(4, price);
                ps.setString(5, author);
                ps.setString(6, genre);
                ps.setString(7, publisher);
                ps.setString(8, yearPublished);
                ps.setString(9, copiesSold);
                ps.setInt(10, rating);
                return ps;
            });
            logger.info("In {} method. Book details successfully updated.", methodName);
        } catch (Exception ex) {
            logger.error("ERROR in {} method.", methodName);
            throw new BadRequestException("Book details failed to insert.");
        }
        return null;
    }
}