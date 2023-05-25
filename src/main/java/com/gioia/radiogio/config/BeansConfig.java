package com.gioia.radiogio.config;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletContextListener;
import org.dizitart.no2.Nitrite;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.gioia.radiogio")
public class BeansConfig {
    private static final String DB_PATH = "/home/bravo/file.db";

    // Database
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Nitrite nitrite(){
        return  Nitrite
                    .builder()
                    .registerModule(new Jdk8Module())        // register jdk8 module
                    .registerModule(new JavaTimeModule())    // register java.time module
                    //
                // .nitriteMapper(new JacksonMapper())
                    .filePath(DB_PATH)
                    .openOrCreate();
    }

    // To perform actions at the end of the server's execution.
    @Bean
    ServletListenerRegistrationBean<ServletContextListener> servletListener() {
        ServletListenerRegistrationBean<ServletContextListener> srb = new ServletListenerRegistrationBean<>();
        srb.setListener(new RadioGioServletContextListener());
        return srb;
    }
}
