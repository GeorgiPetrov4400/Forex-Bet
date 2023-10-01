package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.model.dto.ForecastAddDTO;
import forexbet.tradingforecasts.service.ForecastService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/forecasts")
public class ForecastAddController {

    private final ForecastService forecastService;

    public ForecastAddController(ForecastService forecastService) {
        this.forecastService = forecastService;
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

        return "redirect:/forecast";

    }
}
