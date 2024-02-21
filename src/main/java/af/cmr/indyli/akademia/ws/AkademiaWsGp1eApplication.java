package af.cmr.indyli.akademia.ws;

import af.cmr.indyli.akademia.ws.config.AkademiaWsGp1eConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AkademiaWsGp1eConfig.class)
public class AkademiaWsGp1eApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkademiaWsGp1eApplication.class, args);
    }

}
