package forexbet.tradingforecasts.service.impl;

import forexbet.tradingforecasts.model.view.PictureViewModel;
import forexbet.tradingforecasts.repository.PictureRepository;
import forexbet.tradingforecasts.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PictureViewModel findByForecastId(Long id) {
        return pictureRepository.findByForecastId(id)
                .map(picture -> modelMapper.map(picture, PictureViewModel.class)).orElse(null);
    }
}
