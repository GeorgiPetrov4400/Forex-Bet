package forexbet.tradingforecasts.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;

@Component
public class ClosedForMaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/orders/order")) {
            LocalTime now = LocalTime.now();
            if (now.isAfter(LocalTime.of(0, 0,0)) && now.isBefore(LocalTime.of(0, 0, 10))) {
                response.sendRedirect("/closed");
            }
            return true;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);

    }


}
