package com.Rest.CategoryProduct.Repositories;

import com.Rest.CategoryProduct.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepositories extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findUserByName(String username);
}
