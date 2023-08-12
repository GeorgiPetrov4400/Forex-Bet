package forexbet.tradingforecasts.model.dto;

import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;
import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ForecastAddDTOTest {

    @Test
    public void testSetDescription() {
        // Arrange
        ForecastAddDTO forecast = new ForecastAddDTO();
        String description = "This is a test description";

        // Act
        forecast.setDescription(description);

        // Assert
        assertEquals(description, forecast.getDescription());
    }

    @Test
    public void testSetPictureUrl() {
        // Arrange
        ForecastAddDTO forecast = new ForecastAddDTO();
        MultipartFile file = Mockito.mock(MultipartFile.class);

        // Act
        forecast.setPictureUrl(file);

        // Assert
        assertEquals(file, forecast.getPictureUrl());
    }

    @Test
    public void testSetPrice() {
        // Arrange
        ForecastAddDTO forecast = new ForecastAddDTO();
        BigDecimal price = new BigDecimal("10.50");

        // Act
        forecast.setPrice(price);

        // Assert
        assertEquals(price, forecast.getPrice());
    }

    @Test
    public void testSetActive() {
        // Arrange
        ForecastAddDTO forecast = new ForecastAddDTO();
        boolean isActive = true;

        // Act
        forecast.setActive(isActive);

        // Assert
        assertEquals(isActive, forecast.isActive());
    }

    @Test
    public void testSetCategory() {
        // Arrange
        ForecastAddDTO forecast = new ForecastAddDTO();
        CategoryNameEnum category = CategoryNameEnum.EurUsd;

        // Act
        forecast.setCategory(category);

        // Assert
        assertEquals(category, forecast.getCategory());
    }

    @Test
    public void testSetType() {
        // Arrange
        ForecastAddDTO forecast = new ForecastAddDTO();
        ForecastTypeEnum type = ForecastTypeEnum.Short;

        // Act
        forecast.setType(type);

        // Assert
        assertEquals(type, forecast.getType());
    }

}
