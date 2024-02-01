package af.cmr.indyli.akademiaws;


import af.cmr.indyli.akademiaws.config.AkademiaWsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AkademiaWsConfig.class)
public class AkademiaWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkademiaWsApplication.class, args);
    }

}
