package af.cmr.indyli.akdemia.ws;

import af.cmr.indyli.akdemia.ws.config.AkdemiaWsGp1eConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AkdemiaWsGp1eConfig.class)
public class AkdemiaWsGp1eApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkdemiaWsGp1eApplication.class, args);
    }

}
