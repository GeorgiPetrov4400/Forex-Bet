package forexbet.tradingforecasts.service;

import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.model.view.UserViewModel;

import java.util.Optional;

public interface UserService {

    void initAdmin();

    void initModerator();

    void registerUser(UserViewModel userViewModel);

    User getUserByUsername(String username);

    User findById(Long id);

    void saveUserChanges(User changeUsername);

}
