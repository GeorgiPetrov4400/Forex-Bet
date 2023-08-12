package forexbet.tradingforecasts.interceptors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClosedForMaintenanceInterceptorTest {

    @Test
    public void testPreHandleReturnsTrueForNonOrderRequests() throws Exception {
        // Arrange
        ClosedForMaintenanceInterceptor interceptor = new ClosedForMaintenanceInterceptor();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Object handler = new Object();
        when(request.getRequestURI()).thenReturn("/some/other/url");

        // Act
        boolean result = interceptor.preHandle(request, response, handler);

        // Assert
        assertTrue(result);
    }
}
