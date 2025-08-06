package hub.forum.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI forumHubOpenAPI() {
       return new OpenAPI().info( new Info()
               .title("Forum Hub API")
               .version("1.0")
               .description("Swagger documentation of Forum Hub API"))
               .addSecurityItem(
                       new SecurityRequirement()
                       .addList("bearerAuth")
               )
               .components(
                       new Components()
                             .addSecuritySchemes("bearerAuth",
                                  new SecurityScheme()
                                 .type(SecurityScheme.Type.HTTP)
                                 .bearerFormat("JWT")
                                 .scheme("bearer")
                             )
               );
    }
}
