package com.example.crypticlibexample;

import com.google.common.collect.Lists;
import crypticlib.config.ConfigHandler;
import crypticlib.config.entry.StringListConfigEntry;

@ConfigHandler(path = "config.yml")
public class Configs {

    public static final StringListConfigEntry LAYOUT = new StringListConfigEntry("layout", Lists.newArrayList("#### ####", "###   ###", "##     ##"));

}
