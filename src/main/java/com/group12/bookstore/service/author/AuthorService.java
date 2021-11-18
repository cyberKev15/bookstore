package com.group12.bookstore.service.author;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.exeptions.BadRequestException;

public interface AuthorService {
    Author addAuthor(String authorFirstName, String authorLastName, String publisher, String biography) throws BadRequestException;
}