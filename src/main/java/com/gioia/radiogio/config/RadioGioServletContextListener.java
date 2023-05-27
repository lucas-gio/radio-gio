package com.gioia.radiogio.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

/**
 * This class is used to perform actions at the end of the server's execution.
 */
public class RadioGioServletContextListener implements ServletContextListener {
    @Autowired
    private AudioPlayerComponent audioPlayerComponent;

    private Logger logger = LoggerFactory.getLogger(RadioGioServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.atDebug().log("Turning off.");
        logger.atDebug().log("Performing tasks before turning off Radio Gio.");

        closeAudioPlayerComponent();
    }

    private void closeAudioPlayerComponent(){
        logger.atDebug().log("AudioPlayerComponent: releasing.");
        if(audioPlayerComponent == null){
            logger.atDebug().log("AudioPlayerComponent: was null.");
        }
        else {
            audioPlayerComponent.release();
            logger.atDebug().log("AudioPlayerComponent: released.");
        }
    }
}