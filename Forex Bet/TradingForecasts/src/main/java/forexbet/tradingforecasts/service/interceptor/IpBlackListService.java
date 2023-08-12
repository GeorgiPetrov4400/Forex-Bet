package forexbet.tradingforecasts.service.interceptor;

import org.springframework.stereotype.Service;

@Service
public class IpBlackListService {

    public boolean isBlacklisted(String ipAddress) {
        return ipAddress.startsWith("41") || ipAddress.startsWith("102");
    }
}
