package forexbet.tradingforecasts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClosedForMaintenanceController {

    @GetMapping("/closed")
    public String closed() {
        return "maintenance-closed";
    }
}
