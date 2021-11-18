package com.group12.bookstore.resources;

import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.service.book.BookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookDataResource {
    @Autowired
    BookDataService bookDataService;

    @PostMapping("/book-data")
    public ResponseEntity<Map<String, String>> createBook(@RequestBody Map<String, Object> bookMap) throws SQLException {
        Long isbn = (Long) bookMap.get("isbn");
        String bookName = (String) bookMap.get("book name");
        String bookDescription = (String) bookMap.get("book description");
        Double price = (Double) bookMap.get("price");
        String author = (String) bookMap.get("author");
        String genre = (String) bookMap.get("genre");
        String publisher = (String) bookMap.get("publisher");
        String yearPublished = (String) bookMap.get("year published");
        String copiesSold = (String) bookMap.get("copiesSold");
        Integer rating = (Integer) bookMap.get("rating");

        BookData bookData = bookDataService.createBookData(isbn, bookName, bookDescription, price, author, genre, publisher, yearPublished, copiesSold, rating);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Book Data added successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping("/get-data")
    public BookData getBookData(@RequestBody Map<String, Object> bookMap) {
        Long isbn = (Long) bookMap.get("isbn");

        return bookDataService.getBook(isbn);
    }
}