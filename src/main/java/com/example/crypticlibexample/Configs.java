package com.example.crypticlibexample;

import crypticlib.config.ConfigContainer;
import crypticlib.config.entry.StringConfigEntry;

@ConfigContainer(path = "settings.yml")
public class Configs {

    public static final StringConfigEntry test = new StringConfigEntry("test", "config test");

}
