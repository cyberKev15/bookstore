package com.group12.bookstore.service.book;

import com.group12.bookstore.domain.BookData;

import java.sql.SQLException;

public interface BookDataService {
    BookData createBookData(Long isbn, String bookName, String bookDescription, Double price, String author, String genre, String publisher, String yearPublished, String copiesSold, Integer rating) throws SQLException;
}