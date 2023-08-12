package forexbet.tradingforecasts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFoundException extends RuntimeException {

    private long orderId;

    public OrderNotFoundException(long orderId) {
        super("Order with ID " + orderId + " not found");
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public OrderNotFoundException setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }
}
