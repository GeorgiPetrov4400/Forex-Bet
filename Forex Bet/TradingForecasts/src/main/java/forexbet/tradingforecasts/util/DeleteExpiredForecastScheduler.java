package forexbet.tradingforecasts.util;

import forexbet.tradingforecasts.model.view.ForecastViewModel;
import forexbet.tradingforecasts.service.ForecastService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class DeleteExpiredForecastScheduler {

    private final ForecastService forecastService;

    public DeleteExpiredForecastScheduler(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @Scheduled(cron = "0 2 0 * * *")
    public void deleteForecastsExpireDateIsMoreThanTwoMonths() {
        List<ForecastViewModel> expiredForecasts = forecastService.getExpiredForecasts();

        for (ForecastViewModel expiredForecast : expiredForecasts) {
            LocalDateTime currentDateAndTime = LocalDateTime.now();

            LocalDateTime dateExpired = expiredForecast.getClosed();

            long months = ChronoUnit.MONTHS.between(dateExpired, currentDateAndTime);
            if (months > 2) {
                forecastService.removeForecastById(expiredForecast.getId());
            }
        }
    }

//    @Scheduled(cron = "* */2 * * * *")
//    public void deleteForecastsExpiredBeforeTenMinutes() {
//        List<ForecastDTO> expiredForecasts = forecastService.getExpiredForecasts();
//
//        for (ForecastDTO expiredForecast : expiredForecasts) {
//            LocalDateTime currentDateAndTime = LocalDateTime.now();
//
//            LocalDateTime dateExpired = expiredForecast.getClosed();
//
//            long minutes = ChronoUnit.MINUTES.between(dateExpired, currentDateAndTime);
//            if (minutes > 10) {
//                forecastService.removeForecastById(expiredForecast.getId());
//            }
//        }
//    }
}
