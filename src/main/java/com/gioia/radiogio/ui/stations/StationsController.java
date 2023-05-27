package com.gioia.radiogio.ui.stations;

import com.gioia.radiogio.data.repositories.OldCountryRepository;
import com.gioia.radiogio.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping("/stations")
public class StationsController {
    @Autowired
    private StationsService stationsService;

    @Autowired
    private OldCountryRepository oldCountryRepository;

    @GetMapping("/test/{countryCode}")
    /**
    *    List 5 radio stations of an country.
     *    Example: GET http://localhost:8080/stations/test/AR
     *    returns Argentinian test stations.
     */
    public String test(Model model, @PathVariable(value = "countryCode") String countryCode) {
        model.addAttribute("stationsList", stationsService.test(countryCode));
        return "stations/test";
    }
}
