package forexbet.tradingforecasts.service.impl;

import forexbet.tradingforecasts.model.entity.UserRole;
import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;
import forexbet.tradingforecasts.repository.UserRoleRepository;
import forexbet.tradingforecasts.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void seedUserRoles() {
        if (userRoleRepository.count() != 0) {
            return;
        }

        Arrays.stream(UserRoleEnum.values()).forEach(userRoleEnum -> {
            UserRole userRole = new UserRole();
            userRole.setRole(userRoleEnum);

            userRoleRepository.save(userRole);
        });
    }

    @Override
    public Optional<UserRole> findUserRole(UserRoleEnum role) {
        return userRoleRepository.findUserRoleByRole(role);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
