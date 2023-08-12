package forexbet.tradingforecasts.service.account;

import forexbet.tradingforecasts.model.dto.ChangeAccountUsernameDTO;
import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(userService);
    }

    @Test
    void editAccountUsername_validInput_usernameChanged() {
        // arrange
        ChangeAccountUsernameDTO newUserDto = new ChangeAccountUsernameDTO().setUsername("new_username");
        UserDetails principal = mock(UserDetails.class);
        User user = new User().setUsername("old_username");
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal.getUsername(),
                principal.isCredentialsNonExpired(), principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userService.getUserByUsername(principal.getUsername())).thenReturn(user);
        when(userService.getUserByUsername(newUserDto.getUsername())).thenReturn(null);

        // act
        accountService.editAccountUsername(newUserDto, principal);

        // assert
        assertEquals(newUserDto.getUsername(), user.getUsername());
        verify(userService, times(1)).saveUserChanges(user);
    }

    @Test
    void editAccountUsername_userAlreadyExists_throwException() {
        // arrange
        ChangeAccountUsernameDTO newUserDto = new ChangeAccountUsernameDTO().setUsername("existing_username");
        UserDetails principal = mock(UserDetails.class);
        User user = new User().setUsername("old_username");
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal.getUsername(),
                principal.isCredentialsNonExpired(), principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userService.getUserByUsername(principal.getUsername())).thenReturn(user);
        when(userService.getUserByUsername(newUserDto.getUsername())).thenReturn(new User());

        // act & assert
        assertThrows(RuntimeException.class, () -> accountService.editAccountUsername(newUserDto, principal));
        assertEquals(user.getUsername(), "old_username");
        verify(userService, never()).saveUserChanges(user);
    }

    @Test
    void editAccountUsername_userNotLoggedIn_throwException() {
        // arrange
        ChangeAccountUsernameDTO newUserDto = new ChangeAccountUsernameDTO().setUsername("new_username");
        UserDetails principal = null;

        // act & assert
        assertThrows(RuntimeException.class, () -> accountService.editAccountUsername(newUserDto, principal));
        verify(userService, never()).getUserByUsername(anyString());
        verify(userService, never()).getUserByUsername(anyString());
        verify(userService, never()).saveUserChanges(any(User.class));
    }
}
