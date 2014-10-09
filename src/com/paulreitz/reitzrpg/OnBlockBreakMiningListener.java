package com.paulreitz.reitzrpg;

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

public class OnBlockBreakMiningListener implements Listener {
	
	Reitzrpgmain plugin;
    public OnBlockBreakMiningListener(Reitzrpgmain instance)
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
	    //PlayerData pd = new PlayerData(p.getName());
    	PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
	    miningpower = pd.getData().getInt("Mining");
	    miningexp = pd.getData().getInt("Mining-EXP");
	    mininglevelupcost = (miningpower * 100) + 1;
	    mininglevel = miningexp/mininglevelupcost;

	    
    	
    	ItemStack iteminhand = event.getPlayer().getItemInHand();
    	String blockbroken;
    	blockbroken = event.getBlock().getType().toString();
    	if(blockbroken.equals("NETHERRACK"))
    			{
    				newexptotal = miningexp + 2;
    				PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining-EXP", newexptotal);
    				pd.save();
    				FileConfiguration config = Reitzrpgmain.config;
    				if(API.blockexpshow == true)
    				{
    					long time = new java.util.Date().getTime();
    					final long timeend = new java.util.Date().getTime()+1500;
    					try{
    					final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +2");
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
    	else if(blockbroken.equals("STONE") || blockbroken.equals("STONE_BRICK")
    			|| blockbroken.equals("COBBLESTONE") || blockbroken.equals("BLOCK_OF_QUARTZ")
    			|| blockbroken.equals("STAINED_CLAY") || blockbroken.equals("NETHER_BRICK")
    			|| blockbroken.equals("SANDSTONE") || blockbroken.equals("IRON_ORE"))
		{
			newexptotal = miningexp + 4;
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining-EXP", newexptotal);
			//pd.getData().set("Mining-EXP", newexptotal);
			//pd.save();

			FileConfiguration config = Reitzrpgmain.config;
			if(API.blockexpshow == true)
			{
				long time = new java.util.Date().getTime();
				final long timeend = new java.util.Date().getTime()+1500;
				try
				{
				final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +4");
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
    	else if(blockbroken.equals("COAL_ORE") || blockbroken.equals("COAL"))
		{
			newexptotal = miningexp + 10;
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining-EXP", newexptotal);
			//pd.getData().set("Mining-EXP", newexptotal);
			//pd.save();

			FileConfiguration config = Reitzrpgmain.config;
			if(API.blockexpshow == true)
			{
				long time = new java.util.Date().getTime();
				final long timeend = new java.util.Date().getTime()+1500;
				try
				{
				final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +10");
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
    	else if(blockbroken.equals("EMERALD_ORE") || blockbroken.equals("GOLD_ORE")
    			|| blockbroken.equals("NETHER_QUARTZ_ORE") || blockbroken.equals("MONSTER_SPAWNER"))
    	{
			newexptotal = miningexp + 20;
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining-EXP", newexptotal);
			//pd.getData().set("Mining-EXP", newexptotal);
			//pd.save();

			FileConfiguration config = Reitzrpgmain.config;
			if(API.blockexpshow == true)
			{
				long time = new java.util.Date().getTime();
				final long timeend = new java.util.Date().getTime()+1500;
				try
				{
				final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +20");
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
    	else if(blockbroken.equals("DIAMOND_ORE") || blockbroken.equals("DIAMOND_BLOCK"))
    	{
			newexptotal = miningexp + 75;
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining-EXP", newexptotal);
			//pd.getData().set("Mining-EXP", newexptotal);
			//pd.save();

			FileConfiguration config = Reitzrpgmain.config;
			if(API.blockexpshow == true)
			{

				long time = new java.util.Date().getTime();
				final long timeend = new java.util.Date().getTime()+1500;
				try
				{
				final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +75");
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
    	{	System.out.println("Somebodies mining leveled up");
    		p.sendMessage(ChatColor.GOLD + "MINING LEVEL UP");
    		int oldlevel = pd.getData().getInt("Mining");
    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining", oldlevel+1);
    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Mining-EXP", miningexp - mininglevelupcost);
    		//pd.save();
    		miningexp = pd.getData().getInt("Mining-EXP");
    		miningpower = pd.getData().getInt("Mining");
    		mininglevelupcost = (miningpower * 100) + 1;
    		ScoreboardStuff.manageScoreboard(p, "TeamName");
    		
    	}	
    	
    	
    }	
    
    
    
}
