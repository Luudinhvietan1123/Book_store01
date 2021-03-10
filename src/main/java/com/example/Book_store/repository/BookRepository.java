package com.example.Book_store.repository;

import com.example.Book_store.repository.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    List<Book> findAllByUserId(int userId);
}
