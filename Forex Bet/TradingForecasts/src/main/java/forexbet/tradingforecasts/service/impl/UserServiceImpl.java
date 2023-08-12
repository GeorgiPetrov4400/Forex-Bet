package forexbet.tradingforecasts.service.impl;

import forexbet.tradingforecasts.model.entity.User;
import forexbet.tradingforecasts.model.entity.UserRole;
import forexbet.tradingforecasts.model.entity.enums.UserRoleEnum;
import forexbet.tradingforecasts.model.view.UserViewModel;
import forexbet.tradingforecasts.repository.UserRepository;
import forexbet.tradingforecasts.service.UserRoleService;
import forexbet.tradingforecasts.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    //  private final String defaultAdminPass;
    //  private final String defaultModeratorPass;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService,
                           PasswordEncoder passwordEncoder,
                           //  @Value("${TradingForecasts.Admin.defaultPass}") String defaultAdminPass,
                           //    @Value("${TradingForecasts.Moderator.defaultPass}") String defaultModeratorPass,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        //   this.defaultAdminPass = defaultAdminPass;
        //    this.defaultModeratorPass = defaultModeratorPass;
    }

    @Override
    public void initAdmin() {
        User adminUser = new User()
                .setEmail("admin@example.com")
                .setUsername("Admin")
                .setPassword(passwordEncoder.encode("12345"))
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setRoles(userRoleService.findAll());

        userRepository.save(adminUser);
    }

    @Override
    public void initModerator() {
        List<UserRole> moderatorRoles = new ArrayList<>();

        UserRole moderatorRole = userRoleService.findUserRole(UserRoleEnum.Moderator).orElseThrow();
        UserRole userRole = userRoleService.findUserRole(UserRoleEnum.User).orElseThrow();

        moderatorRoles.add(moderatorRole);
        moderatorRoles.add(userRole);

        User moderatorUser = new User()
                .setEmail("moderator@example.com")
                .setUsername("Moderator")
                .setPassword(passwordEncoder.encode("12345"))
                .setFirstName("Moder")
                .setLastName("Moderatorov")
                .setRoles(moderatorRoles);

        userRepository.save(moderatorUser);
    }

    @Override
    public void registerUser(UserViewModel userViewModel) {
        if (!userViewModel.getPassword().equals(userViewModel.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password should match");
        }

        Optional<User> findByEmail = userRepository.findByEmail(userViewModel.getEmail());

        if (findByEmail.isPresent()) {
            throw new RuntimeException("Email is already used");
        }

        Optional<User> findByUsername = userRepository.findByUsername(userViewModel.getUsername());

        if (findByUsername.isPresent()) {
            throw new RuntimeException("Username is already used");
        }

        UserRole normalUserRole = userRoleService.findUserRole(UserRoleEnum.User).orElseThrow();

        User normalUser = new User()
                .setEmail(userViewModel.getEmail())
                .setUsername(userViewModel.getUsername())
                .setPassword(passwordEncoder.encode(userViewModel.getPassword()))
                .setFirstName(userViewModel.getFirstName())
                .setLastName(userViewModel.getLastName())
                .setRoles(List.of(normalUserRole));

        modelMapper.map(userRepository.save(normalUser), UserViewModel.class);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUserChanges(User changeUsername) {
        userRepository.save(changeUsername);
    }
}
