package com.example.Book_store.controller.Response;

import com.example.Book_store.repository.BookRepository;
import com.example.Book_store.repository.entities.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping(value = "book_store")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = "/book_id/{book_id}")
    public Book findAllByBookId(@PathVariable(value = "book_id") int bookId) throws Exception{
        Book response = bookRepository.findAllByBookId(bookId);
        return response;
    }

    @GetMapping(value = "/book_genre/{book_genre}")
    public List<Book> findAllByBookGenre(@PathVariable(value = "book_genre") String bookGenre) throws Exception{
        List<Book> response = bookRepository.findAllByBookGenre(bookGenre);
        return response;
    }

    //User page 3
    @GetMapping(value = "/user/favorite/{userId}")
    public List<Book> findFavoriteBookByUserId(@PathVariable(name = "userId") int userId) throws Exception{
        List<Book> response = bookRepository.findAllFavoriteBookByUserId(userId);
        return response;
    }

    @GetMapping(value = "/search/{keyword}")
    public List<Book> findAllByKeyword(@PathVariable(value = "keyword") String keyword) throws Exception{
        List<Book> response = bookRepository.findAllByKeyword(keyword);
        return response;
    }

    @PostMapping(value = "/user_store/addBook")
    public Book addBook(@RequestBody BookRequest request){
        Book insertEntity = new Book();
        insertEntity.setUserId(request.getUserId());
        insertEntity.setBookTitle(request.getBookTitle());
        insertEntity.setBookImage(request.getBookImage());
        insertEntity.setBookGenre(request.getBookGenre());
        insertEntity.setBookPrice(request.getBookPrice());
        insertEntity.setBookRelease(request.getBookRelease());
        insertEntity.setBookDescription(request.getBookDescription());
        insertEntity.setBookAuthor(request.getBookAuthor());
        insertEntity = bookRepository.save(insertEntity);
        return insertEntity;
    }

    @PostMapping(value = "/user/add_favorite_book/{bookId}")
    public String addFavoriteBook(@PathVariable(value = "bookId") int userId, int bookId){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
        }
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/web_bookshop?characterEncoding=utf8", "root", "adminstrator0");
            Statement statement = connection.createStatement();
            String sql = "insert into user_favorite_book (user_id, book_id) value (" + userId + ", " + bookId +")";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
        }
        return "success";
    }

    @DeleteMapping(value = "/user/delete_favorite_book/{bookId}")
    public String deleteFavoriteBook(@PathVariable(value = "bookId") int userId, int bookId){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
        }
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/web_bookshop?characterEncoding=utf8", "root", "adminstrator0");
            Statement statement = connection.createStatement();
            String sql0 = "SET SQL_SAFE_UPDATES = 0;";
            statement.executeUpdate(sql0);
            String sql = "delete from user_favorite_book where (user_id=" + userId + " and book_id=" + bookId+")";
            statement.executeUpdate(sql);
            String sql1 = "SET SQL_SAFE_UPDATES = 1;";
            statement.executeUpdate(sql1);
            connection.close();
        } catch (SQLException e) {
        }
        return "success";
    }

    @PostMapping(value = "/user_store/updateBook/{bookId}")
    public Book updateBook(@PathVariable(name = "bookId") int bookId, @RequestBody BookRequest bookRequest) throws Exception{
        Book update = bookRepository.findAllByBookId(bookId);
        if(update == null){
            System.out.println("Book with id = " + bookId + " is not existed!");
            return null;
        }
        update.setUserId(bookRequest.getUserId());
        update.setBookTitle(bookRequest.getBookTitle());
        update.setBookPrice(bookRequest.getBookPrice());
        update.setBookImage(bookRequest.getBookImage());
        update.setBookGenre(bookRequest.getBookGenre());
        update.setBookRelease(bookRequest.getBookRelease());
        update.setBookDescription(bookRequest.getBookDescription());
        update.setBookAuthor(bookRequest.getBookAuthor());
        update = bookRepository.save(update);
        return update;
    }

    @DeleteMapping(value = "/user_store/deleteBook/{bookId}")
    public String deleteBook(@PathVariable(name = "bookId") int bookId)throws Exception{
        Book deleteBook = bookRepository.findAllByBookId(bookId);
        if(deleteBook == null){
            System.out.println("Book with id = " + bookId + " is not existed!");
            return null;
        }
        bookRepository.delete(deleteBook);
        return "Delete success!";
    }
}

@Getter @Setter
class BookRequest{
    private int userId;
    private String bookTitle;
    private String bookImage;
    private String bookGenre;
    private int bookPrice;
    private int bookRelease;
    private String bookDescription;
    private String bookAuthor;
}
