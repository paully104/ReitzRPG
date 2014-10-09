package com.paulreitz.reitzrpg;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class OnBlockBreakDiggingListener implements Listener {
	
	Reitzrpgmain plugin;
    public OnBlockBreakDiggingListener(Reitzrpgmain instance)
    {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig!        
    }
    @EventHandler(ignoreCancelled = true,priority=EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event)
    {
    	if (event.isCancelled()) { return; }
    	if(event.getPlayer().getGameMode() == GameMode.CREATIVE)
    	{
    		return; //no exp for creative!
    	}	
    	int miningpower, woodcuttingpower, miningexp, newexptotal, mininglevelupcost, mininglevel;
    	Player p = event.getPlayer();
	    //PlayerData pd = new PlayerData(p.getName()); old style 
    	
    	PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
    	//HashMap<String, PlayerData> local = PlayerJoinListener.users;
    	
	    miningpower = pd.getData().getInt("Digging"); //$NON-NLS-1$
	    miningexp = pd.getData().getInt("Digging-EXP"); //$NON-NLS-1$
	    mininglevelupcost = (miningpower * 100) + 1;
	    mininglevel = miningexp/mininglevelupcost;

	    
    	
    	ItemStack iteminhand = event.getPlayer().getItemInHand();
    	String blockbroken;
    	blockbroken = event.getBlock().getType().toString();
    	if(blockbroken.contains("NETHERRACK")) //$NON-NLS-1$
    			{
    				newexptotal = miningexp + 2;
    				PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Digging-EXP", newexptotal);
    				//pd.getData().set("Digging-EXP", newexptotal); //$NON-NLS-1$ old way
    				
    				//pd.save(); save saves it to the file not needed when storing locally till disconnect
    				
    				
    				
    				FileConfiguration config = Reitzrpgmain.config;
    				if(API.blockexpshow == true) //$NON-NLS-1$
    				{//show this stuff	
    					long time = new java.util.Date().getTime();
    					final long timeend = new java.util.Date().getTime()+1500;
    					
    					
    					//$NON-NLS-1$ //$NON-NLS-2$
    					 
    					try{
    						final Hologram c =  new Hologram(ChatColor.GREEN+"EXP: "+" +2");
    						c.show(event.getBlock().getLocation());
    					
    					
    					

    					
    				new BukkitRunnable(){

    				@Override
    				public void run(){
    					
    					
    					
    					final long time = new java.util.Date().getTime();
    				
    					if(time<timeend)
    					{
    						

    					}
    					else
    					{
    						try{
    						c.destroy();
    						}
    						catch(NoClassDefFoundError e)
    						{
    							
    						}
    						
    						
    						this.cancel();
    					}


    				}

    				}.runTaskTimer(plugin, 0L, 2L);
    				}
    				catch(NoClassDefFoundError e)
    				{
    					
    				}
    				}
    				
    				
    		
    			}
    	if(blockbroken.contains("CLAY") || blockbroken.contains("FARMLAND") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.contains("GRASS") || blockbroken.contains("GRAVEL") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.contains("MYCELIUM") || blockbroken.contains("DIRT") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.contains("SAND") || blockbroken.contains("SOUL_SAND") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.contains("SNOW")) //$NON-NLS-1$
		{
			newexptotal = miningexp + 4;
			
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Digging-EXP", newexptotal);
			//pd.getData().set("Digging-EXP", newexptotal); //$NON-NLS-1$
			//System.out.println(pd.getData().get("Digging-EXP"));
			//pd.save(); save saves it to the file not needed when storing locally till disconnect

			FileConfiguration config = Reitzrpgmain.config;
			if(API.blockexpshow == true) //$NON-NLS-1$
			{
				long time = new java.util.Date().getTime();
				final long timeend = new java.util.Date().getTime()+1500;
				
				try{
				final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +4"); //$NON-NLS-1$ //$NON-NLS-2$
				c.show(event.getBlock().getLocation());


			new BukkitRunnable(){

			@Override
			public void run(){
				
				
				
				final long time = new java.util.Date().getTime();
			
				if(time<timeend)
				{
					

				}
				else
				{
					c.destroy();
					this.cancel();
				}


			}

			}.runTaskTimer(plugin, 0L, 2L);
		}
			
			
			catch(NoClassDefFoundError e)
			{
				
			}
		}
		} 
    	
    	if(miningexp >= mininglevelupcost)
    	{	System.out.println(event.getPlayer().toString() + " mining has leveled!"); //$NON-NLS-1$
    		p.sendMessage(ChatColor.GOLD + Messages.getString("OnBlockBreakDiggingListener.0")); //$NON-NLS-1$
    		int oldlevel = pd.getData().getInt("Digging"); //$NON-NLS-1$
    		
    		
    		//pd.getData().set("Digging", oldlevel+1); //$NON-NLS-1$ old way
    		//pd.getData().set("Digging-EXP", miningexp - mininglevelupcost); //$NON-NLS-1$ old way
    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Digging", oldlevel+1);
    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Digging-EXP", miningexp-mininglevelupcost);
    		
    		//pd.save(); save saves it to the file not needed when storing locally till disconnect
    		miningexp = pd.getData().getInt("Digging-EXP"); //$NON-NLS-1$
    		miningpower = pd.getData().getInt("Diging"); //$NON-NLS-1$
    		mininglevelupcost = (miningpower * 100) + 1;
    		ScoreboardStuff.manageScoreboard(p, "TeamName"); //$NON-NLS-1$
    		
    	}	
    	
    	
    }	
    
    
    
}
