package com.example.Book_store.services.UserServices;

import com.example.Book_store.repository.UserRepository;
import com.example.Book_store.repository.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByUserPhone(userPhone);
        if(userEntity == null) {
            System.out.println("User not found! " + userPhone);
            throw new UsernameNotFoundException("User " + userPhone + " was not found in database");
        }
        System.out.println("Found user: " + userEntity);

        List<String> roleNames = Arrays.stream(userEntity.getUserRole().split(",")).collect(Collectors.toList());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(roleNames != null){
            for(String role : roleNames){
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(userEntity.getUserPhone(), //
                userEntity.getUserPassword(), grantList);
        return userDetails;
    }
}
