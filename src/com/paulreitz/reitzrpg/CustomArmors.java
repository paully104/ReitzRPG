package com.paulreitz.reitzrpg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CustomArmors implements Listener
{
	static Plugin plugin = Reitzrpgmain.getPlugin();
	static List<String>list;
	static int size;
	
    public CustomArmors(Reitzrpgmain instance) {
        plugin = instance;   
    }
    
    public static File f;
    public static YamlConfiguration custom_armors;
	
    public static void Create_File()
    {
	f = Reitzrpgmain.custom_armors;
	custom_armors = YamlConfiguration.loadConfiguration(f);
	//custom armor start
	custom_armors.addDefault("CustomArmors", null); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet", null); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Topleft", 0); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Topmid", 280); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Topright", 280); //$NON-NLS-1$
	
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Midleft", 280); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Midmid", 280); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Midright", 280); //$NON-NLS-1$

	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Botleft", 280); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Botmid", 280); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Botright", 280); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Mindefense", 1); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Maxdefense", 2); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Material", 298);
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Enchantment", "ARROW_DAMAGE");
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.EnchantmentLevel", 1);
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Level", 10); //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Lore", null);	 //$NON-NLS-1$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Lore.Top", "\"TOP\""); //$NON-NLS-1$ //$NON-NLS-2$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Lore.Mid", "\"MID\""); //$NON-NLS-1$ //$NON-NLS-2$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Lore.Bot", "\"BOT\""); //$NON-NLS-1$ //$NON-NLS-2$
	custom_armors.addDefault("CustomArmors.Awesome_Helmet.Name", "Awesome Helmet"); //$NON-NLS-1$ //$NON-NLS-2$
	custom_armors.options().copyDefaults(true);
	try {
		custom_armors.save(f);
	} catch (IOException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

	public static  void onEnableLoadWeapons()
	{
	//Set<String> homes = plugin.getConfig().getConfigurationSection("CustomArmors").getKeys(false);//true outputs all subkeys
	//Set<String> homes = API.getConfiguration().getConfigurationSection("CustomArmors").getKeys(false);
	Set<String> homes = API.getArmorsConfiguration().getConfigurationSection("CustomArmors").getKeys(false);
	/*List<String>*/ list = new ArrayList<String>(homes);
	size = homes.size();
	
	for(int i = 0; i<size; i++)
	{
		String weapon = list.get(i);
		//int material = plugin.getConfig().getInt("CustomArmors."+weapon+".Material"); //268 woodensword
	    //String name = plugin.getConfig().getString("CustomArmors."+weapon+".Name");
		int material = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Material"); //268 woodensword
	    String name = API.getArmorsConfiguration().getString("CustomArmors."+weapon+".Name");
	    if (name == null)
	    {
	    	name = "ERROR";
	    	
	    }	
		//ItemStack material2 = new ItemStack(material);
		Material a = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Topleft"));
		Material b = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Topmid"));
		Material c = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Topright"));
		Material d = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Midleft"));
		Material e = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Midmid"));
		Material f = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Midright"));
		Material g = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Botleft"));
		Material h = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Botmid"));
		Material i2 = Material.getMaterial(plugin.getConfig().getInt("CustomArmors."+weapon+".Botright"));
		
		//String enchantment = plugin.getConfig().getString("CustomArmors."+weapon+".Enchantment");
		//int enchantlevel = plugin.getConfig().getInt("CustomArmors."+weapon+".EnchantmentLevel");
		String enchantment = API.getArmorsConfiguration().getString("CustomArmors."+weapon+".Enchantment");
		int enchantlevel = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".EnchantmentLevel");
	    ItemStack local = new ItemStack(material);
	    
	    if(enchantment != null && enchantlevel != 0)
	    {
	   local.addUnsafeEnchantment(Enchantment.getByName(enchantment), enchantlevel);
	    }

	    ItemMeta local_meta = local.getItemMeta();
	    
	    local_meta.setDisplayName(name);
	    String lore1 = API.getArmorsConfiguration().getString("CustomArmors."+weapon+".Lore.Top");	    
	    String lore2 = API.getArmorsConfiguration().getString("CustomArmors."+weapon+".Lore.Mid");
	    String lore3 = API.getArmorsConfiguration().getString("CustomArmors."+weapon+".Lore.Bot");
	    int min = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Mindefense");
		int max = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Maxdefense");
		int level = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Level");
		String levelamount = "Level " + level + " required"; 
	    String damageamount = min + " - " + max + " Defense Negation ";
	    if(lore1 != null && lore2 != null && lore3 != null && damageamount != null
	    		&& levelamount != null)
	    {
	    	String colorlore1 = ChatColor.translateAlternateColorCodes('&', lore1);
	    	String colorlore2 = ChatColor.translateAlternateColorCodes('&', lore2);
	    	String colorlore3 = ChatColor.translateAlternateColorCodes('&', lore3);
	    local_meta.setLore(Arrays.asList(colorlore1,colorlore2,colorlore3,damageamount,levelamount));
	    }
	    else if(lore1 == null && lore2 == null && lore3 == null)
	    {

	    	local_meta.setLore(Arrays.asList(damageamount,levelamount));
	    	
	    }	
	    else if(lore1 != null && lore2 == null && lore3 == null)
	    {
	    	String colorlore1 = ChatColor.translateAlternateColorCodes('&', lore1);
	    	local_meta.setLore(Arrays.asList(colorlore1,damageamount,levelamount));
	    	
	    }
	    else if(lore1 != null && lore2 != null && lore3 == null)
	    {
	    	String colorlore1 = ChatColor.translateAlternateColorCodes('&', lore1);
	    	String colorlore2 = ChatColor.translateAlternateColorCodes('&', lore2);
	    	local_meta.setLore(Arrays.asList(colorlore1,colorlore2,damageamount,levelamount));
	    	
	    }			    
	    local.setItemMeta(local_meta);
	 
	 
	    ShapedRecipe local_recipe = new ShapedRecipe(new ItemStack(local));
	    
	    local_recipe.shape(new String[] {"ABC", "DEF", "GHI"});
	    if( !a.equals(Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('A', a);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("A");
	    }	
	    if( !b.equals(Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('B', b);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("B");
	    }	
	    if( !c.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('C', c);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("C");	    	
	    }	
	    if( !d.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('D', d);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("D");	
	    }	
	    if( !e.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('E', e);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("E");	
	    }	
	    if( !f.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('F', f);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("F");	
	    }	
	    if( !g.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('G', g);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("G");	
	    }	
	    if( !h.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('H', h);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("H");	
	    }	
	    if( !i2.equals( Material.getMaterial(0)))
	    {	    
	    local_recipe.setIngredient('I', i2);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("I");	
	    }	
	    Bukkit.getServer().addRecipe(local_recipe);

		
		
	}	
	
	}
		
		
		
	
	

}
