package com.example.crypticlibexample;

import crypticlib.config.yaml.YamlConfigHandler;
import crypticlib.config.yaml.entry.StringConfigEntry;

@YamlConfigHandler(path = "settings.yml")
public class Configs {

    public static final StringConfigEntry test = new StringConfigEntry("test", "config test");

}
