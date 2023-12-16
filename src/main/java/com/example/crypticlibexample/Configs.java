package com.example.crypticlibexample;

import crypticlib.chat.LangConfigHandler;
import crypticlib.chat.entry.StringLangConfigEntry;

@LangConfigHandler(langFileFolder = "lang")
public class Configs {

    public static final StringLangConfigEntry test = new StringLangConfigEntry("test", "config test");

}
