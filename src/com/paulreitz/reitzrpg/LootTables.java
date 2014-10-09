package com.paulreitz.reitzrpg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class LootTables implements Listener{
	
	
	public static Reitzrpgmain plugin;
    public LootTables(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    } 
    public static File f;
    public static YamlConfiguration loot_table;
	
    public static void LootTableDefault()
    {
	f = Reitzrpgmain.f;
	loot_table = YamlConfiguration.loadConfiguration(f);
	loot_table.addDefault("Level", null);
	loot_table.addDefault("Level.1", null);
	loot_table.addDefault("Level.1.CREEPER", null);
	loot_table.addDefault("Level.1.CREEPER.Items", "1");
	loot_table.addDefault("Level.1.CREEPER.Chances", "50");
	loot_table.addDefault("Level.1", null);
	loot_table.addDefault("Level.1.ZOMBIE", null);
	loot_table.addDefault("Level.1.ZOMBIE.Items", "1");
	loot_table.addDefault("Level.1.ZOMBIE.Chances", "1");
	loot_table.addDefault("Level.2", null);
	loot_table.addDefault("Level.2.CREEPER", null);
	loot_table.addDefault("Level.2.CREEPER.Items", "1");
	loot_table.addDefault("Level.2.CREEPER.Chances", "50");
	loot_table.options().copyDefaults(true);
	try {
		loot_table.save(f);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
    }
    @EventHandler
    public static void onMobDeath(EntityDeathEvent event)
    {
    	
    		int MonsterLevel = (int) DistanceLevel.getMonsterLevel(event.getEntity());
    		String Entity = event.getEntityType().toString();
    		
    	Set<String> homes = loot_table.getConfigurationSection("Level").getKeys(false);//true outputs all subkeys
    	List<String> list = new ArrayList<String>(homes);
    	int size = homes.size();
    	
    	
    		//int item = loot_table.getInt("Level."+MonsterLevel+"."+Entity+"."+"Items"); //Level.1.CREEPER.268
    	try{
    		String items= loot_table.getString("Level."+MonsterLevel+"."+Entity+"."+"Items");
    		String chances= loot_table.getString("Level."+MonsterLevel+"."+Entity+"."+"Chances");
    		String[] splits = items.split("\\s+");
    		String[] chancessplit = chances.split("\\s+");
    		
    		for(String item: splits)
    		{
    			for(String chance: chancessplit)
    			{
    				int item2 = Integer.parseInt(item);
    				if(item2 != 0)
    				{
    					int chance2 = Integer.parseInt(chance);
    					double d = Math.random();
    					if(d*100 <= (0+chance2))
    					{
    					event.getDrops().add(new ItemStack(item2, 1));
    					}
    					return;
    				}
    			}
    			
    		}
    		
    	}
    	catch(NullPointerException e)
    	{
    		
    	}
    

    	
    	
    }

}
