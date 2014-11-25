package com.paulreitz.reitzrpg;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class CustomWeaponEngine {
	
	public int CalculateDamageFromCustomWeapon(Plugin plugin, Player player, int size,List list,EntityDamageByEntityEvent event)
	{
		int damage =0;
		for(int i = 0; i<size; i++)
		{
			String weapon = (String) list.get(i);
			 
		    //String name = plugin.getConfig().getString("CustomWeapons."+weapon+".Name");
			String name = API.getConfiguration().getString("CustomWeapons."+weapon+".Name");
		    /*Player player = (Player) event.getDamager(); */
		    if (player.getItemInHand().hasItemMeta() == false
		    		|| player.getItemInHand().getItemMeta().hasDisplayName() == false
		    		|| !(player.getItemInHand().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			damage = 0;
		    		}
		    else if (player.getItemInHand().hasItemMeta() == true
		    		&& ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).equals(name))
		    		{
		    			//Min + (int)(Math.random() * ((Max - Min) + 1))
		    			PlayerData pd = new PlayerData(player.getName());
		    			int min = plugin.getConfig().getInt("CustomWeapons."+weapon+".Mindamage");
		    			int max = plugin.getConfig().getInt("CustomWeapons."+weapon+".Maxdamage");
		    			int level = plugin.getConfig().getInt("CustomWeapons."+weapon+".Level");
		    			Random random = new Random();
		    			damage = random.nextInt(max - min) +min;
		    			return damage;
		    			
		    			

		    			
		    		}	
		}
		return damage;   
		
	}

}
