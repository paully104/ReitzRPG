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

public class OnBlockBreakWoodcuttingListener implements Listener {
	
	Reitzrpgmain plugin;
    public OnBlockBreakWoodcuttingListener(Reitzrpgmain instance)
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
	    miningpower = pd.getData().getInt("Woodcutting"); //$NON-NLS-1$
	    miningexp = pd.getData().getInt("Woodcutting-EXP"); //$NON-NLS-1$
	    mininglevelupcost = (miningpower * 100) + 1;
	    

	    
    	
    	ItemStack iteminhand = event.getPlayer().getItemInHand();
    	String blockbroken;
    	blockbroken = event.getBlock().getType().toString();
    	if(blockbroken.equals("WOOD") || blockbroken.equals("WOOD_PLANKS") || blockbroken.equals("VINES") || blockbroken.equals("FENCE") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    			|| blockbroken.equals("WOODEN_DOOR") || blockbroken.equals("BOOKSHELF") || blockbroken.equals("HUGE_MUSHROOM") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    			|| blockbroken.equals("WOODEN_STAIRS") || blockbroken.equals("WOODEN_SLAB") || blockbroken.equals("OAK_WOOD") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    			|| blockbroken.equals("SPRUCE_WOOD") || blockbroken.equals("JUNGLE_WOOD") || blockbroken.equals("OAK_WOOD_PLANKS") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    			|| blockbroken.equals("SPRUCE_WOOD_PLANKS") || blockbroken.equals("BIRCH_WOOD_PLANKS") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.equals("ACACIA_WOOD") || blockbroken.equals("ACACIA_WOOD_PLANKS") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.equals("BIRCH_WOOD") || blockbroken.equals("LOG") //$NON-NLS-1$ //$NON-NLS-2$
    			|| blockbroken.contains("DARK_OAK") || blockbroken.contains("LOG") //$NON-NLS-1$ //$NON-NLS-2$
    					|| blockbroken.contains("OAK")) //$NON-NLS-1$
		{
			newexptotal = miningexp + 4;
			//pd.getData().set("Woodcutting-EXP", newexptotal); //$NON-NLS-1$
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Woodcutting-EXP", newexptotal);
			//pd.save();
			FileConfiguration config = Reitzrpgmain.config;
			if(API.blockexpshow == true) //$NON-NLS-1$
			{
			
			long time = new java.util.Date().getTime();
			final long timeend = new java.util.Date().getTime()+1500;
			try
			{
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
    	{	System.out.println("Somebodies mining leveled up"); //$NON-NLS-1$
    		p.sendMessage(ChatColor.GOLD + Messages.getString("OnBlockBreakWoodcuttingListener.0")); //$NON-NLS-1$
    		int oldlevel = pd.getData().getInt("Woodcutting"); //$NON-NLS-1$
    		//pd.getData().set("Woodcutting", oldlevel+1); //$NON-NLS-1$
    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Woodcutting", oldlevel+1);
    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Woodcutting-EXP", miningexp - mininglevelupcost); //$NON-NLS-1$
    		//pd.save();
    		miningexp = pd.getData().getInt("Woodcutting-EXP"); //$NON-NLS-1$
    		miningpower = pd.getData().getInt("Woodcutting"); //$NON-NLS-1$
    		mininglevelupcost = (miningpower * 100) + 1;
    		ScoreboardStuff.manageScoreboard(p, "TeamName"); //$NON-NLS-1$
    		
    	}	
    	
    	
    }	
    
    
    
}
