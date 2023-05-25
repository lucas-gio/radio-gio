package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Configuration;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConfigurationRepositoryImpl implements ConfigurationRepository{
    private Nitrite database;
    private Logger logger = LoggerFactory.getLogger(ConfigurationRepositoryImpl.class);

    private ObjectRepository<Configuration> getRepository(){
        return database.getRepository(Configuration.class);
    }

    @Override
    public void upsert(Configuration configuration) {
        getRepository().update(configuration, true);
        logger.atDebug().log("Actualizada la configuración ${configuration.key}, valor: ${configuration.value}");
    }

    @Override
    public Configuration find(String key) {
        logger.atDebug().log("Buscando configuración $key");
        Configuration config = getRepository()
            .find(
                ObjectFilters.eq("key", key)
            )
            .firstOrDefault();
        logger.atDebug().log("Leída la configuración $key, valor: " + (config.getValue() != null ? config.getValue() : "n.d"));
        return config;
    }
}