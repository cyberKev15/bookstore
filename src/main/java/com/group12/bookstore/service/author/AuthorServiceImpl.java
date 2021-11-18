package com.group12.bookstore.service.author;

import com.group12.bookstore.domain.Author;
import com.group12.bookstore.exeptions.BadRequestException;
import com.group12.bookstore.repositories.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author addAuthor(String authorFirstName, String authorLastName, String publisher, String biography) throws BadRequestException {
        Author author = authorRepository.addAuthor(authorFirstName, authorLastName, publisher, biography);
        return author;
    }
}