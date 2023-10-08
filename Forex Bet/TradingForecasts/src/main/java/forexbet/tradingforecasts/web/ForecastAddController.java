package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.model.dto.ForecastAddDTO;
import forexbet.tradingforecasts.model.view.ForecastViewModel;
import forexbet.tradingforecasts.model.view.PictureViewModel;
import forexbet.tradingforecasts.service.ForecastService;
import forexbet.tradingforecasts.service.PictureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/forecasts")
public class ForecastAddController {

    private final ForecastService forecastService;
    private final PictureService pictureService;

    public ForecastAddController(ForecastService forecastService, PictureService pictureService) {
        this.forecastService = forecastService;
        this.pictureService = pictureService;
    }

    @ModelAttribute
    public ForecastAddDTO forecastAddDTO() {
        return new ForecastAddDTO();
    }

    @GetMapping("/add")
    public String add() {
        return "forecast-add";
    }

    @PostMapping("/add")
    public String addForecast(@Valid ForecastAddDTO forecastAddDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("forecastAddDTO", forecastAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.forecastAddDTO",
                            bindingResult);

            return "redirect:add";
        }

        forecastService.createForecast(forecastAddDTO, principal, forecastAddDTO.getPictureUrl());

        return "redirect:/active-forecasts/orders";

    }

    @GetMapping("own-added")
    public String ownAdded(Principal principal, Model model) {
        List<ForecastViewModel> ownForecastsAdded = forecastService.getOwnForecastsAdded(principal);
        addForecastPicture(ownForecastsAdded);
        model.addAttribute("ownForecastsAdded", ownForecastsAdded);

        return "own-add-forecasts";
    }

    @GetMapping("/own-added/expire/{id}")
    public String expiredForecast(@PathVariable Long id) {
        forecastService.expireForecastById(id);

        return "redirect:/forecasts/own-added";
    }

    private void addForecastPicture(List<ForecastViewModel> allActiveForecasts) {
        for (ForecastViewModel activeForecast : allActiveForecasts) {
            PictureViewModel forecastId = pictureService.findByForecastId(activeForecast.getId());
            activeForecast.setPictureUrl(forecastId.getUrl());
        }
    }
}
