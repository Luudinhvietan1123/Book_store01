package com.example.Book_store.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_db")
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_image")
    private String bookImage;

    @Column(name = "book_genre")
    private String bookGenre;

    @Column(name = "book_price")
    private int bookPrice;

    @Column(name = "book_release")
    private int bookRelease;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "book_author")
    private String bookAuthor;
}
