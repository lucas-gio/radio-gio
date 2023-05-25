package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Configuration;

public interface ConfigurationRepository extends Indexable{
    void upsert(Configuration configuration);
    Configuration find(String key);
}