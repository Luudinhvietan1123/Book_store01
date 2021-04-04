package com.example.Book_store.repository;

import com.example.Book_store.repository.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>{

    List<BookEntity> findAll();

    List<BookEntity> findAllByUserId(int userId);

    List<BookEntity> findAllByBookGenre(String bookGenre);

    BookEntity findAllByBookId(int bookId);

    @Query(nativeQuery = true, value = "select * from book_db where book_id in (select book_id from user_favorite_book where user_id = ?1)")
    List<BookEntity> findAllFavoriteBookByUserId(int userId);

    @Query(nativeQuery = true, value = "select * from book_db where ((book_title LIKE %:keyword%) or (book_release LIKE %:keyword%) or (book_description LIKE %:keyword%) or (book_author LIKE %:keyword%))")
    List<BookEntity> findAllByKeyword(String keyword);

}
