package com.example.demoapp;

import dto.CreateUserRequest;
import model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class DemoappApplicationTests {

	@Mock
	private UserService userService;

	@Test
	void test() {

		User value = new User();
		when(userService.createUser(any())).thenReturn(value);

		User user = userService.createUser(new CreateUserRequest());

//		assertEquals(value,user);
	}

}
