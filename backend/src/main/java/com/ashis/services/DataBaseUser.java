package com.ashis.services;

import com.ashis.entities.User;
import com.ashis.exceptions.AccountNotFoundException;
import com.ashis.repositories.UserRepository;
import com.ashis.utils.CustomUserDetail;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public  class DataBaseUser implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        User byUsername = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("User not Found "));


        return  new CustomUserDetail(byUsername);
    }
}