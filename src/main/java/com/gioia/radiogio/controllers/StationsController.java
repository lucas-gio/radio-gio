package com.gioia.radiogio.controllers;

import com.gioia.radiogio.data.repositories.OldCountryRepository;
import com.gioia.radiogio.helpers.EncoderHelper;
import com.gioia.radiogio.services.PlayerService;
import com.gioia.radiogio.services.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping("/stations")
public class StationsController {
    @Autowired
    private StationsService stationsService;

    @Autowired
    private OldCountryRepository oldCountryRepository;

    @Autowired
    private PlayerService playerService;


    /**
    *    List 5 radio stations of an country.
     *    Example: GET http://localhost:8080/stations/test/AR
     *    returns Argentinian test stations.
     */
    @GetMapping("/test/{countryCode}")
    public String test(Model model, @PathVariable(value = "countryCode") String countryCode) {
        model.addAttribute("stationsList", stationsService.test(countryCode));
        return "stations/test";
    }

    @GetMapping("/station/{url}")
    public String station(Model model, @PathVariable(value = "url") String url) {
        String decodedUrl = EncoderHelper.decode(url);
        playerService.playRadio(decodedUrl);
        model.addAttribute("stationsList", List.of());
        return "stations/test";
    }
}
