package com.group12.bookstore.domain;

public class Author {

    private String authorFirstName;
    private String authorLastName;
    private String publisher;
    private String password;
    private String biography;

    public Author(String authorFirstName, String authorLastName, String publisher, String password, String biography) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publisher = publisher;
        this.password = password;
        this.biography = biography;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
