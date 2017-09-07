package com;

import javafx.application.Application;

import java.util.Locale;

/**
 * Created by pavel on 07.09.17.
 */
public class Config {

    private static Config sInstance;

    public Locale locale = new Locale("ru");
    public Application application;

    private Config(Application application) {
        this.application = application;
    }

    public static Config getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("first you need to call the init method");
        }
        return sInstance;
    }

    public static Config init(Application application) {
        return sInstance == null ? sInstance = new Config(application) : sInstance;
    }
}
