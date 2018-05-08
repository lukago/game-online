package pl.zzpwj.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zzpwj.game.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {

    Optional<User> findOneByLogin(final String login);

    List<User> findAllByEmail(final String email);
}
