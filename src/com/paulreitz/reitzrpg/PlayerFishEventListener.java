package com.paulreitz.reitzrpg;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerFishEventListener implements Listener
{
	@EventHandler
	public void FishEvent(PlayerFishEvent event)
	{
		Player p = event.getPlayer();
		//PlayerData pd = new PlayerData(p.getName());
		PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
		int fishingexp = pd.getData().getInt("Fishing-EXP"); //$NON-NLS-1$
		int fishinglevel = pd.getData().getInt("Fishing"); //$NON-NLS-1$
		int fishinglevelupcost = fishinglevel * 100;
		
		if (event.getState()  == PlayerFishEvent.State.CAUGHT_FISH)
		{	
			int expamount = (event.getExpToDrop() + 3) + fishingexp;
			//pd.getData().set("Fishing-EXP", expamount); //$NON-NLS-1$
			PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Fishing-EXP", expamount);
			fishingexp = pd.getData().getInt("Fishing-EXP"); //$NON-NLS-1$
			long time = new java.util.Date().getTime();
			final long timeend = new java.util.Date().getTime()+1500;
			final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +"+event.getExpToDrop()); //$NON-NLS-1$ //$NON-NLS-2$
//test
			World world = event.getPlayer().getWorld();
			Location loc = event.getPlayer().getLocation();
			Block behind = loc.getBlock();
			int direction = (int)loc.getYaw();
			 
			if(direction < 0) {
			    direction += 360;
			    direction = (direction + 45) / 90;
			}else {
			    direction = (direction + 45) / 90;
			}
			 
			switch (direction) {
			    case 1:
			        behind = world.getBlockAt(behind.getX() + -2, behind.getY(), behind.getZ());
			        c.show(behind.getLocation());
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

						}.runTaskTimer(Reitzrpgmain.getPlugin(), 0L, 2L);
			        
			        break;
			    case 2:
			        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() + -2);
			        c.show(behind.getLocation());
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

						}.runTaskTimer(Reitzrpgmain.getPlugin(), 0L, 2L);
			        
			        
			        break;
			    case 3:
			        behind = world.getBlockAt(behind.getX() - -2, behind.getY(), behind.getZ());
			        c.show(behind.getLocation());
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

						}.runTaskTimer(Reitzrpgmain.getPlugin(), 0L, 2L);
			        
			        
			        break;
			    case 4:
			        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - -2);
			        c.show(behind.getLocation());
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

						}.runTaskTimer(Reitzrpgmain.getPlugin(), 0L, 2L);
			       
			        break;
			    case 0:
			        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - -2);
			        c.show(behind.getLocation());
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

						}.runTaskTimer(Reitzrpgmain.getPlugin(), 0L, 2L);
			        break;
			    default:
			        break;
			}//end

			//pd.save();
			if(fishinglevel > 5)
			{
					double random = Math.random() * 1;
					Item fish = (Item) event.getCaught();
					ItemStack fish2 = fish.getItemStack();
					//Material fish2 = fish.getItemStack().getType();;
					if(random >= .49)
					{
					p.getInventory().addItem(fish2);
					}
					
					
				
				
			}
			if(fishinglevel > 10)
			{
					double random = Math.random() * 1;
					Item fish = (Item) event.getCaught();
					ItemStack fish2 = fish.getItemStack();
					//Material fish2 = fish.getItemStack().getType();;
					if(random >= .49)
					{
					p.getInventory().addItem(fish2);
					}
					
					
				
				
			}
			if(fishinglevel > 15)
			{
					double random = Math.random() * 1;
					Item fish = (Item) event.getCaught();
					ItemStack fish2 = fish.getItemStack();
					//Material fish2 = fish.getItemStack().getType();;
					if(random >= .49)
					{
					p.getInventory().addItem(fish2);
					}
					
					
				
				
			}
			if(fishinglevel > 20)
			{
					double random = Math.random() * 1;
					Item fish = (Item) event.getCaught();
					ItemStack fish2 = fish.getItemStack();
					//Material fish2 = fish.getItemStack().getType();;
					if(random >= .49)
					{
					p.getInventory().addItem(fish2);
					}
					
					
				
				
			}

	    if(fishingexp >= fishinglevelupcost)
	    	{	System.out.println("Somebodies fishing leveled up"); //$NON-NLS-1$
	    		p.sendMessage(ChatColor.GOLD + Messages.getString("PlayerFishEventListener.0")); //$NON-NLS-1$
	    		int oldlevel = pd.getData().getInt("Fishing"); //$NON-NLS-1$
	    		//pd.getData().set("Fishing", oldlevel+1); //$NON-NLS-1$
	    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Fishing", oldlevel+1);
	    		//pd.getData().set("Fishing-EXP", fishingexp - fishinglevelupcost); //$NON-NLS-1$
	    		PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("Fishing-EXP", fishingexp);
	    		//pd.save();
	    		fishingexp = pd.getData().getInt("Fishing-EXP"); //$NON-NLS-1$
	    		fishinglevel = pd.getData().getInt("Fishing"); //$NON-NLS-1$
	    		fishinglevelupcost = (fishinglevel * 100);
	    		ScoreboardStuff.manageScoreboard(p, "TeamName"); //$NON-NLS-1$
	    		
	    	}
			
		}	
		else if(event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY)
		{
			EntityType fish = event.getCaught().getType();
			if(fish == EntityType.ZOMBIE || fish == EntityType.SKELETON
				|| fish == EntityType.CREEPER || fish == EntityType.SPIDER)
			{	
				
				event.getCaught().setVelocity(new Vector(0,1,0));
			  	
			}  
		
		
		}	
	}

	
	
	
	
	
	
	
	
}
