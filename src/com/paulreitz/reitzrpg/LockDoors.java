package com.paulreitz.reitzrpg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class LockDoors implements Listener 
{

	public static Reitzrpgmain plugin;
    public LockDoors(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    } 

    public static void onlinepersistance()
    {
    	System.out.println("Persistance is happening");
    	Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "ReitzRPG: Loading existing locks [ONLINE]");
    	Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "--------------------------------");
    	Player[] list = Bukkit.getOnlinePlayers();
    	OfflinePlayer[] list2 = Bukkit.getOfflinePlayers();
    	
    	for(Player p : list){
    		
    		//PlayerData pd = new PlayerData(p.getName());
    		PlayerData pd = PlayerJoinListener.users.get(p.getName());
    		
    		int count = pd.getData().getInt("Count")+1;
    		System.out.println(count + "this is the total count");
    		int i = 0;
    		while(i< count)
    		{
    			
    			if(i == 0)
    			{
    				
    	    		int x = pd.getData().getInt("Items-Locked.Location.x");
    	    		int y = pd.getData().getInt("Items-Locked.Location.y");
    	    		int z = pd.getData().getInt("Items-Locked.Location.z");
    	    		int locknumber = pd.getData().getInt("Items-Locked.Location.count");
    	    		String world = pd.getData().getString("Items-Locked.Location.world");
    	    		String key = pd.getData().getString("Items-Locked.Location.key");
            		if(world == null)
            		{
            			i = i+2;
            			continue;
            			
            		}
            		else
            		{
    	    		Location locklocation = new Location(Bukkit.getWorld(world),x,y,z);
    	    		if(locklocation.getBlock().getType().name().equals(Material.WOODEN_DOOR.toString())
    	    				|| locklocation.getBlock().getType().name().equals(Material.IRON_DOOR.toString())
    	    						|| locklocation.getBlock().getType().name().equals(Material.CHEST.toString())
    	    						|| locklocation.getBlock().getType().name().equals(Material.TRAPPED_CHEST))
    	    		{
    	    			
    	    		Location abovelocation = new Location(Bukkit.getWorld(world),x,y+1,z);
    	    		Location belowlocation = new Location(Bukkit.getWorld(world),x,y-1,z);
    	    		abovelocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    	    		abovelocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    	    		abovelocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
    	    		belowlocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    	    		belowlocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    	    		belowlocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
    	    		
    	    		locklocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    	    		locklocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    	    		locklocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
    	    		i = i+2;
    	    		continue;
    	    		
    	    		}
            		}
    	    		  				
    				
    			}	
    			else
    			{
    				
    				
    				int x = pd.getData().getInt("Items-Locked"+i+".Location.x");
    				int y = pd.getData().getInt("Items-Locked"+i+".Location.y");
    				int z = pd.getData().getInt("Items-Locked"+i+".Location.z");
    				String world = pd.getData().getString("Items-Locked"+i+".Location.world");
    				String key = pd.getData().getString("Items-Locked"+i+".Location.key");
    				if(world == null)
    				{
    					i = i+1;
    					continue;
    					
    				}
    				else
    				{
    				Location locklocation = new Location(Bukkit.getWorld(world),x,y,z);
    				if(locklocation.getBlock().getType().name().equals(Material.WOODEN_DOOR.toString())
    						|| locklocation.getBlock().getType().name().equals(Material.IRON_DOOR.toString())
    						|| locklocation.getBlock().getType().name().equals(Material.CHEST.toString())
    						|| locklocation.getBlock().getType().name().equals(Material.TRAPPED_CHEST))
    				{
    					Location abovelocation = new Location(Bukkit.getWorld(world),x,y+1,z);
    					Location belowlocation = new Location(Bukkit.getWorld(world),x,y-1,z);
    					abovelocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    					abovelocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    					abovelocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,i));
    					belowlocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    					belowlocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    					belowlocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,i));
        	    		locklocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
        	    		locklocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
        	    		locklocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
        	    		i = i+2;
        	    		continue;
        	    		
    				}
    				else
    				{
    					i=i+1;
    					continue;
    				}
    				
    				}
    			}
    		
    		}
    		
    		
    	}
    	
    }
    
    public static void offlinepersistance(){
    	Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "ReitzRPG: Loading existing locks [OFFLINE]");
    	Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "--------------------------------");
    	OfflinePlayer[] list2 = Bukkit.getOfflinePlayers();
    	for(OfflinePlayer p : list2){
    		
    		UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(p.getName()));
    		PlayerData pd = new PlayerData(p.getName());
    		
    		int count = pd.getData().getInt("Count")+1;
    		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + ""+p.getName() + " has " + count + "chests!");
    		
    		
    		
    		int i = 0;
    		while(i< count)
    		{
    			
    			if(i == 0)
    			{
    				
    	    		int x = pd.getData().getInt("Items-Locked.Location.x");
    	    		int y = pd.getData().getInt("Items-Locked.Location.y");
    	    		int z = pd.getData().getInt("Items-Locked.Location.z");
    	    		int locknumber = pd.getData().getInt("Items-Locked.Location.count");
    	    		String world = pd.getData().getString("Items-Locked.Location.world");
    	    		String key = pd.getData().getString("Items-Locked.Location.key");
            		if(world == null)
            		{
            			i = i+2;
            			continue;
            			
            		}
            		else
            		{
    	    		Location locklocation = new Location(Bukkit.getWorld(world),x,y,z);
    	    		if(locklocation.getBlock().getType().name().equals(Material.WOODEN_DOOR.toString())
    	    				|| locklocation.getBlock().getType().name().equals(Material.IRON_DOOR.toString())
    	    				|| locklocation.getBlock().getType().name().equals(Material.CHEST.toString())
    	    				|| locklocation.getBlock().getType().name().equals(Material.TRAPPED_CHEST))
    	    		{
    	    			
    	    		Location abovelocation = new Location(Bukkit.getWorld(world),x,y+1,z);
    	    		Location belowlocation = new Location(Bukkit.getWorld(world),x,y-1,z);
    	    		abovelocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    	    		abovelocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    	    		abovelocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
    	    		belowlocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    	    		belowlocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    	    		belowlocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
    	    		
    	    		locklocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    	    		locklocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    	    		locklocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
    	    		i = i+2;
    	    		continue;
    	    		
    	    		}
            		}
    	    		  				
    				
    			}	
    			else
    			{
    				
    				
    				int x = pd.getData().getInt("Items-Locked"+i+".Location.x");
    				int y = pd.getData().getInt("Items-Locked"+i+".Location.y");
    				int z = pd.getData().getInt("Items-Locked"+i+".Location.z");
    				String world = pd.getData().getString("Items-Locked"+i+".Location.world");
    				String key = pd.getData().getString("Items-Locked"+i+".Location.key");
    				if(world == null)
    				{
    					i = i+1;
    					continue;
    					
    				}
    				else
    				{
    				Location locklocation = new Location(Bukkit.getWorld(world),x,y,z);
    				if(locklocation.getBlock().getType().name().equals(Material.WOODEN_DOOR.toString())
    						|| locklocation.getBlock().getType().name().equals(Material.IRON_DOOR.toString())
    						|| locklocation.getBlock().getType().name().equals(Material.CHEST.toString()))
    				{
    					Location abovelocation = new Location(Bukkit.getWorld(world),x,y+1,z);
    					Location belowlocation = new Location(Bukkit.getWorld(world),x,y-1,z);
    					abovelocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    					abovelocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    					abovelocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,i));
    					belowlocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
    					belowlocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
    					belowlocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,i));
        	    		locklocation.getBlock().setMetadata("lock", new FixedMetadataValue(plugin,key));
        	    		locklocation.getBlock().setMetadata("wholocked", new FixedMetadataValue(plugin,p.getName()));
        	    		locklocation.getBlock().setMetadata("Count", new FixedMetadataValue(plugin,1));
        	    		i = i+1;
        	    		continue;
        	    		
    				}
    				else
    				{
    					i=i+1;
    					continue;
    				}
    				
    				}
    			}
    		
    		}
    		
    		}
    		
    		
    		
    		
    	}

    	
    	
    
	public static String getMetaData(Block door)
	{
		
	    List<MetadataValue> values = door.getMetadata("lock");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asString();
	        }
	    }
	    //System.out.println("something went wrong?");
		return null;
    
	}	
	public static String getWhoLocked(Block door)
	{
		
	    List<MetadataValue> values = door.getMetadata("wholocked");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asString();
	        }
	    }
	    //System.out.println("something went wrong?");
		return null;
    
	}
	public static Integer getCount(Block door)
	{
		
	    List<MetadataValue> values = door.getMetadata("Count");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asInt();
	        }
	    }
	    //System.out.println("something went wrong?");
		return null;
    
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void PlayerInteract(PlayerInteractEvent event)
	{ 
	
	if(event.getClickedBlock() == null)
	{
		
	}	
	else if (event.getClickedBlock().getType() == Material.WOODEN_DOOR
				|| event.getClickedBlock().getType() == Material.IRON_DOOR
				||event.getClickedBlock().getType() == Material.IRON_DOOR_BLOCK
				|| event.getClickedBlock().getType() == Material.CHEST
				|| event.getClickedBlock().getType() == Material.WOOD_BUTTON
				|| event.getClickedBlock().getType() == Material.STONE_BUTTON
				|| event.getClickedBlock().getType() == Material.STONE_PLATE
				|| event.getClickedBlock().getType() == Material.WOOD_PLATE
				|| event.getClickedBlock().getType() ==Material.GOLD_PLATE
				|| event.getClickedBlock().getType() ==Material.IRON_PLATE)
		{
			if(LockDoors.getMetaData(event.getClickedBlock()) == null && LockDoors.getWhoLocked(event.getClickedBlock()) == null)
			{
				//old event.getClickedBlock().getMetadata("lock") == null
				//there is no lock so do nothing
				
				
			}	
			else if (LockDoors.getMetaData(event.getClickedBlock()) != null)
			{
				String metadata = getMetaData(event.getClickedBlock());
				//the door has a lock
				String item = event.getPlayer().getItemInHand().getType().name();
				if( metadata == null)
				{
					return;
				}	
				else if( metadata.equals(item))
				{
					return;
					
				}
				else
				{
					event.setCancelled(true);
					event.getPlayer().sendMessage(event.getClickedBlock().getType() + " is locked by " + getWhoLocked(event.getClickedBlock()));
					//player doesn't have the right key
				}	
				
			}	
	
			
		}	
		
		
	}	
	
	
	
	
	
}
