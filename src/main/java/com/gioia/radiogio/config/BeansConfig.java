package com.gioia.radiogio.config;

import org.dizitart.no2.Nitrite;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.gioia.radiogio")
public class BeansConfig {
    private static final String DB_PATH = "/home/bravo/file.db";

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Nitrite nitrite(){
        return  Nitrite
                    .builder()
                    .filePath(DB_PATH)
                    .openOrCreate();
    }

    public void closeNitrite(){

    }
}
