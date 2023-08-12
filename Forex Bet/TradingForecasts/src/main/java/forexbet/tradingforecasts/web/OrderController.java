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
@RequestMapping("/orders")
public class OrderController {

    private final ForecastService forecastService;
    private final PictureService pictureService;

    public OrderController(ForecastService forecastService, PictureService pictureService) {
        this.forecastService = forecastService;
        this.pictureService = pictureService;
    }

    @GetMapping("/order")
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

        List<ForecastViewModel> expiredForecasts = forecastService.getExpiredForecasts();
        addForecastPicture(expiredForecasts);
        model.addAttribute("expiredForecasts", expiredForecasts);

        return "order";
    }

    private void addForecastPicture(List<ForecastViewModel> allActiveForecasts) {
        for (ForecastViewModel activeForecast : allActiveForecasts) {
            PictureViewModel forecastId = pictureService.findByForecastId(activeForecast.getId());
            activeForecast.setPictureUrl(forecastId.getUrl());
        }
    }

    @GetMapping("/order/buy/{id}")
    public String buyForecast(@PathVariable Long id, Principal principal) {
        forecastService.buyForecast(id, principal);

        return "redirect:/orders/order";
    }

    @GetMapping("/order/expire/{id}")
    public String expiredForecast(@PathVariable Long id) {
        forecastService.expireForecastById(id);

        return "redirect:/orders/order";
    }

    @GetMapping("/orders/remove/{id}")
    public String removeForecast(@PathVariable Long id) {
        forecastService.removeForecastById(id);

        return "redirect:/orders/order";
    }
}
