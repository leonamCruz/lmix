package top.lmix.sitelmix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SiteLmixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteLmixApplication.class, args);
    }

}
