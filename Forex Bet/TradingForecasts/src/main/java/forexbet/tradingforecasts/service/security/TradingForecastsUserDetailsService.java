package forexbet.tradingforecasts.service.security;

import forexbet.tradingforecasts.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class TradingForecastsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public TradingForecastsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));

        return
                new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles()
                                .stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                                .collect(Collectors.toList())
                );
    }
}
