package junia.rpg.web.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import junia.rpg.web.controller.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "junia.rpg.web.controller")
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class WSConfig {


    @Bean
    public Server jaxrsServer(JacksonJsonProvider jsonProvider,Bus cxfBus,List<RestController> controllers){
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        List<Object> serviceBeans = new ArrayList<>(controllers);
        sf.setServiceBeans(serviceBeans);
        sf.setProviders(List.of(jsonProvider));
        sf.setAddress("/");
        sf.setBus(cxfBus);
        return sf.create();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
}
