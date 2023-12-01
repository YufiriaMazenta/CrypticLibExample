package com.example.crypticlibexample;

import crypticlib.config.YamlConfigHandler;
import crypticlib.config.entry.StringConfigEntry;

@YamlConfigHandler(path = "settings.yml")
public class Configs {

    public static final StringConfigEntry test = new StringConfigEntry("test", "config test");

}
