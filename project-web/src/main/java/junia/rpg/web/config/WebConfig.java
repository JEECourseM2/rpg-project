package junia.rpg.web.config;

import com.shieldsolutions.velocity.view.VelocityConfigurer;
import com.shieldsolutions.velocity.view.VelocityViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "junia.rpg.web.controller")
public class WebConfig implements WebMvcConfigurer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String RESOURCE_LOCATION = "/webjars/";
        registry.addResourceHandler("/webjars/**").addResourceLocations(RESOURCE_LOCATION);
        LOGGER.info("Add resource handlers at {}",  RESOURCE_LOCATION);
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        final String VELOCITY_PATH = "/WEB-INF/velocity";
        velocityConfigurer.setResourceLoaderPath(VELOCITY_PATH);
        LOGGER.info("Add velocity configurer at {}",  VELOCITY_PATH);
        return velocityConfigurer;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
        final String VELOCITY_SUFFIX = ".vm";
        velocityViewResolver.setSuffix(VELOCITY_SUFFIX);
        LOGGER.info("Add velocity view resolver with suffix {}", VELOCITY_SUFFIX);
        return velocityViewResolver;
    }

}
