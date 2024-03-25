package com.uni.project.service;

import com.uni.project.dao.UserEntity;
import com.uni.project.dao.repo.UserRepository;
import com.uni.project.exception.NoUserFoundException;
import com.uni.project.exception.UserExistException;
import com.uni.project.exception.WrongUserCredentials;
import com.uni.project.models.RegisterRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserRegisterService service;

    @Test
    public void createUser_whenValidPin() {

        RegisterRequest request = RegisterRequest.builder().pin("7bslc70")
                .password("234").build();

        UserEntity user = UserEntity.builder()
                .pin(request.getPin().toUpperCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();


        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);

        RegisterRequest registeredUser = service.registerUser(request);

        Assertions.assertThat(registeredUser).isNotNull();

    }

    @Test
    public void returnExceptionMessage_whenExistedPin() {
        UserEntity user = UserEntity.builder().pin("7bslc70")
                .password("234").build();
        RegisterRequest request = RegisterRequest.builder().pin("7bslc70")
                .password("234").build();


        when(userRepository.findByPin(user.getPin().toUpperCase())).thenReturn(Optional.ofNullable(user));


        assertThrows(UserExistException.class, () -> service.registerUser(request));

    }


    @Test
    public void checkUser_whenValidPinAndPassword() {
        UserEntity user = UserEntity.builder()
                .pin("7BSLC70")
                .password(passwordEncoder.encode("123"))
                .build();

        RegisterRequest request = RegisterRequest.builder().pin("7BSLC70")
                .password("123").build();
        lenient().when(userRepository.findByPin("7BSLC70")).thenReturn(Optional.ofNullable(user));

        when(passwordEncoder.matches("123", user.getPassword())).thenReturn(true);


        assertEquals("Successful login", service.login(request));
    }

    @Test
    public void checkUser_whenPinNotNotRegistered() {
        UserEntity user = UserEntity.builder()
                .pin("7BSLC70")
                .password("123")
                .build();

        RegisterRequest request = RegisterRequest.builder().pin("7BSLC70")
                .password("234").build();

        lenient().when(userRepository.findByPin("7BSLC76")).thenReturn(Optional.ofNullable(user));
        assertThrows(NoUserFoundException.class, () -> service.login(request));

    }

    @Test
    public void checkUser_whenWrongPassword() {
        UserEntity user = UserEntity.builder()
                .pin("7BSLC70")
                .password(passwordEncoder.encode("1234"))
                .build();

        RegisterRequest request = RegisterRequest.builder().pin("7BSLC70")
                .password("123").build();
        lenient().when(userRepository.findByPin("7BSLC70")).thenReturn(Optional.ofNullable(user));

        when(!passwordEncoder.matches("123", user.getPassword())).thenReturn(false);

        assertThrows(WrongUserCredentials.class, () -> service.login(request));
    }


}
