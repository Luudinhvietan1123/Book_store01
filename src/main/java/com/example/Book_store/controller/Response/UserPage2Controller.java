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
public class UserPage2Controller {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = "/my_store/{user_id}")
    public List<Book> findAllByUser_id(@PathVariable(name = "user_id") int userId) throws Exception{
        List<Book> response = bookRepository.findAllByUserId(userId);
        return response;
    }
}
