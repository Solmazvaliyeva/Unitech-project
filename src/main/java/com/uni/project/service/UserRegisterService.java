package com.uni.project.service;


import com.uni.project.models.RegisterRequest;
import com.uni.project.exception.NoUserFoundException;
import com.uni.project.exception.UserExistException;
import com.uni.project.exception.WrongUserCredentials;
import com.uni.project.dao.UserEntity;
import com.uni.project.dao.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserRegisterService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public RegisterRequest registerUser(RegisterRequest request) {

        Boolean userExists = repository.findByPin(request.getPin().toUpperCase()).isPresent();
        if (userExists) {
            throw new UserExistException("Entered pin already exists");
        }
        var user = UserEntity.builder()
                .pin(request.getPin().toUpperCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        UserEntity savedUser =repository.save(user);
       RegisterRequest registerRequest = RegisterRequest.builder()
               .pin(savedUser.getPin())
               .password("***********").build();

        return  registerRequest;
    }

    public String login(RegisterRequest request){
        UserEntity user =repository.findByPin(request.getPin().toUpperCase()).orElseThrow(()-> new NoUserFoundException("Entered User not found"));
        String result = "" ;
        if(passwordEncoder.matches(request.getPassword() ,user.getPassword())){
            result = "Successful login" ;
        }else {
            throw new WrongUserCredentials("Wrong credentials : Entered pin or password do not exist");
        }
        return result;
    }


}


