package pl.zzpwj.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zzpwj.game.repository.IUserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsBindService implements UserDetailsService {

    private IUserRepository userRepository;

    @Autowired
    public UserDetailsBindService(IUserRepository userService) {
        this.userRepository = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pl.zzpwj.game.model.User user = userRepository.findOneByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getLogin(), user.getPassword(), getUserAuthorities());
    }

    private Set<GrantedAuthority> getUserAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(grantedAuthority);
        return authorities;
    }
}
