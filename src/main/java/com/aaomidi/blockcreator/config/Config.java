package com.aaomidi.blockcreator.config;

import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

@Getter
public class Config {
    private final List<Material> blocks;

    public Config(List<Material> materials) {
        this.blocks = materials;
    }
}
