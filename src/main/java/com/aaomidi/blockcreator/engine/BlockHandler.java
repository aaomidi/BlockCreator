package com.aaomidi.blockcreator.engine;

import com.aaomidi.blockcreator.BlockCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockHandler {
    private final BlockCreator instance;

    public BlockHandler(BlockCreator instance) {
        this.instance = instance;
    }

    public ItemStack[] handle(ItemStack itemStack) {
        for (Material material : instance.getConfiguration().getBlocks()) {
            ItemStack[] result = new Block(material, itemStack).getBlocks();
            if (result == null)
                continue;


            return result;
        }
        return null;
    }
}
