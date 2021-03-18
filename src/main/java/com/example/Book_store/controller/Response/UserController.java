package com.example.Book_store.controller.Response;

import com.example.Book_store.repository.BookRepository;
import com.example.Book_store.repository.UserRepository;
import com.example.Book_store.repository.entities.Book;
import com.example.Book_store.repository.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book_store")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    //User page 1
    @GetMapping(value = "/user/{userId}")
    public User findUserByUserId(@PathVariable(value = "userId") int userId) throws Exception{
        User response = userRepository.findUserByUserId(userId);
        return response;
    }

    //User page 2
    @GetMapping(value = "/user_store/{user_id}")
    public List<Book> findAllByUser_id(@PathVariable(name = "user_id") int userId) throws Exception{
        List<Book> response = bookRepository.findAllByUserId(userId);
        return response;
    }

    @PostMapping(value = "/user/register")
    public User registerNewUser(@RequestBody UserRequest request){
        User insertUser = new User();
        insertUser.setUserEmail(request.getUserEmail());
        User checkInfor1 = userRepository.findUserByUserEmail(request.getUserEmail());
        if(checkInfor1 != null){
            System.out.println("Email: " + request.getUserEmail() + " already used!");
            return null;
        }
        insertUser.setUserPhone(request.getUserPhone());
        User checkInfor2 = userRepository.findUserByUserPhone(request.getUserPhone());
        if(checkInfor2 != null){
            System.out.println("Phone: " + request.getUserPhone() + " already used!");
            return null;
        }
        insertUser.setUserPassword(request.getUserPassword());
        insertUser = userRepository.save(insertUser);
        return insertUser;
    }

    @PostMapping(value = "/user/updateInformation/{userId}")
    public User updateUser(@PathVariable(value = "userId") int userId, @RequestBody UserRequest userRequest){
        User update = userRepository.findUserByUserId(userId);
        User checkInfor1 = userRepository.findUserByUserEmail(userRequest.getUserEmail());
        if(checkInfor1 != null){
            System.out.println("Email: " + userRequest.getUserEmail() + " already used!");
            return null;
        }
        User checkInfor2 = userRepository.findUserByUserPhone(userRequest.getUserPhone());
        if(checkInfor2 != null){
            System.out.println("Phone: " + userRequest.getUserPhone() + " already used!");
            return null;
        }
        update.setUserPassword(userRequest.getUserPassword());
        update.setUserPhone(userRequest.getUserPhone());
        update.setUserEmail(userRequest.getUserEmail());
        update = userRepository.save(update);
        return update;
    }
}

@Setter @Getter
class UserRequest{
    private String userEmail;
    private String userPhone;
    private String userPassword;
}