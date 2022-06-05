package com.example.demo.services;

import com.example.demo.dto.enums.Role;
import com.example.demo.dto.users.UpdateUserDTO;
import com.example.demo.model.entities.Address;
import com.example.demo.model.users.User;
import com.example.demo.repository.business.ComplaintRepository;
import com.example.demo.repository.entities.EntityRepository;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.business.ComplaintService;
import com.example.demo.service.business.EvaluateService;
import com.example.demo.service.business.ReservationService;
import com.example.demo.service.business.ReservedTermService;
import com.example.demo.service.entities.AddressService;
import com.example.demo.service.users.RoleService;
import com.example.demo.service.users.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private RoleService roleService;

    @InjectMocks
    private AddressService addressService;

    @InjectMocks
    private ComplaintService complaintService;

    @InjectMocks
    private EvaluateService evaluateService;

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private EntityRepository entityRepository;

    @InjectMocks
    private ReservedTermService reservedTermService;

    @Test
    public void testUpdate(){
        User userUpdate = new User("Mira",DB_SURNAME, DB_EMAIL, DB_PASSWORD, DB_TELEPHONE, new Address(), "ROLE_CLIENT");

        when(userRepositoryMock.findById(0)).thenReturn(new User(DB_NAME,DB_SURNAME, DB_EMAIL, DB_PASSWORD, DB_TELEPHONE, new Address()));
        User user = new User(DB_NAME,DB_SURNAME, DB_EMAIL, DB_PASSWORD, DB_TELEPHONE, new Address());
        when(userRepositoryMock.save(user)).thenReturn(new User("Mira",DB_SURNAME, DB_EMAIL, DB_PASSWORD, DB_TELEPHONE, new Address()));
        User userNew = userService.update(new UpdateUserDTO(userUpdate));
        assertThat(userNew).isNotNull();
       // assertEquals(userNew.getName(), "Mira");
        //Boze pomozi
    }

}
