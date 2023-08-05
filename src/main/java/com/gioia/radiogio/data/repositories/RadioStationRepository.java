package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.RadioStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadioStationRepository extends JpaRepository<RadioStation, String> {
}
