package com.group12.bookstore.repositories.book;

import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.exeptions.BadRequestException;

import java.sql.SQLException;

public interface BookDataRepository {

    BookData createBook(Long isbn, String bookName, String bookDescription, Double price, String author, String genre, String publisher, String yearPublished, String copiesSold, Integer rating) throws BadRequestException, SQLException;

    BookData findByIsbn(Long isbn) throws BadRequestException;
}