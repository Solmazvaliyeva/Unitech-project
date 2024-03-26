package com.uni.project.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "registered_users")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//UserDetails
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "pin is mandatory")
    @Length(min=7 ,max = 7 ,message = "invalid pin")
    @Column(name = "user_pin")
    private String pin;
    @NotBlank(message = "password is mandatory")

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user" ,cascade ={CascadeType.DETACH ,CascadeType.PERSIST ,CascadeType.MERGE ,
    CascadeType.REFRESH})
    private List<UserAccounts> accounts;





    public UserEntity(Long id, String pin, String password, List<UserAccounts> accounts) {
        this.id = id;
        this.pin = pin;
        this.password = password;
        this.accounts = accounts;
    }

//    public UserEntity(String pin, String password, List<UserAccounts> accounts) {
//        this.pin = pin;
//        this.password = password;
//        this.accounts = accounts;
//    }

    public UserEntity() {
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAccounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccounts> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", pin='" + pin + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                '}';
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//

//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
