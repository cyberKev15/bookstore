package com.group12.bookstore.repositories.author;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.domain.BookData;
import com.group12.bookstore.exeptions.BadRequestException;

import java.util.List;

public interface AuthorRepository {
    Author addAuthor(String authorFirstName, String authorLastName, String publisher, String biography) throws BadRequestException;

    List<BookData> getBooks(String authorName) throws BadRequestException;
}