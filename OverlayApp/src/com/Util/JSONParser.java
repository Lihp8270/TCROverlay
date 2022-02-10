package com.Util;

import com.Model.Config;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class JSONParser {
    private Gson gson;

    public JSONParser() {
        gson = new Gson();
    }

    /**
     * Read Config JSON File
     * @param filePath
     * @return config object
     */
    public Config readConfig(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Config config = gson.fromJson(reader, Config.class);

            return config;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
