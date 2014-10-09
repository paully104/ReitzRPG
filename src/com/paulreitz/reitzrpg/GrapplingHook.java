package com.paulreitz.reitzrpg;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class GrapplingHook implements Listener
{
	
	@EventHandler(priority = EventPriority.LOWEST)
	 
    public void onPlayerFishingEvent(PlayerFishEvent event)
	{
        Player p = event.getPlayer();
    Material item = p.getItemInHand().getType();
    if(item == Material.FISHING_ROD && p.getItemInHand().getItemMeta().getDisplayName() == null)
    	{
    	}
    else if(item == Material.FISHING_ROD && p.getItemInHand().getItemMeta().getDisplayName().equals("Grappling Hook"))
    	{
    	
    		java.util.List<Entity> nearby = p.getNearbyEntities(25,25,25); // searches in a 100*100*100 radius around the player for other entities
    		Entity hook = null; // holds the future hook
    		for (Entity e : nearby)
    		{ // loop through entities
           
    			if (e.getType() == EntityType.FISHING_HOOK)
    			{ // it is a hook!
    				hook = e;
    				break;
    			}
        
    			if (hook != null)
    			{	
            	Location hookLocation = hook.getLocation(); // the location of the hook
           		p.getLocation();
           		p.teleport(hookLocation);
    			}
            else
        	{
            
        	}
    		}
        
    	}
	}
}


    	
    	
    	
    	
    	
       
   


