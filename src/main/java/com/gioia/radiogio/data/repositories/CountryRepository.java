package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Country;

import java.util.List;

interface CountryRepository{
    void removeAll();
    void saveAll(List<Country> countries);
    void setFavourite(String countryName, String radioName);

    List<Country> findAllCountries();
}