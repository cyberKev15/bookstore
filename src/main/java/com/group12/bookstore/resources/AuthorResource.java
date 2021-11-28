package com.group12.bookstore.resources;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.service.author.AuthorService;
import com.group12.bookstore.service.book.BookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/authors")
public class AuthorResource {
    @Autowired
    BookDataService bookDataService;

    @Autowired
    AuthorService authorService;

    @PostMapping("/add-author")
    public ResponseEntity<Map<String, String>> createAuthor(@RequestBody Map<String, Object> authorMap) {
        String authorFirstName = (String) authorMap.get("first name");
        String authorLastName = (String) authorMap.get("last name");
        String publisher = (String) authorMap.get("publisher");
        String biography = (String) authorMap.get("biography");

        Author author = authorService.addAuthor(authorFirstName, authorLastName, publisher, biography);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Successfully added author");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping("/get-books")
    public List<BookData> getBookByAuthor(@RequestBody Map<String, Object> bookMap) {
        String authorName = (String) bookMap.get("authorName");

        return authorService.getBookByAuthor(authorName);
    }
}