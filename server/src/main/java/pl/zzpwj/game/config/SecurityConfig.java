package pl.zzpwj.game.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import pl.zzpwj.game.service.UserDetailsBindService;
import pl.zzpwj.game.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private UserDetailsBindService userDetailsService;

    @Autowired
    public SecurityConfig(CustomLogoutSuccessHandler customLogoutSuccessHandler,
                          UserDetailsBindService userDetailsService) {
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyRole("USER")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}