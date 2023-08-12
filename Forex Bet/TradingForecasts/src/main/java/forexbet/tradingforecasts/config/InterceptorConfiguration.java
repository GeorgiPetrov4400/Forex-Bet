package forexbet.tradingforecasts.config;

import forexbet.tradingforecasts.interceptors.ClosedForMaintenanceInterceptor;
import forexbet.tradingforecasts.interceptors.IpBlackListInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final IpBlackListInterceptor ipBlackListInterceptor;
    private final ClosedForMaintenanceInterceptor closedForMaintenanceInterceptor;

    public InterceptorConfiguration(IpBlackListInterceptor blackListInterceptor, ClosedForMaintenanceInterceptor closedForMaintenanceInterceptor) {
        this.ipBlackListInterceptor = blackListInterceptor;
        this.closedForMaintenanceInterceptor = closedForMaintenanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlackListInterceptor);
        registry.addInterceptor(closedForMaintenanceInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
