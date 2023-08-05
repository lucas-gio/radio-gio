package com.gioia.radiogio.helpers;

import java.util.Base64;

public class EncoderHelper {
    public static String encode(String value){
        return Base64.getUrlEncoder().encodeToString(value.getBytes());
    }

    public static String decode(String value){
        return new String(Base64.getUrlDecoder().decode(value));
    }
}
