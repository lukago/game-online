package pl.zzpwj.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zzpwj.game.model.User;
import pl.zzpwj.game.repository.IUserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void delete(final String login) {
        userRepository.delete(findOneByLogin(login));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOneByLogin(final String login) {
        return userRepository.findOneByLogin(login).orElseThrow(NoSuchElementException::new);
    }

    public List<User> findAllByEmail(final String email) {
        return userRepository.findAllByEmail(email);
    }
}
