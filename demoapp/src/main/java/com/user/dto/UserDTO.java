package com.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserDTO {
    @JsonProperty(value= "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "The name field is required.")
    private String name;
    @Email(message = "Invalid email value.")
    private String email;
    @NotNull(message = "The age field is required.")
    private Integer age;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
    }
}
