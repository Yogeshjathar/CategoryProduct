package com.Rest.CategoryProduct.Service;

import com.Rest.CategoryProduct.Entity.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface userService {

    public String createUser(AppUser user);
}
