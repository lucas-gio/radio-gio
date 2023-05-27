package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Configuration;

public interface OldConfigurationRepository {
    void upsert(Configuration configuration);
    Configuration find(String key);
}