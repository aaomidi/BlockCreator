package com.aaomidi.blockcreator;

import com.aaomidi.blockcreator.config.Config;
import com.aaomidi.blockcreator.engine.BlockHandler;
import com.aaomidi.blockcreator.engine.CommandHandler;
import com.google.gson.Gson;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class BlockCreator extends JavaPlugin {
    private Gson gson = new Gson();
    @Getter
    private Config configuration;
    @Getter
    private BlockHandler handler;


    @Override
    public void onLoad() {
        File configJson = new File(getDataFolder(), "config.json");

        if (!configJson.exists()) {
            saveResource("config.json", false);
        }

        try {
            configuration = gson.fromJson(new FileReader(configJson), Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        getCommand("block").setExecutor(new CommandHandler(this));
        handler=new BlockHandler(this);
    }
}
