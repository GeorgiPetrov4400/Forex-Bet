package forexbet.tradingforecasts.model.entity;

import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ForecastTest {

    @Test
    public void testGettersAndSetters() {
        Forecast forecast = new Forecast();

        Category category = new Category();
        forecast.setCategory(category);
        Assertions.assertEquals(category, forecast.getCategory());

        User admin = new User();
        forecast.setAdmin(admin);
        Assertions.assertEquals(admin, forecast.getAdmin());

        List<User> buyers = new ArrayList<>();
        forecast.setBuyer(buyers);
        Assertions.assertEquals(buyers, forecast.getBuyer());

        String description = "test forecast";
        forecast.setDescription(description);
        Assertions.assertEquals(description, forecast.getDescription());

        ForecastTypeEnum forecastType = ForecastTypeEnum.Long;
        forecast.setForecastType(forecastType);
        Assertions.assertEquals(forecastType, forecast.getForecastType());

        boolean isActive = true;
        forecast.setActive(isActive);
        Assertions.assertEquals(isActive, forecast.isActive());

        BigDecimal price = BigDecimal.valueOf(10);
        forecast.setPrice(price);
        Assertions.assertEquals(price, forecast.getPrice());

        LocalDateTime created = LocalDateTime.now();
        forecast.setCreated(created);
        Assertions.assertEquals(created, forecast.getCreated());

        LocalDateTime closed = LocalDateTime.now();
        forecast.setClosed(closed);
        Assertions.assertEquals(closed, forecast.getClosed());

        Set<Picture> picturesUrl = new HashSet<>();
        forecast.setPicturesUrl(picturesUrl);
        Assertions.assertEquals(picturesUrl, forecast.getPicturesUrl());
    }
}
