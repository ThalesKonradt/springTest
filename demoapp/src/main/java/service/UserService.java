package service;

import dto.CreateUserRequest;
import lombok.extern.java.Log;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import repository.UserRepository;

import java.util.Optional;

@Service
@Log
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserRequest createUserRequest){

      return userRepository.save(new User(createUserRequest));

    }

//
//    public Optional<User> findUserById(Integer id){
//
//        Optional<User> byId = userRepository.findById(id);
//
////        if(byId.isEmpty())
////            throw not
//
//        log.info("testmessage");
//
//
//
//    }

}
