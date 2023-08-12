package forexbet.tradingforecasts.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")
                        .with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegistration_Success() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "gosho@example.com")
                        .param("username", "Gosho")
                        .param("password", "12345")
                        .param("confirmPassword", "12345")
                        .param("firstName", "Gosho")
                        .param("lastName", "Goshev")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));
    }

    @Test
    void testRegistration_ValidationFailed() throws Exception {
        MvcResult result = mockMvc.perform(post("/users/register")
                        .param("email", "example.com")
                        .param("username", "Go")
                        .param("password", "123")
                        .param("confirmPassword", "123")
                        .param("firstName", "G")
                        .param("lastName", "G")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register")).andReturn();

        BindingResult br = (BindingResult)result.getFlashMap().get("org.springframework.validation.BindingResult.userRegisterDTO");
        assertEquals("Please enter valid email!", br.getFieldError("email").getDefaultMessage());
        assertEquals("Username length must be between 3 and 20 characters!", br.getFieldError("username").getDefaultMessage());
        assertEquals("First name length must be between 2 and 20 characters!", br.getFieldError("firstName").getDefaultMessage());
        assertEquals("Last name length must be between 2 and 20 characters!", br.getFieldError("lastName").getDefaultMessage());
        assertEquals("Password length must be between 5 and 20 characters!", br.getFieldError("password").getDefaultMessage());

    }

    @Test
    void testRegistration_EmailAlreadyUsed() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "example@gosh.com")
                        .param("username", "Gosho123")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                        .param("firstName", "Goshko")
                        .param("lastName", "Goshkov")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

        MvcResult result = mockMvc.perform(post("/users/register")
                        .param("email", "example@gosh.com")
                        .param("username", "Gosho123")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                        .param("firstName", "Goshko")
                        .param("lastName", "Goshkov")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register")).andReturn();

        BindingResult br = (BindingResult)result.getFlashMap().get("org.springframework.validation.BindingResult.userRegisterDTO");
        assertEquals("Email is already used", br.getGlobalError().getDefaultMessage());
    }

    @Test
    void testGetLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")
                        .with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginWithEmptyParams_Failed() throws Exception {
        mockMvc.perform(post("/users/login-error")
                        .param("Username", "")
                        .param("Password", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));
    }

}
