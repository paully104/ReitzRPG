package com.paulreitz.reitzrpg;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fish;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class CustomRecipes{
	Reitzrpgmain plugin;
    public CustomRecipes(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    }
    public static void CustomRecipes()
    {
	
 

 
    	
    	
    	
    	
	//Dirt Sword
    ItemStack dirt_sword = new ItemStack(Material.WOOD_SWORD);
    
    
    dirt_sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    ItemMeta dirt_sword_meta = dirt_sword.getItemMeta();
    dirt_sword_meta.setDisplayName(ChatColor.YELLOW + "Dirt Sword");
    dirt_sword_meta.setLore(Arrays.asList(ChatColor.BLUE + "RARE"));
    dirt_sword.setItemMeta(dirt_sword_meta); 
    ShapedRecipe dirt_sword_recipe = new ShapedRecipe(new ItemStack(dirt_sword));
    dirt_sword_recipe.shape(new String[] {" C ", " C ", " S "});
    dirt_sword_recipe.setIngredient('S', Material.STICK);
    dirt_sword_recipe.setIngredient('C', Material.DIRT);
    Bukkit.getServer().addRecipe(dirt_sword_recipe);
    //end of dirt Sword
  
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    //TORNADO WAND
    ItemStack tornado_wand_item = new ItemStack(Material.STICK);
    ItemMeta tornado_wand_meta = tornado_wand_item.getItemMeta();
    tornado_wand_meta.setDisplayName("Tornado Wand");
    tornado_wand_item.setItemMeta(tornado_wand_meta);
 
 
    ShapedRecipe tornado_wand_recipe = new ShapedRecipe(new ItemStack(tornado_wand_item));
    tornado_wand_recipe.shape(new String[] {" C ", " S ", " S "});
    // goes top mid bot
    //use spaces for blank spots
    //recipe.setIngredient('I', Material.AIR);
    tornado_wand_recipe.setIngredient('S', Material.STICK);
    tornado_wand_recipe.setIngredient('C', Material.DIRT);
    Bukkit.getServer().addRecipe(tornado_wand_recipe);
    
    //BATTLE AXE OF ARCANE MIGHT
    ItemStack battle_axe_of_arcane_might_item = new ItemStack(Material.DIAMOND_AXE);
    ItemMeta battle_axe_of_arcane_might_item_meta = battle_axe_of_arcane_might_item.getItemMeta();
    battle_axe_of_arcane_might_item_meta.setDisplayName("BATTLE AXE OF ARCANE MIGHT");
    battle_axe_of_arcane_might_item_meta.setLore(Arrays.asList(ChatColor.RED + "For Cassandra ", ChatColor.GREEN + "6 months",
    		ChatColor.BLUE +" together"));
    battle_axe_of_arcane_might_item.setItemMeta(battle_axe_of_arcane_might_item_meta);
 
 
    ShapedRecipe battle_axe_of_arcane_might_item_recipe = new ShapedRecipe(new ItemStack(battle_axe_of_arcane_might_item));
    battle_axe_of_arcane_might_item_recipe.shape(new String[] {" CC", " SC", " S "});
    battle_axe_of_arcane_might_item_recipe.setIngredient('S', Material.STICK);
    battle_axe_of_arcane_might_item_recipe.setIngredient('C', Material.OBSIDIAN);
    Bukkit.getServer().addRecipe(battle_axe_of_arcane_might_item_recipe);
    
    //GRAPPLING HOOK
    ItemStack grappling_hook_item = new ItemStack(Material.FISHING_ROD);
    ItemMeta grappling_hook_item_meta = grappling_hook_item.getItemMeta();
    grappling_hook_item_meta.setDisplayName("Grappling Hook");
    grappling_hook_item.setItemMeta(grappling_hook_item_meta);
 
 
    ShapedRecipe grappling_hook_item_recipe = new ShapedRecipe(new ItemStack(grappling_hook_item));
    grappling_hook_item_recipe.shape(new String[] {"  S", " SC", "SCC"});
    // goes top mid bot
    //use spaces for blank spots
    //recipe.setIngredient('I', Material.AIR);
    grappling_hook_item_recipe.setIngredient('S', Material.STICK);
    grappling_hook_item_recipe.setIngredient('C', Material.STRING);
    Bukkit.getServer().addRecipe(grappling_hook_item_recipe);
    
    //Cheap Arrows
    ItemStack cheap_arrows_item = new ItemStack(Material.ARROW);
    cheap_arrows_item.setAmount(8); //set amount of cheap arrows to make?
    ItemMeta cheap_arrows_item_meta = cheap_arrows_item.getItemMeta();
    cheap_arrows_item_meta.setDisplayName("Cheap Arrow");
    cheap_arrows_item.setItemMeta(cheap_arrows_item_meta);
 
 
    ShapedRecipe cheap_arrows_recipe = new ShapedRecipe(new ItemStack(cheap_arrows_item));
    cheap_arrows_recipe.shape(new String[] {"   ", " S ", " C "});
    // goes top mid bot
    //use spaces for blank spots
    //recipe.setIngredient('I', Material.AIR);
    cheap_arrows_recipe.setIngredient('S', Material.STICK);
    cheap_arrows_recipe.setIngredient('C', Material.FEATHER);
    Bukkit.getServer().addRecipe(cheap_arrows_recipe);
    
    //Cooked Pufferfish


}


}
