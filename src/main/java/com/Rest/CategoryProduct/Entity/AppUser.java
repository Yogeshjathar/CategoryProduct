package com.Rest.CategoryProduct.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class AppUser {

    @Id
    private Long id;
    private String name;
    private String username;
    private String password;
    private Set<String> roles;
}