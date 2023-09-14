package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.user.repository.UserRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User(123L, "John Doe", "john.doe@bluecoding.com", 30);
    }

    @Test
    void testGetUserSuccess() {
        when(userRepository.findById(123L)).thenReturn(Optional.of(testUser));
        User foundUser = userService.getUser(123L);
        assertEquals(testUser, foundUser);
    }

    @Test
    void testGetUserFailure() {
        when(userRepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(123L));
    }

}
