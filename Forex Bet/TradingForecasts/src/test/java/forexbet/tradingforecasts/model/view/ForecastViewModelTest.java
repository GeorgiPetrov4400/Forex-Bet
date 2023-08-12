package forexbet.tradingforecasts.model.view;

import forexbet.tradingforecasts.model.entity.Category;
import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;
import forexbet.tradingforecasts.model.view.ForecastViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ForecastViewModelTest {

    @Mock
    private Category category;

    @Test
    public void testGettersAndSetters() {
        ForecastViewModel forecastViewModel = new ForecastViewModel()
                .setId(1L)
                .setCategory(category)
                .setForecastType(ForecastTypeEnum.Short)
                .setDescription("Test forecast")
                .setPictureUrl("https://example.com/picture.jpg")
                .setPrice(BigDecimal.valueOf(10.00))
                .setCreated(LocalDateTime.of(2023, 4, 7, 10, 30))
                .setClosed(LocalDateTime.of(2023, 4, 10, 12, 0))
                .setActive(true);

        assertEquals(1L, forecastViewModel.getId());
        assertEquals(category, forecastViewModel.getCategory());
        assertEquals(ForecastTypeEnum.Short, forecastViewModel.getForecastType());
        assertEquals("Test forecast", forecastViewModel.getDescription());
        assertEquals("https://example.com/picture.jpg", forecastViewModel.getPictureUrl());
        assertEquals(BigDecimal.valueOf(10.00), forecastViewModel.getPrice());
        assertEquals(LocalDateTime.of(2023, 4, 7, 10, 30), forecastViewModel.getCreated());
        assertEquals(LocalDateTime.of(2023, 4, 10, 12, 0), forecastViewModel.getClosed());
        assertEquals(true, forecastViewModel.isActive());
    }
}
