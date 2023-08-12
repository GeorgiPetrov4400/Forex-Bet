package forexbet.tradingforecasts.model.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PictureViewModelTest {

    @Test
    public void testSetTitle() {
        // Arrange
        PictureViewModel pictureViewModel = new PictureViewModel();
        String title = "Test title";

        // Act
        pictureViewModel.setTitle(title);

        // Assert
        Assertions.assertEquals(title, pictureViewModel.getTitle());
    }

    @Test
    public void testSetUrl() {
        // Arrange
        PictureViewModel pictureViewModel = new PictureViewModel();
        String url = "https://example.com/test.jpg";

        // Act
        pictureViewModel.setUrl(url);

        // Assert
        Assertions.assertEquals(url, pictureViewModel.getUrl());
    }

    @Test
    public void testSetPublicId() {
        // Arrange
        PictureViewModel pictureViewModel = new PictureViewModel();
        String publicId = "abc123";

        // Act
        pictureViewModel.setPublicId(publicId);

        // Assert
        Assertions.assertEquals(publicId, pictureViewModel.getPublicId());
    }

    @Test
    public void testSetAndGetId() {
        // Arrange
        PictureViewModel pictureViewModel = new PictureViewModel();
        Long id = 123L;

        // Act
        pictureViewModel.setId(id);

        // Assert
        Assertions.assertEquals(id, pictureViewModel.getId());
    }
}
