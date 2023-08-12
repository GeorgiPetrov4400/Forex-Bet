package forexbet.tradingforecasts.init;

import forexbet.tradingforecasts.repository.UserRepository;
import forexbet.tradingforecasts.service.CategoryService;
import forexbet.tradingforecasts.service.UserRoleService;
import forexbet.tradingforecasts.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final UserRepository userRepository;

    public DatabaseInit(CategoryService categoryService,
                        UserRoleService userRoleService,
                        UserService userService,
                        UserRepository userRepository) {
        this.categoryService = categoryService;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryService.seedCategories();
        userRoleService.seedUserRoles();

        if (userRepository.count() == 0) {
        userService.initAdmin();
        userService.initModerator();
        }
    }
}
