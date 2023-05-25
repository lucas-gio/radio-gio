package com.gioia.radiogio.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.dizitart.no2.Nitrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

/**
 * This class is used to perform actions at the end of the server's execution.
 */
public class RadioGioServletContextListener implements ServletContextListener {
    @Autowired
    Nitrite nitrite;

    @Autowired
    AudioPlayerComponent audioPlayerComponent;
    private Logger logger = LoggerFactory.getLogger(RadioGioServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.atDebug().log("Turning off.");
        logger.atDebug().log("Performing tasks before turning off Radio Gio.");

        closeDb();
        closeAudioPlayerComponent();
    }

    private void closeDb(){
        if (nitrite == null){
            logger.atDebug().log("DB: nitrite instance null.");
            return;
        }

        logger.atDebug().log("DB: closing.");
        if(!nitrite.isClosed()){
            nitrite.close();
            logger.atDebug().log("DB: closed.");
        }
        else{
            logger.atDebug().log("DB: already closed.");
        }
    }

    private void closeAudioPlayerComponent(){
        logger.atDebug().log("AudioPlayerComponent: releasing.");
        audioPlayerComponent.release();
        logger.atDebug().log("AudioPlayerComponent: released.");
    }
}