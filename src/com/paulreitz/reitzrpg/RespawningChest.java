package com.paulreitz.reitzrpg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class RespawningChest implements Listener {

	static Reitzrpgmain plugin;
    public RespawningChest(Reitzrpgmain instance) {
        plugin = instance;   
    }

	public static void makethechestrefill(Chest chest,String name,Player player)
	{//set the contents of a chest
		int time = plugin.getConfig().getInt("TreasureChests."+name+".Time");
		//int respawntime = plugin.getConfig().getInt("TreasureChests."+name+".RespawnTime");
		String contents = plugin.getConfig().getString("TreasureChests."+name+".Contents");
		chest.getLocation().getBlock().setMetadata("Contents", new FixedMetadataValue(plugin,contents));
		chest.getLocation().getBlock().setMetadata("Time", new FixedMetadataValue(plugin,time));
		chest.getLocation().getBlock().setMetadata("RespawnTime", new FixedMetadataValue(plugin,0));
		chest.getLocation().getBlock().setMetadata("Name", new FixedMetadataValue(plugin,name));
		Inventory contents2 = InventoryStringDeSerializer.StringToInventory(contents);
		System.out.println(contents);
		System.out.println("TEST BLOCK");
		System.out.println(contents2);
		chest.getInventory().setContents(contents2.getContents());

		
	}	
	
	public static int getTime(Chest chest)
	{
	    List<MetadataValue> values =chest.getMetadata("Time");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asInt();
	        }
	    }
	    //System.out.println("something went wrong?");
	    return 0;    
	}	
	public static long getRespawnTime(Chest chest)
	{
	    List<MetadataValue> values = chest.getMetadata("RespawnTime");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asLong();
	        }
	    }
	    //System.out.println("something went wrong?");
	    return 0;    
	}	
	public static String getContents(Chest chest)
	{
	    List<MetadataValue> values = chest.getMetadata("Contents");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asString();
	        }
	    }
	    //System.out.println("something went wrong?");
	    return "";    
	}	
	
	
	@EventHandler
	public void treasurechestrightclick(PlayerInteractEvent event)
	{
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        
        if (block != null && block.getType() == Material.CHEST)
        {
        	        	
        	Chest chest = (Chest) block.getState();
        	
        	if(chest.hasMetadata("Name") && chest.hasMetadata("Time") && chest.hasMetadata("RespawnTime"))
        			{  //sets the contents to the treasure chest 
        				int timer = RespawningChest.getTime(chest);
        				long respawntime = RespawningChest.getRespawnTime(chest);
        				String contents = RespawningChest.getContents(chest);
        				if(player.getWorld().getTime() > respawntime)
        				{
            				Inventory contents2 = InventoryStringDeSerializer.StringToInventory(contents);
            				chest.getInventory().setContents(contents2.getContents());
            				chest.getLocation().getBlock().setMetadata("RespawnTime",
            						new FixedMetadataValue(plugin,player.getWorld().getTime()+timer));
        					
        					
        				}	
        				else
        				{
        					event.setCancelled(true);
        					player.sendMessage("Can't loot this chest yet!");
        				}	

        		
        		
        			}
        	
        }
		
		
	}	
	
	
}
