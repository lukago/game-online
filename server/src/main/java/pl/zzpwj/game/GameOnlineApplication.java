package pl.zzpwj.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GameOnlineApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(GameOnlineApplication.class);

    public static void main(String[] args) {
        log.info("Starting application");
        SpringApplication.run(GameOnlineApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GameOnlineApplication.class);
    }
}
