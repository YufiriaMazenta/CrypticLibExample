package com.example.crypticlibexample;

import crypticlib.chat.LangConfigEntry;
import crypticlib.chat.LangConfigHandler;

import java.util.HashMap;
import java.util.Map;

@LangConfigHandler(langFileFolder = "lang")
public class Configs {

    public static final LangConfigEntry test = new LangConfigEntry("test", "config test", () -> {
        Map<String, String> langMap = new HashMap<>();
        langMap.put("zh_cn", "你好");
        langMap.put("en_us", "Hello");
        langMap.put("zh_tw", "你好");
        return langMap;
    });

}
