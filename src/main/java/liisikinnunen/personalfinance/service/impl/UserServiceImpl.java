package liisikinnunen.personalfinance.service.impl;

import liisikinnunen.personalfinance.model.User;
import liisikinnunen.personalfinance.repository.UserRepository;
import liisikinnunen.personalfinance.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    @NonNull
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll(null);
    }
}
