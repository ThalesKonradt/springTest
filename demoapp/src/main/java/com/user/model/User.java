package com.user.model;

import com.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private Integer age;


    public User(UserDTO createUserRequest) {
        updateValues(createUserRequest);
    }

    public User updateValues(UserDTO createUserRequest){
        this.name = createUserRequest.getName();
        this.email = createUserRequest.getEmail();
        this.age = createUserRequest.getAge();
        return this;
    }

    public UserDTO toDTO(){
        return new UserDTO(this);
    }

}
