package model;

import dto.CreateUserRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    public User(CreateUserRequest createUserRequest) {
        if(createUserRequest == null)
            return;

        this.name = createUserRequest.getName();
        this.email = createUserRequest.getEmail();
        this.age = createUserRequest.getAge();
    }
}
