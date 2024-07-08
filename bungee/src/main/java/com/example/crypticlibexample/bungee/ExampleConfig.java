package com.example.crypticlibexample.bungee;

import crypticlib.config.ConfigHandler;
import crypticlib.config.node.impl.bungee.StringConfig;
import crypticlib.config.node.impl.bungee.StringListConfig;

import java.util.List;

@ConfigHandler(path = "example.yml")
public class ExampleConfig {

    public static final StringConfig test = new StringConfig("test", "test");
    public static final StringListConfig test2 = new StringListConfig("test2", List.of("1", "2", "3"));

}
