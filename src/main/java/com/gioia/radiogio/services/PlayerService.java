package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.RadioStation;

interface PlayerService {
    void playRadio(String url);
    void stopRadio();
    void toggleFavourite(Boolean isNowInFavourite, RadioStation radioStation);
    void changeVolume(Integer value);
}