package af.cmr.indyli.akademiaws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import af.cmr.indyli.akademia.business.config.AkdemiaBusinessConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@Import(AkdemiaBusinessConfig.class)
@ComponentScan(basePackages = { "af.cmr.indyli.akademiaws.controller" })
public class AkademiaWsConfig {

	@Bean
	OpenAPI akademiaOpenAPI() {
		return new OpenAPI().info(new Info().title("AKADEMIA-DOC").description("Documentation of api").version("1.0"));
	}
}
