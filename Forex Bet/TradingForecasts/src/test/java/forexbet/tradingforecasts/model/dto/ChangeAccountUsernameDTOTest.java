package forexbet.tradingforecasts.model.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeAccountUsernameDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void setUsername_validInput_success() {
        // arrange
        ChangeAccountUsernameDTO dto = new ChangeAccountUsernameDTO();
        String username = "testuser123";
        dto.setUsername(username);

        // act
        validator.validate(dto);

        // assert
        assertEquals(username, dto.getUsername());
    }
}
