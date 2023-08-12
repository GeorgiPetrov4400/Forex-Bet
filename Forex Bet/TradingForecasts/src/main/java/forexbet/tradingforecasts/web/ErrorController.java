package forexbet.tradingforecasts.web;

import forexbet.tradingforecasts.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ModelAndView onOrderNotFound(OrderNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("order-not-found");

        modelAndView.addObject("orderId", onfe.getOrderId());

        return modelAndView;
    }
}
