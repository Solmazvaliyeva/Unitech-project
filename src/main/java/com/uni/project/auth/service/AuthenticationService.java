package com.uni.project.auth.service;


import com.uni.project.auth.dto.RegisterRequest;
import com.uni.project.auth.exception.NoUserFoundException;
import com.uni.project.auth.exception.UserExistException;
import com.uni.project.auth.exception.WrongUserCredentials;
import com.uni.project.dao.UserEntity;
import com.uni.project.dao.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public String registerUser(RegisterRequest request) {

        Boolean userExists = repository.findByPin(request.getPin()).isPresent();
        if (userExists) {
            throw new UserExistException("Entered User already exists");
        }
        var user = UserEntity.builder()
                .pin(request.getPin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();


        repository.save(user);

        return "successfully registered";
    }

    public String login(RegisterRequest request){
        UserEntity user =repository.findByPin(request.getPin()).orElseThrow(()-> new NoUserFoundException("Entered User not found"));
        String result = "" ;
        if(passwordEncoder.matches(request.getPassword() ,user.getPassword())){
            result = "Successful login" ;
        }else {
            throw new WrongUserCredentials("Wrong credentials : Entered pin or password do not exist");
        }
        return result;
    }


}


