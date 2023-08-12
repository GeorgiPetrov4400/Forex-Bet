package forexbet.tradingforecasts.service;

import forexbet.tradingforecasts.model.entity.UserRole;
import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    void seedUserRoles();

    Optional<UserRole> findUserRole(UserRoleEnum role);

    List<UserRole> findAll();
}
