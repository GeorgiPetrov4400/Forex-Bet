package forexbet.tradingforecasts.service.security;

import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.model.entity.UserRole;
import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;
import forexbet.tradingforecasts.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradingForecastsUserDetailsServiceTest {

    private TradingForecastsUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new TradingForecastsUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserFound() {
        UserRole testRoleAdmin = new UserRole().setRole(UserRoleEnum.Admin);
        UserRole testRoleModerator = new UserRole().setRole(UserRoleEnum.Moderator);
        UserRole testRoleUser = new UserRole().setRole(UserRoleEnum.User);

        User testUser = new User().setUsername("Admin").setPassword("12345")
                .setRoles(List.of(testRoleAdmin, testRoleModerator, testRoleUser));

        when(mockUserRepository.findByUsername("Admin")).thenReturn(Optional.of(testUser));

        UserDetails adminDetails = toTest.loadUserByUsername("Admin");

        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals("Admin", adminDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), adminDetails.getPassword());
        Assertions.assertEquals(3, adminDetails.getAuthorities().size());
        assertRole(adminDetails.getAuthorities(), "ROLE_Admin");
        assertRole(adminDetails.getAuthorities(), "ROLE_Moderator");
        assertRole(adminDetails.getAuthorities(), "ROLE_User");
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities, String role) {
        authorities.stream().filter(a -> role.equals(a.getAuthority())).findAny()
                .orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> toTest.loadUserByUsername("NotExist"));
    }

    @Test
    void testUserEmailNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> toTest.loadUserByUsername("NotExist@example.com"));
    }
}
