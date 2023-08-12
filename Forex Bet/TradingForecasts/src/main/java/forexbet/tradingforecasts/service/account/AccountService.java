package forexbet.tradingforecasts.service.account;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import forexbet.tradingforecasts.model.dto.ChangeAccountUsernameDTO;
import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.service.UserService;

@Service
public class AccountService {

    private final UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public void editAccountUsername(ChangeAccountUsernameDTO newUserDto,
                                    UserDetails principal) {

        if (principal == null) {
            throw new RuntimeException("If you want to change username more than once you have to logout and then login again");
        }

        User user = userService.getUserByUsername(principal.getUsername());

        if (userService.getUserByUsername(newUserDto.getUsername()) != null) {
            throw new RuntimeException("Username is already in use");
        }

        Authentication newAuthentication =
                new UsernamePasswordAuthenticationToken(newUserDto.getUsername(),
                        principal.isCredentialsNonExpired(), principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        user.setUsername(newUserDto.getUsername());

        userService.saveUserChanges(user);
    }
}