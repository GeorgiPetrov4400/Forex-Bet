package forexbet.tradingforecasts.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PictureTest {

    private Picture picture;

    @BeforeEach
    void setUp() {
        picture = new Picture();
    }

    @Test
    void setTitle_ShouldSetTitle() {
        String title = "Example title";
        picture.setTitle(title);
        assertEquals(title, picture.getTitle());
    }

    @Test
    void setUrl_ShouldSetUrl() {
        String url = "https://example.com/image.jpg";
        picture.setUrl(url);
        assertEquals(url, picture.getUrl());
    }

    @Test
    void setPublicId_ShouldSetPublicId() {
        String publicId = "example-public-id";
        picture.setPublicId(publicId);
        assertEquals(publicId, picture.getPublicId());
    }

    @Test
    void setForecast_ShouldSetForecast() {
        Forecast forecast = new Forecast();
        picture.setForecast(forecast);
        assertNotNull(picture.getForecast());
        assertEquals(forecast, picture.getForecast());
    }
}
