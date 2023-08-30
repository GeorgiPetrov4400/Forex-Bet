package forexbet.tradingforecasts.config;

import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;
import forexbet.tradingforecasts.service.security.TradingForecastsUserDetailsService;
import forexbet.tradingforecasts.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           SecurityContextRepository securityContextRepository) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/about", "/contact", "/testimonial", "/forecast",
                        "/users/login-error", "/free-forecasts", "/api/free-forecasts", "/forecasts/ui",
                        "/my-forecasts", "/active-forecasts", "/expired-forecasts",
                        "/eur-usd", "/gold", "/dax", "/dow-jones", "/nasdaq").permitAll()
                .requestMatchers("/users/register", "/users/login", "/free-forecasts").anonymous()
                .requestMatchers("/orders/order", "/my-account").authenticated()
                .requestMatchers("/orders/order", "/orders/order/expire/{id}").hasRole(UserRoleEnum.Moderator.name())
                .requestMatchers("/forecasts/add", "/orders/order", "/orders/order/expire/{id}", "/change-role")
                .hasRole(UserRoleEnum.Admin.name())
                .requestMatchers("/orders/order/buy/{id}").hasRole(UserRoleEnum.User.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .failureUrl("/users/login-error")
                .permitAll()
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .rememberMe()
                .key("uniqueAndSecret")
                .tokenValiditySeconds(604800)
                .userDetailsService(userDetailsService());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new TradingForecastsUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}
