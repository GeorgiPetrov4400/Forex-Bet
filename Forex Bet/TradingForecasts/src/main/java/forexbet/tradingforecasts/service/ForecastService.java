package forexbet.tradingforecasts.service;

import forexbet.tradingforecasts.model.dto.ForecastAddDTO;
import forexbet.tradingforecasts.model.view.ForecastViewModel;
import forexbet.tradingforecasts.model.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface ForecastService {

    void createForecast(ForecastAddDTO forecastAddDTO,Principal principal, MultipartFile imageFile);

    List<ForecastViewModel> getOwnForecastsAdded(Principal principal);

    List<ForecastViewModel> getActiveForecasts();

    List<ForecastViewModel> getUserBoughtForecasts(Principal principal);

    void buyForecast(Long id, Principal principal);

    void expireForecastById(Long id);

    List<ForecastViewModel> getExpiredForecasts();

    void removeForecastById(Long id);

    List<ForecastViewModel> getAllActiveFreeForecasts();

    List<ForecastViewModel> getActiveForecastsByCategory(Category category);

    List<ForecastViewModel> getAllExpiredForecastsByCategory(Category category);


}
