package com.example.Book_store.controller.Response;

import com.example.Book_store.repository.BookRepository;
import com.example.Book_store.repository.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/book_store")
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = "/home")
    public List<Book> findAll() throws Exception{
        List<Book> response = bookRepository.findAll();
        return response;
    }
}
