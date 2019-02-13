package es.iti.tamagochi.portal.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"es.iti.tamagochi.portal.web.controllers"})
@EnableWebMvc
public class PortalSpringConfiguration {
    
}
