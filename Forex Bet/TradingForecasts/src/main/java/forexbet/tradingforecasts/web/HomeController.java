package forexbet.tradingforecasts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/forecast")
    public String forecast() {
        return "forecast";
    }

    @GetMapping("/testimonial")
    public String testimonial() {
        return "testimonial";
    }

    @GetMapping("/free-forecasts")
    public String freeForecasts() {
        return "free-forecasts";
    }

    @GetMapping("/my-forecasts")
    public String myForecasts() {
        return "my-forecasts";
    }

    @GetMapping("/active-forecasts")
    public String activeForecasts() {
        return "active-forecasts";
    }

    @GetMapping("/expired-forecasts")
    public String expiredForecasts() {
        return "expired-forecasts";
    }
}
