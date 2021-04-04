package com.example.Book_store.repository;

import com.example.Book_store.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserByUserId(int userId);
    UserEntity findUserByUserEmail(String userEmail);
    UserEntity findUserByUserPhone(String userPhone);
}
