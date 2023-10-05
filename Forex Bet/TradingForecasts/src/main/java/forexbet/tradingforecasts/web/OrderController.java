package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.model.view.ForecastViewModel;
import forexbet.tradingforecasts.model.view.PictureViewModel;
import forexbet.tradingforecasts.service.ForecastService;
import forexbet.tradingforecasts.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/active-forecasts")
public class OrderController {

    private final ForecastService forecastService;
    private final PictureService pictureService;

    public OrderController(ForecastService forecastService, PictureService pictureService) {
        this.forecastService = forecastService;
        this.pictureService = pictureService;
    }

    @GetMapping("orders")
    public String getAllActiveForecast(Principal principal, Model model) {

        List<ForecastViewModel> userBoughtForecasts = forecastService.getUserBoughtForecasts(principal);
        addForecastPicture(userBoughtForecasts);
        model.addAttribute("userBoughtForecasts", userBoughtForecasts);

        List<ForecastViewModel> ownForecastsAdded = forecastService.getOwnForecastsAdded(principal);
        addForecastPicture(ownForecastsAdded);
        model.addAttribute("ownForecastsAdded", ownForecastsAdded);

        List<ForecastViewModel> allActiveForecasts = forecastService.getActiveForecasts();
        addForecastPicture(allActiveForecasts);
        model.addAttribute("allActiveForecast", allActiveForecasts);

        return "orders";
    }

    private void addForecastPicture(List<ForecastViewModel> allActiveForecasts) {
        for (ForecastViewModel activeForecast : allActiveForecasts) {
            PictureViewModel forecastId = pictureService.findByForecastId(activeForecast.getId());
            activeForecast.setPictureUrl(forecastId.getUrl());
        }
    }

    @GetMapping("/orders/buy/{id}")
    public String buyForecast(@PathVariable Long id, Principal principal) {
        forecastService.buyForecast(id, principal);

        return "redirect:/active-forecasts/orders";
    }

    @GetMapping("/orders/expire/{id}")
    public String expiredForecast(@PathVariable Long id) {
        forecastService.expireForecastById(id);

        return "redirect:/active-forecasts/orders";
    }

//    @GetMapping("/orders/remove/{id}")
//    public String removeForecast(@PathVariable Long id) {
//        forecastService.removeForecastById(id);
//
//        return "redirect:/active-forecasts/orders";
//    }
}
