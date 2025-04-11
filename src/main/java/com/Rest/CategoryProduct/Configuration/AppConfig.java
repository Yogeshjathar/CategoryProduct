package com.Rest.CategoryProduct.Configuration;

import com.Rest.CategoryProduct.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableMethodSecurity
@Configuration
public class AppConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

/*    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder().username("Yogesh").password(passwordEncoder().encode("1234")).roles("Admin","User").build();
        UserDetails user2 = User.builder().username("Prashant").password(passwordEncoder().encode("1234")).roles("Admin").build();
        UserDetails user3 = User.builder().username("Ram").password(passwordEncoder().encode("Ram")).roles("User").build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }*/


    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
