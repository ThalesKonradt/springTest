package controller;

import dto.CreateUserRequest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest, @RequestHeader String token){
        return ResponseEntity.ok().body(userService.createUser(createUserRequest));
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByid(@PathVariable Integer id){
//        return ResponseEntity.ok().body(userService.findUserById(id));
        return ResponseEntity.ok().body(new User());
    }


}
