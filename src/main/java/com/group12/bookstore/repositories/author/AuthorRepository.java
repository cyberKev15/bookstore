package com.group12.bookstore.repositories.author;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.exeptions.BadRequestException;

public interface AuthorRepository {
    Author addAuthor(String authorFirstName, String authorLastName, String publisher, String biography) throws BadRequestException;
}