
package es.iti.spring.core.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = 
        {"es.iti.spring.core.service"}
)
public class CoreSpringConfiguration {
    
}
