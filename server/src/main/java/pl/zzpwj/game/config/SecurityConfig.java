package pl.zzpwj.game.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import pl.zzpwj.game.service.UserDetailsBindService;

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
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyRole("USER")
                .and()
                .httpBasic().authenticationEntryPoint(swaggerAuthenticationEntryPoint())
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Swagger Realm");
        return entryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN", "USER");
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
    }

}