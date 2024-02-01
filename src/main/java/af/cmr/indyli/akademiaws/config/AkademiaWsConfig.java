package af.cmr.indyli.akademiaws.config;

import af.cmr.indyli.akademia.business.config.AkdemiaBusinessConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "REST API", version = "1.0",
        description = "REST API description...",
        contact = @Contact(name = "Name Surname")),
        security = {@SecurityRequirement(name = "bearerToken")}
)
@SecuritySchemes({
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP,
                scheme = "bearer", bearerFormat = "JWT")
})
@Import(AkdemiaBusinessConfig.class)
@ComponentScan(basePackages = { "af.cmr.indyli.akademiaws.*" })
public class AkademiaWsConfig {

	
    @Bean
    public OpenAPI akademiaOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("AKADEMIA-DOC")
                        .description("Documentation of api")
                        .version("1.0"));
    }

    @Bean(value = "BCRYPT_ENCODER")
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}