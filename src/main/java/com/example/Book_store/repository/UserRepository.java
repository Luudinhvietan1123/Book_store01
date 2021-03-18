package com.example.Book_store.repository;

import com.example.Book_store.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(int userId);
    User findUserByUserEmail(String userEmail);
    User findUserByUserPhone(String userPhone);
}
