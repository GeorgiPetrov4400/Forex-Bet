package forexbet.tradingforecasts.service;

import forexbet.tradingforecasts.model.view.PictureViewModel;

public interface PictureService {

    PictureViewModel findByForecastId(Long id);
}
