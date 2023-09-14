package controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.user.controller.UserController;
import com.user.exception.UserNotFoundException;
import com.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;
    private User testUser;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        testUser = new User(123L, "John Doe", "john.doe@bluecoding.com", 30);
    }

    @Test
    void testGetUserSuccess() throws Exception {
        when(userService.getUser(123L)).thenReturn(testUser);
        mockMvc.perform(get("/api/users/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetUserFailure() throws Exception {
        when(userService.getUser(123L)).thenThrow(new UserNotFoundException(123L));
        mockMvc.perform(get("/api/users/123"))
                .andExpect(status().isNotFound());
    }
}

