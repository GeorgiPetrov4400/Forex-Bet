package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.model.entity.UserRole;
import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private User testAdmin;

    @BeforeEach
    void setUp() {
        testAdmin = createTestAdmin("admin@example.com", "Admin");
    }

    private User createTestAdmin(String email, String username) {

        UserRole adminRole = new UserRole().setRole(UserRoleEnum.Admin);
        UserRole moderatorRole = new UserRole().setRole(UserRoleEnum.Moderator);
        UserRole userRole = new UserRole().setRole(UserRoleEnum.User);

        User testAdminUser = new User()
                .setEmail(email)
                .setUsername(username)
                .setPassword("12345")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setRoles(List.of(adminRole, moderatorRole, userRole));

        return testAdminUser;
    }

    @Test
    void testGetHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    void testGetContactUs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    void testGetAboutUs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    void testGetFreeForecasts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/free-forecasts")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("free-forecasts"));
    }
}
