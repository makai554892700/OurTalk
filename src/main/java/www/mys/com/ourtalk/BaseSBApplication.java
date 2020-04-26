package www.mys.com.ourtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import www.mys.com.ourtalk.common.encrypt.EnableSecurity;

@SpringBootApplication
@EnableScheduling
@PropertySource(value = {
        "classpath:/properties/user.properties",
}, encoding = "UTF-8")
@EnableSecurity
public class BaseSBApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSBApplication.class, args);
    }

}
