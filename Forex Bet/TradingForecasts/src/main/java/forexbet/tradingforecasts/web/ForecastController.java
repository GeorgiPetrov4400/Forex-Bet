package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.model.view.ForecastViewModel;
import forexbet.tradingforecasts.model.view.PictureViewModel;
import forexbet.tradingforecasts.model.entity.Category;
import forexbet.tradingforecasts.model.entity.enums.CategoryNameEnum;
import forexbet.tradingforecasts.service.CategoryService;
import forexbet.tradingforecasts.service.ForecastService;
import forexbet.tradingforecasts.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/forecasts")
public class ForecastController {

    private final ForecastService forecastService;
    private final PictureService pictureService;
    private final CategoryService categoryService;

    public ForecastController(ForecastService forecastService, PictureService pictureService, CategoryService categoryService) {
        this.forecastService = forecastService;
        this.pictureService = pictureService;
        this.categoryService = categoryService;
    }

    @GetMapping("/ui")
    public String freeForecasts() {
        return "free-forecasts";
    }

    @GetMapping("/eur-usd")
    public String forecastsEurUsd(Model model) {
        Category byCategoryNameEnum = categoryService.findByCategoryNameEnum(CategoryNameEnum.EurUsd);

        List<ForecastViewModel> allActiveEurUsdForecasts =
                forecastService.getActiveForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allActiveEurUsdForecasts);
        model.addAttribute("allActiveEurUsdForecasts", allActiveEurUsdForecasts);

        List<ForecastViewModel> allExpiredEurUsdForecast =
                forecastService.getAllExpiredForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allExpiredEurUsdForecast);
        model.addAttribute("allExpiredEurUsdForecast", allExpiredEurUsdForecast);

        return "eur-usd";
    }

    @GetMapping("/gold-forecast")
    public String forecastGold(Model model) {
        Category byCategoryNameEnum = categoryService.findByCategoryNameEnum(CategoryNameEnum.Gold);

        List<ForecastViewModel> allActiveGoldForecasts =
                forecastService.getActiveForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allActiveGoldForecasts);
        model.addAttribute("allActiveGoldForecasts", allActiveGoldForecasts);

        List<ForecastViewModel> allExpiredGoldForecast =
                forecastService.getAllExpiredForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allExpiredGoldForecast);
        model.addAttribute("allExpiredGoldForecast", allExpiredGoldForecast);

        return "gold";
    }

    @GetMapping("/dax-forecast")
    public String forecastDax(Model model) {
        Category byCategoryNameEnum = categoryService.findByCategoryNameEnum(CategoryNameEnum.Dax);

        List<ForecastViewModel> allActiveDaxForecasts =
                forecastService.getActiveForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allActiveDaxForecasts);
        model.addAttribute("allActiveDaxForecasts", allActiveDaxForecasts);

        List<ForecastViewModel> allExpiredDaxForecast =
                forecastService.getAllExpiredForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allExpiredDaxForecast);
        model.addAttribute("allExpiredDaxForecast", allExpiredDaxForecast);

        return "dax";
    }

    @GetMapping("/dow-jones-forecast")
    public String forecastDowJones(Model model) {
        Category byCategoryNameEnum = categoryService.findByCategoryNameEnum(CategoryNameEnum.DowJones);

        List<ForecastViewModel> allActiveDowJonesForecasts =
                forecastService.getActiveForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allActiveDowJonesForecasts);
        model.addAttribute("allActiveDowJonesForecasts", allActiveDowJonesForecasts);

        List<ForecastViewModel> allExpiredDowJonesForecast =
                forecastService.getAllExpiredForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allExpiredDowJonesForecast);
        model.addAttribute("allExpiredDowJonesForecast", allExpiredDowJonesForecast);

        return "dow-jones";
    }

    @GetMapping("/nasdaq-forecast")
    public String forecastNasdaq(Model model) {
        Category byCategoryNameEnum = categoryService.findByCategoryNameEnum(CategoryNameEnum.Nasdaq);

        List<ForecastViewModel> allActiveNasdaqForecasts =
                forecastService.getActiveForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allActiveNasdaqForecasts);
        model.addAttribute("allActiveNasdaqForecasts", allActiveNasdaqForecasts);

        List<ForecastViewModel> allExpiredNasdaqForecast =
                forecastService.getAllExpiredForecastsByCategory(byCategoryNameEnum);

        addForecastPicture(allExpiredNasdaqForecast);
        model.addAttribute("allExpiredNasdaqForecast", allExpiredNasdaqForecast);

        return "nasdaq";
    }

    private void addForecastPicture(List<ForecastViewModel> allActiveForecasts) {
        for (ForecastViewModel activeForecast : allActiveForecasts) {
            PictureViewModel forecastId = pictureService.findByForecastId(activeForecast.getId());
            activeForecast.setPictureUrl(forecastId.getUrl());
        }
    }
}
