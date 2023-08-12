package forexbet.tradingforecasts.service.impl;

import forexbet.tradingforecasts.model.dto.ForecastAddDTO;
import forexbet.tradingforecasts.model.entity.Forecast;
import forexbet.tradingforecasts.model.entity.Picture;
import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;
import forexbet.tradingforecasts.model.entity.enums.ForecastTypeEnum;
import forexbet.tradingforecasts.model.view.ForecastViewModel;
import forexbet.tradingforecasts.repository.ForecastRepository;
import forexbet.tradingforecasts.repository.PictureRepository;
import forexbet.tradingforecasts.repository.UserRepository;
import forexbet.tradingforecasts.service.CategoryService;
import forexbet.tradingforecasts.service.UserService;
import forexbet.tradingforecasts.service.cloudinary.PictureCloudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ForecastServiceImplTest {

    @Mock
    private ForecastRepository forecastRepository;
    @Mock
    private UserService userService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PictureRepository pictureRepository;
    @Mock
    private PictureCloudService pictureCloudService;

    private ModelMapper modelMapper = new ModelMapper();

    private ForecastServiceImpl forecastService;

    @BeforeEach
    void setUp() throws Exception {
        forecastService = new ForecastServiceImpl(forecastRepository, modelMapper, userService, categoryService,
                userRepository, pictureRepository, pictureCloudService);
    }

    @Test
    public void createForecast_withValidData_shouldSaveForecast() throws Exception {
        // arrange
        ForecastAddDTO forecastAddDTO = new ForecastAddDTO();
        forecastAddDTO.setCategory(CategoryNameEnum.EurUsd);
        forecastAddDTO.setType(ForecastTypeEnum.Short);
        forecastAddDTO.setDescription("DESCRIPTION");
        forecastAddDTO.setPrice(BigDecimal.valueOf(1.0));

        User admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("admin");


        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(admin));
        when(userService.findById(1L)).thenReturn(admin);
        when(categoryService.findByCategoryNameEnum(any())).thenReturn(null);
        when(pictureCloudService.savePicture(any())).thenReturn("URL");

        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());

        // act
        forecastService.createForecast(forecastAddDTO, principal, file);

        // assert
        verify(forecastRepository).save(any(Forecast.class));
        verify(pictureRepository).save(any(Picture.class));
    }

    @Test
    @WithMockUser
    public void createForecast_withInvalidAdmin_shouldNotSaveForecast() throws Exception {
        // arrange
        ForecastAddDTO forecastAddDTO = new ForecastAddDTO();
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("nonexistent_admin");

        // act
        forecastService.createForecast(forecastAddDTO, principal, null);

        // assert
        verify(forecastRepository, never()).save(any(Forecast.class));
    }

    @Test
    void testGetUserBoughtForecasts() {
        User buyer = new User();
        buyer.setId(1L);
        buyer.setUsername("buyer");

        ArrayList<Forecast> forecastList = new ArrayList<Forecast>();
        Forecast f = new Forecast();
        f.setId(1L);
        f.setDescription("test forecast");
        forecastList.add(f);

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("buyer");

        when(userRepository.findByUsername(buyer.getUsername())).thenReturn(Optional.of(buyer));

        when(forecastRepository.findAllByBuyer_IdAndPriceNotNullOrderByIdDesc(buyer.getId()))
                .thenReturn(forecastList);


        List<ForecastViewModel> actualForecasts = forecastService.getUserBoughtForecasts(principal);

        assertEquals(1, actualForecasts.size());
        assertEquals("test forecast", actualForecasts.get(0).getDescription());
    }

    @Test
    void testGetOwnForecastsAdded() {
        User admin = new User();
        admin.setId(1L);
        admin.setUsername("Admin");

        ArrayList<Forecast> forecastList = new ArrayList<Forecast>();
        Forecast f = new Forecast();
        f.setId(1L);
        f.setDescription("description");
        forecastList.add(f);

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("Admin");

        when(userRepository.findByUsername(admin.getUsername())).thenReturn(Optional.of(admin));

        when(forecastRepository.findByAdmin_IdAndClosedIsNullOrderByCreatedDesc(admin.getId()))
                .thenReturn(forecastList);


        List<ForecastViewModel> actualForecasts = forecastService.getOwnForecastsAdded(principal);

        assertEquals(1, actualForecasts.size());
        assertEquals("description", actualForecasts.get(0).getDescription());
    }
}