package com.example.crypticlibexample;

import crypticlib.chat.LangConfigEntry;
import crypticlib.chat.LangConfigHandler;

@LangConfigHandler(langFileFolder = "lang")
public class Configs {

    public static final LangConfigEntry test = new LangConfigEntry("test", "config test");

}
