package com.aaomidi.blockcreator.engine;

import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

@ToString(exclude = "recipe")
public class Block {
    private final Material result;
    private final ItemStack request;
    private ShapedRecipe recipe;

    public Block(Material result, ItemStack request) {
        this.result = result;
        this.request = request;
    }


    private ShapedRecipe getRecipe() {
        List<Recipe> recipes = Bukkit.getRecipesFor(new ItemStack(result));

        for (Recipe recipe : recipes) {
            if (isIntendedRecipe(recipe)) {
                this.recipe = (ShapedRecipe) recipe;
                return this.recipe;
            }
        }
        return null;
    }

    private boolean isIntendedRecipe(Recipe recipe) {
        int itemData = request.getDurability();
        Material itemMaterial = request.getType();

        if (!(recipe instanceof ShapedRecipe)) return false;

        ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
        int length = shapedRecipe.getIngredientMap().values().size();

        if (length != 4 && length != 9) return false;

        System.out.println(length);
        System.out.println(recipe.getResult().getType());


        for (ItemStack is : shapedRecipe.getIngredientMap().values()) {
            if (is == null) return false;

            if (itemMaterial != is.getType()) {
                return false;
            }

            if (itemData > 0 && itemData != is.getDurability()) {
                return false;
            }
        }
        return true;
    }

    public ItemStack[] getBlocks() {
        ShapedRecipe recipe = getRecipe();
        if (recipe == null) {
            return null;
        }

        if (request.getAmount() >= getMaterialsRequired()) {
            ItemStack result = recipe.getResult();
            result.setAmount(request.getAmount() / getMaterialsRequired());

            ItemStack remove = request.clone();
            remove.setAmount(getMaterialsRequired() * result.getAmount());
            return new ItemStack[]{result, remove};
        }
        return null;
    }

    private int getMaterialsRequired() {
        return getRecipe().getIngredientMap().values().size();
    }

}
