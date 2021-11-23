package org.dellmdq.blogdemo.config;

import com.monitorjbl.json.JsonViewSupportFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonViewConfig {
    @Bean
    public JsonViewSupportFactoryBean views() {
        return new JsonViewSupportFactoryBean();
    }

}
