package com.example.demo.services;

import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.User;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.users.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.constants.UserConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void testUpdate(){
        User userUpdate = new User("Mira",DB_SURNAME, DB_EMAIL, DB_PASSWORD, DB_TELEPHONE, new Address());
        when(userRepositoryMock.findById(1)).thenReturn(new User(DB_NAME,DB_SURNAME, DB_EMAIL, DB_PASSWORD, DB_TELEPHONE, new Address()));
        User user = userService.update(new UpdateUserDTO(userUpdate));
        assertThat(user).isNotNull();
        assertEquals(user.getName(), "Mira");
    }

}
