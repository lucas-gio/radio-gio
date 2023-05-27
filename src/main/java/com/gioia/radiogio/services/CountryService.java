package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.Country;
import com.gioia.radiogio.data.domains.RadioStation;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    void saveAll(List<Country> countries);
}
