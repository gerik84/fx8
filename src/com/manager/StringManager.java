package com.manager;

import com.Config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by pavel on 07.09.17.
 */
public class StringManager {


    public static String getString(String key) {
        return getString(key, Config.getInstance().locale);
    }

    public static String getString(String key, Locale locale) {
        return ResourceBundle.getBundle("com.resource.strings", locale).getString(key);
    }
}
