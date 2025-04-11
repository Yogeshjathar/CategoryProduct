package com.Rest.CategoryProduct.Service;

import com.Rest.CategoryProduct.Entity.AppUser;
import com.Rest.CategoryProduct.Exceptions.DataAlreadyExistException;
import com.Rest.CategoryProduct.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepositories.findUserByName(username)
                .orElseThrow(() -> new DataAlreadyExistException("User Already Exist !! You can not create same user"));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROlE"+role))
                        .collect(Collectors.toList())

        );
    }

//    public String createUser(AppUser user){
//        AppUser userByName = userRepositories.findUserByName(user.getUsername())
//        return "New User Created Successfully !!";
//    }
}
