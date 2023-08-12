package forexbet.tradingforecasts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradingForecastsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingForecastsApplication.class, args);
	}

}
