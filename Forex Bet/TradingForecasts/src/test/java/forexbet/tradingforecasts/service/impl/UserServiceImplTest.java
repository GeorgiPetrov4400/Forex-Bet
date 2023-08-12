package forexbet.tradingforecasts.service.impl;

import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.model.entity.UserRole;
import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;
import forexbet.tradingforecasts.model.view.UserViewModel;
import forexbet.tradingforecasts.repository.UserRepository;
import forexbet.tradingforecasts.service.UserRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleService mockUserRoleService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserServiceImpl toTest;

    public UserServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(mockUserRepository, mockUserRoleService, mockPasswordEncoder, mockModelMapper);
    }

    @Test
    void testInitAdmin() {
        String defaultAdminPass = "11111";

        when(mockUserRoleService.findAll()).thenReturn(List.of(new UserRole()));

        User testAdminUser = new User()
                .setEmail("admin@example.com")
                .setUsername("Admin")
                .setPassword(defaultAdminPass)
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setRoles(mockUserRoleService.findAll());

        mockUserRepository.save(testAdminUser);
    }

    @Test
    void testUserRegister() {
        when(mockUserRoleService.findUserRole(UserRoleEnum.User))
                .thenReturn(Optional.of(new UserRole().setRole(UserRoleEnum.User)));

        UserViewModel testUserViewModel = new UserViewModel()
                .setEmail("test@example.com")
                .setUsername("Test")
                .setPassword("11111")
                .setConfirmPassword("11111")
                .setFirstName("Tester")
                .setLastName("Testov")
                .setUserRole("User");

        toTest.registerUser(testUserViewModel);

        Mockito.verify(mockUserRepository).save(any());
    }

    @Test
    void testUserRegisterWithArgumentCaptor() {
        String testPassword = "11111";
        String encodedPassword = "encoded_password";

        when(mockUserRoleService.findUserRole(UserRoleEnum.User))
                .thenReturn(Optional.of(new UserRole().setRole(UserRoleEnum.User)));

        UserViewModel testUserViewModel = new UserViewModel()
                .setEmail("test@example.com")
                .setUsername("Test")
                .setPassword(testPassword)
                .setConfirmPassword(testPassword)
                .setFirstName("Tester")
                .setLastName("Testov")
                .setUserRole("User");

        when(mockPasswordEncoder.encode(testUserViewModel.getPassword())).thenReturn(encodedPassword);

        toTest.registerUser(testUserViewModel);

        Mockito.verify(mockUserRepository).save(userArgumentCaptor.capture());

        User actualSavedUser = userArgumentCaptor.getValue();
        Assertions.assertEquals(testUserViewModel.getEmail(), actualSavedUser.getEmail());
        Assertions.assertEquals(encodedPassword, actualSavedUser.getPassword());
    }

    @Test
    void testGetUserByUsername() {
        String testUsername = "Admin";
        User testUser = new User().setUsername(testUsername).setFirstName("Admin").setLastName("Adminov");

        Mockito.when(mockUserRepository.findByUsername(testUsername)).thenReturn(Optional.of(testUser));

        User actualUser = mockUserRepository.findByUsername(testUser.getUsername()).orElseThrow();

        Assertions.assertEquals(testUser, actualUser);
        Assertions.assertEquals(testUser.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(testUser.getFirstName(), actualUser.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), actualUser.getLastName());
    }
}
