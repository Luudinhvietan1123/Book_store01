package com.example.Book_store.controller.Response;

import com.example.Book_store.repository.BookRepository;
import com.example.Book_store.repository.UserRepository;
import com.example.Book_store.repository.entities.BookEntity;
import com.example.Book_store.repository.entities.UserEntity;
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
    public UserEntity findUserByUserId(@PathVariable(value = "userId") int userId) throws Exception{
        UserEntity response = userRepository.findUserByUserId(userId);
        return response;
    }

    //User page 2
    @GetMapping(value = "/user_store/{user_id}")
    public List<BookEntity> findAllByUser_id(@PathVariable(name = "user_id") int userId) throws Exception{
        List<BookEntity> response = bookRepository.findAllByUserId(userId);
        return response;
    }

    @PostMapping(value = "/user/register")
    public UserEntity registerNewUser(@RequestBody UserRequest request){
        UserEntity insertUserEntity = new UserEntity();
        insertUserEntity.setUserEmail(request.getUserEmail());
        UserEntity checkInfor1 = userRepository.findUserByUserEmail(request.getUserEmail());
        if(checkInfor1 != null){
            System.out.println("Email: " + request.getUserEmail() + " already used!");
            return null;
        }
        insertUserEntity.setUserPhone(request.getUserPhone());
        UserEntity checkInfor2 = userRepository.findUserByUserPhone(request.getUserPhone());
        if(checkInfor2 != null){
            System.out.println("Phone: " + request.getUserPhone() + " already used!");
            return null;
        }
        insertUserEntity.setUserPassword(request.getUserPassword());
        insertUserEntity = userRepository.save(insertUserEntity);
        return insertUserEntity;
    }

    @PostMapping(value = "/user/updateInformation/{userId}")
    public UserEntity updateUser(@PathVariable(value = "userId") int userId, @RequestBody UserRequest userRequest){
        UserEntity update = userRepository.findUserByUserId(userId);
        UserEntity checkInfor1 = userRepository.findUserByUserEmail(userRequest.getUserEmail());
        if(checkInfor1 != null){
            System.out.println("Email: " + userRequest.getUserEmail() + " already used!");
            return null;
        }
        UserEntity checkInfor2 = userRepository.findUserByUserPhone(userRequest.getUserPhone());
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