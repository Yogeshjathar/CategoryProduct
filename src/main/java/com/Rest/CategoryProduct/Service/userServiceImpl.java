package com.Rest.CategoryProduct.Service;

import com.Rest.CategoryProduct.Entity.AppUser;
import com.Rest.CategoryProduct.Exceptions.DataAlreadyExistException;
import com.Rest.CategoryProduct.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private UserRepositories userRepositories;
    @Override
    public String createUser(AppUser user) {
        Optional<AppUser> userByName = Optional.ofNullable(userRepositories.findUserByName(user.getUsername())
                .orElseThrow(() -> new DataAlreadyExistException("User Already Exist !! You can not create same user")));

        userRepositories.save(user);
        return "User Created Successfully !!";
    }
}
