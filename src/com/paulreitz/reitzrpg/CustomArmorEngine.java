package com.paulreitz.reitzrpg;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class CustomArmorEngine {
	
	public int CalculateDefenseFromCustomArmor(Plugin plugin, Player player, int size,List list,EntityDamageByEntityEvent event)
	{
		int damage = 0;
	for(Player playercheck : Bukkit.getOnlinePlayers())
	{
		if(playercheck == player)
		{	
			for(int i = 0; i<size; i++)
			{
				String weapon = (String) list.get(i);
			 
				//String name = plugin.getConfig().getString("CustomWeapons."+weapon+".Name");
				String name = API.getArmorsConfiguration().getString("CustomArmors."+weapon+".Name");
				/*Player player = (Player) event.getDamager(); */
				if(player.getInventory().getHelmet() != null)
				{	
				if (player.getInventory().getHelmet().hasItemMeta() == false
		    		|| player.getInventory().getHelmet().getItemMeta().hasDisplayName() == false
		    		|| !(player.getInventory().getHelmet().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			damage = damage + 0;
		    		}
				else if (player.getInventory().getHelmet().hasItemMeta() == true
		    		&& ChatColor.stripColor(player.getInventory().getHelmet().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			//Min + (int)(Math.random() * ((Max - Min) + 1))
		    			PlayerData pd = new PlayerData(player.getName());
		    			//int min = plugin.getConfig().getInt("CustomArmors."+weapon+".Mindefense");
		    			//int max = plugin.getConfig().getInt("CustomArmors."+weapon+".Maxdefense");
		    			//int level = plugin.getConfig().getInt("CustomArmors."+weapon+".Level");
		    			int min = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Mindefense");
		    			int max = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Maxdefense");
		    			int level = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Level"); 
		    			Random random = new Random();
		    			damage = damage + random.nextInt(max - min) +min;
		    			
		    			
		    			

		    			
		    		}	
				}
				if(player.getInventory().getChestplate() != null)
				{
				if (player.getInventory().getChestplate().hasItemMeta() == false
		    		|| player.getInventory().getChestplate().getItemMeta().hasDisplayName() == false
		    		|| !(player.getInventory().getChestplate().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			damage = damage + 0;
		    		}
				else if (player.getInventory().getChestplate().hasItemMeta() == true
		    		&& ChatColor.stripColor(player.getInventory().getChestplate().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			//Min + (int)(Math.random() * ((Max - Min) + 1))
		    			PlayerData pd = new PlayerData(player.getName());
		    			//int min = plugin.getConfig().getInt("CustomArmors."+weapon+".Mindefense");
		    			//int max = plugin.getConfig().getInt("CustomArmors."+weapon+".Maxdefense");
		    			//int level = plugin.getConfig().getInt("CustomArmors."+weapon+".Level");
		    			int min = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Mindefense");
		    			int max = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Maxdefense");
		    			int level = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Level"); 
		    			Random random = new Random();
		    			damage = damage + random.nextInt(max - min) +min;
		    			
		    			
		    			

		    			
		    		}
				}
				if(player.getInventory().getLeggings() != null)
				{
				if (player.getInventory().getLeggings().hasItemMeta() == false
		    		|| player.getInventory().getLeggings().getItemMeta().hasDisplayName() == false
		    		|| !(player.getInventory().getLeggings().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			damage = damage + 0;
		    		}
				else if (player.getInventory().getLeggings().hasItemMeta() == true
		    		&& ChatColor.stripColor(player.getInventory().getLeggings().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			//Min + (int)(Math.random() * ((Max - Min) + 1))
		    			PlayerData pd = new PlayerData(player.getName());
		    			//int min = plugin.getConfig().getInt("CustomArmors."+weapon+".Mindefense");
		    			//int max = plugin.getConfig().getInt("CustomArmors."+weapon+".Maxdefense");
		    			//int level = plugin.getConfig().getInt("CustomArmors."+weapon+".Level");
		    			int min = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Mindefense");
		    			int max = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Maxdefense");
		    			int level = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Level"); 
		    			Random random = new Random();
		    			damage = damage + random.nextInt(max - min) +min;
		    			
		    			
		    			

		    			
		    		}
				}
				if(player.getInventory().getBoots() != null)
				{
				if (player.getInventory().getBoots().hasItemMeta() == false
		    		|| player.getInventory().getBoots().getItemMeta().hasDisplayName() == false
		    		|| !(player.getInventory().getBoots().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			damage = damage + 0;
		    		}
				else if (player.getInventory().getBoots().hasItemMeta() == true
		    		&& ChatColor.stripColor(player.getInventory().getBoots().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			//Min + (int)(Math.random() * ((Max - Min) + 1))
		    			PlayerData pd = new PlayerData(player.getName());
		    			//int min = plugin.getConfig().getInt("CustomArmors."+weapon+".Mindefense");
		    			//int max = plugin.getConfig().getInt("CustomArmors."+weapon+".Maxdefense");
		    			//int level = plugin.getConfig().getInt("CustomArmors."+weapon+".Level");
		    			int min = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Mindefense");
		    			int max = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Maxdefense");
		    			int level = API.getArmorsConfiguration().getInt("CustomArmors."+weapon+".Level"); 
		    			Random random = new Random();
		    			damage = damage + random.nextInt(max - min) +min;
		    			
		    			
		    			

		    			
		    		}
				}
			}
		}
	}
		return damage;   
		
	   
}

}
