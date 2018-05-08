package pl.zzpwj.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.game.model.User;
import pl.zzpwj.game.repository.IUserRepository;

import java.util.List;

@Service
public class UserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(final User user) {
        return userRepository.save(user);
    }

    public void delete(final User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOneByLogin(final String login) {
        return userRepository.findOneByLogin(login).orElse(null);
    }

    public List<User> findAllByEmail(final String email) {
        return userRepository.findAllByEmail(email);
    }
}
