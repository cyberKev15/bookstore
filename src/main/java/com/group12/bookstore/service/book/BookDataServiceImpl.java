package com.group12.bookstore.service.book;

import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.exeptions.BadRequestException;
import com.group12.bookstore.repositories.book.BookDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class BookDataServiceImpl implements BookDataService {
    @Autowired
    BookDataRepository bookDataRepository;

    @Override
    public BookData createBookData(Long isbn, String bookName, String bookDescription, Double price, String author, String genre, String publisher, String yearPublished, String copiesSold, Integer rating) throws SQLException {
        if (isbn == null) {
            System.out.println("ISBN must be provided!");
        }
        BookData bookData = bookDataRepository.createBook(isbn, bookName, bookDescription, price, author, genre, publisher, yearPublished, copiesSold, rating);
        return bookData;
    }

    @Override
    public BookData getBook(Long isbn) throws BadRequestException {
        if (isbn != null) isbn = isbn;
        if (isbn == null) {
            System.out.println("ISBN must be provided!");
        }
            return bookDataRepository.findByIsbn(isbn);
    }
}