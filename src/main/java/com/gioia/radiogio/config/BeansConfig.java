package com.gioia.radiogio.config;

import jakarta.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.component.MediaPlayerComponent;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.gioia.radiogio")
public class BeansConfig {
    @Autowired
    Environment environment;

    // To perform actions at the end of the server's execution.
    @Bean
    ServletListenerRegistrationBean<ServletContextListener> servletListener() {
        ServletListenerRegistrationBean<ServletContextListener> srb = new ServletListenerRegistrationBean<>();
        srb.setListener(new RadioGioServletContextListener());
        return srb;
    }

    @Bean
    AudioPlayerComponent audioPlayerComponent(){
        return new AudioPlayerComponent();
    }

    // getRunner executes before init.
    @Bean
    public CommandLineRunner getRunner(ApplicationContext ctx){
        return (args) -> {
            ctx.getBean(Initializer.class).init();
        };
    }
}
