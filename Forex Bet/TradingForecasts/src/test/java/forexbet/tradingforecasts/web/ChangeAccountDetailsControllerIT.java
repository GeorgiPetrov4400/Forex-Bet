package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.TradingForecastsApplication;
import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.repository.UserRepository;
import forexbet.tradingforecasts.service.UserService;
import forexbet.tradingforecasts.service.account.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TradingForecastsApplication.class)
public class ChangeAccountDetailsControllerIT {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");

        userDetails = org.springframework.security.core.userdetails.User
                .withUsername("test")
                .password("test")
                .roles("USER")
                .build();

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(accountService).editAccountUsername(any(), any());
    }

    @Test
    @WithMockUser
    public void testGetMyAccount() throws Exception {
        mockMvc.perform(get("/my-account"))
                .andExpect(status().isOk())
                .andExpect(view().name("change-username"));
    }

}