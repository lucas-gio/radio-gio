package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
