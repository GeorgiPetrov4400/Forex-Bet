package forexbet.tradingforecasts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActiveForecastController {

    @GetMapping("/eur-usd")
    public String eurUsd() {
        return "eur-usd";
    }

    @GetMapping("/gold")
    public String gold() {
        return "gold";
    }

    @GetMapping("/dax")
    public String dax() {
        return "dax";
    }

    @GetMapping("/dow-jones")
    public String dowJones() {
        return "dow-jones";
    }

    @GetMapping("/nasdaq")
    public String nasdaq() {
        return "nasdaq";
    }
}
