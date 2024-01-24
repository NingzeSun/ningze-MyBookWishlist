/* A Book class which contains all attributes needed, with getter and setter methods */
package com.example.ningze_mybookwishlist;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String genre;
    private String year;
    private String status;

    public Book(String title, String author, String genre, String year, String status){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String  getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }
}
