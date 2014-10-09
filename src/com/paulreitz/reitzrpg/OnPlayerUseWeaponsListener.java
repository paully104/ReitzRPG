package com.paulreitz.reitzrpg;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerUseWeaponsListener implements Listener {
	
	Reitzrpgmain plugin;
    public OnPlayerUseWeaponsListener(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    }
	
	//20 ticks in a second so for a minute recast that would be 20*60 1200
	
	
	public long endtimer = 0, starttime = 0, endtime = 0, recasttime = 0;
	
	HashMap<String, Long> startofws = new HashMap<String, Long>();
	HashMap<String, Long> endofws = new HashMap<String, Long>();
	HashMap<String, Long> recastofws = new HashMap<String, Long>();
	HashMap<String, Boolean> wsavailable = new HashMap<String, Boolean>();

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event)
	{
		Player p = event.getPlayer();
		PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
		Integer longnumber = 0;
		startofws.put(p.toString(), longnumber.longValue());  //start of ws
		endofws.put(p.toString(), longnumber.longValue()); //end of ws
		recastofws.put(p.toString(), longnumber.longValue()); //recast time
		wsavailable.put(p.toString(), false);
		//p.setMaxHealth(pd.getData().getDouble("Health"));
		
	}	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onLogout(PlayerQuitEvent event)
	{
		startofws.remove(event.getPlayer().toString());
		endofws.remove(event.getPlayer().toString());
		recastofws.remove(event.getPlayer().toString());
		wsavailable.remove(event.getPlayer().toString());
		
	}	
			
	@EventHandler(ignoreCancelled = true,priority=EventPriority.LOWEST)
	public void onPlayerNoDamageEvent(EntityDamageByEntityEvent event)
	{
		
		
		if(event.getDamager() instanceof Player)
		{
			Player player = (Player) event.getDamager();
			 PlayerData pd = new PlayerData(player.getName());
			if( player.getItemInHand().getType() == Material.STONE_SWORD && 
					pd.getData().getInt("Attack") < 5 &&
					player.getItemInHand().getItemMeta().getDisplayName() == null)
			{
				event.setCancelled(true);

			
			}
			if( player.getItemInHand().getType() == Material.IRON_SWORD && 
					pd.getData().getInt("Attack") < 15 &&
					player.getItemInHand().getItemMeta().getDisplayName() == null)
			{
				event.setCancelled(true);

			
			}
			if( player.getItemInHand().getType() == Material.GOLD_SWORD && 
					pd.getData().getInt("Attack") < 10
					&& player.getItemInHand().getItemMeta().getDisplayName() == null)
			{
				event.setCancelled(true);

			
			}
			if( player.getItemInHand().getType() == Material.DIAMOND_SWORD && 
					pd.getData().getInt("Attack") < 20
					&& player.getItemInHand().getItemMeta().getDisplayName() == null)
			{
				event.setCancelled(true);

			
			}
			
			
		}
		
	}
	@EventHandler(priority=EventPriority.LOWEST)
	public void onPlayerUse(PlayerInteractEvent event)
	{
		
		final Player p = event.getPlayer();
		final ItemStack iteminhand = p.getInventory().getItemInHand();
		int attackpower, rangepower, defensepower; 
		double healthamount;
		final int slot = p.getInventory().getHeldItemSlot();
	    PlayerData pd = new PlayerData(p.getName());
	    attackpower = pd.getData().getInt("Attack"); //$NON-NLS-1$
	    defensepower = pd.getData().getInt("Defense"); //$NON-NLS-1$
	    rangepower = pd.getData().getInt("Archery"); //$NON-NLS-1$
	    healthamount = pd.getData().getDouble("Health"); //$NON-NLS-1$
	    String playerstring = p.toString();
	    //error here if the item doesnt have lore
	    if(recastofws.get(playerstring)  == null || startofws.get(playerstring) == null)//if you use /reload and they are online
	    {
	    	
			Integer longnumber = 0;
			startofws.put(p.toString(), longnumber.longValue());  //start of ws
			endofws.put(p.toString(), longnumber.longValue()); //end of ws
			recastofws.put(p.toString(), longnumber.longValue()); //recast time
			wsavailable.put(p.toString(), false);
			//p.setMaxHealth(pd.getData().getDouble("Health"));
	    	
	    	
	    }	
	    
	    
	    
	    else if(p.getItemInHand().getType() == Material.WOOD_SWORD &&
	    		p.getItemInHand().getItemMeta().getDisplayName() == null)
	    {
	    	
	    	if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
	    	{
	    		if(p.getWorld().getTime() >= recastofws.get(playerstring) || startofws.get(playerstring) == 0
	    				|| recastofws.get(playerstring)  == null || startofws.get(playerstring) == null)
	    		{
	    					
	    		
	    					
	    					 starttime = p.getWorld().getTime(); //current time in timethread
	    					 endtime = starttime + 400; //end of weaponskill in timethread
	    					 recasttime = endtime + 1200;
	    					 startofws.put(playerstring, starttime);
	    					 endofws.put(playerstring, endtime);
	    					 recastofws.put(playerstring,recasttime);
	    					 wsavailable.put(playerstring, false);
	    					//System.out.println("Wood_Sword weapon skill test!");
	    					p.getItemInHand().addEnchantment(Enchantment.KNOCKBACK, 1);
	    			
	    		}
	    		else
	    		{
	    			//p.sendMessage("Recast timer still active!");
	    		}	
	    		
	    					
	    	            	//getItemInHand() returns an ItemStack whereas Item.COOKIE is an NMS Item, they will never be equal
	    	            		    	            	
	    	} 
	    	else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction().equals(Action.LEFT_CLICK_BLOCK))	
	    		
			{	
				//System.out.println(starttime + "Player starttime");
	    		//System.out.println(endtime + "Player endtime");
	    		//System.out.println(startofws.get(playerstring) + " start of ws hashmap");
	    		//System.out.println(endofws.get(playerstring) + "end of ws hashmap!");
	    		//System.out.println(p.getWorld().getTime() + "WORLD TIME");
	    		if ( p.getWorld().getTime() > endofws.get(playerstring) && wsavailable.get(playerstring) == false)
	    		{
	    			
        		iteminhand.removeEnchantment(Enchantment.KNOCKBACK);
        		//weapon skill over
        		wsavailable.put(playerstring, true);
	    		}	
	    		else
	    		{
	    			//no need to do anything, ws still in progress
	    		}	
	    		
			}    			
	    }
	    	else if(p.getItemInHand().getType() == Material.WOOD_SWORD && 
	    		p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Dirt Sword")) //$NON-NLS-1$
	    	{
	    	
	    		if(pd.getData().getInt("Attack") > 1 && pd.getData().getInt("Magic") > 1) //$NON-NLS-1$ //$NON-NLS-2$
	    		{	
	    		
	    
	    			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
	    			{
	    				if(p.getWorld().getTime() >= recastofws.get(playerstring) || startofws.get(playerstring) == 0)
	    				{
	    					

	    					 starttime = p.getWorld().getTime(); //current time in timethread
	    					 endtime = starttime + 400; //end of weaponskill in timethread
	    					 recasttime = endtime + 1200;
	    					 startofws.put(playerstring, starttime);
	    					 endofws.put(playerstring, endtime);
	    					 recastofws.put(playerstring,recasttime);
	    					 wsavailable.put(playerstring, false);
	    					//System.out.println("Wood_Sword weapon skill test!");
	    					p.getItemInHand().addEnchantment(Enchantment.KNOCKBACK, 1);
	    			
	    				}
	    				else
	    				{
	    					p.sendMessage(Messages.getString("OnPlayerUseWeaponsListener.7")); //$NON-NLS-1$
	    				}
	    			}
	    		

	    			else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction().equals(Action.LEFT_CLICK_BLOCK))	
	    		
	    			{	
	    				if ( p.getWorld().getTime() > endofws.get(playerstring) && wsavailable.get(playerstring) == false)
	    				{
	    			
	    					iteminhand.removeEnchantment(Enchantment.KNOCKBACK);
	    					p.sendMessage(Messages.getString("OnPlayerUseWeaponsListener.8")); //$NON-NLS-1$
	    					wsavailable.put(playerstring, true);
	    				}	
	    				else
	    				{
	    			//no need to do anything, ws still in progress
	    				}	
	    		
	    		} 
	    	}
	    		else
	    		{

	    			p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseWeaponsListener.9")); //$NON-NLS-1$
	    			final long time2 = new java.util.Date().getTime() + 150;
		    		new BukkitRunnable(){
		    			
		    			@Override
		    			public void run(){
		    				long time = new java.util.Date().getTime();
		    				if(p.getItemInHand() != null || p.getItemInHand() != new ItemStack(Material.AIR))
		    				{
			    	    		if(p.getInventory().getHeldItemSlot() < 8)
			    	    		{
			    	    			p.getInventory().setHeldItemSlot(slot + 1);
			    	    			  //$NON-NLS-1$
			    	    			return;
			    	    		}
			    	    		else
			    	    		{
			    	    			p.getInventory().setHeldItemSlot(slot - 1);
			    	    			 //$NON-NLS-1$
			    	    			return;//$NON-NLS-1$
			    	    		}
		    					
		    				}
		    				else
		    				{
		    					this.cancel();
		    				}
		    				
		    			}
		    			
		    		}.runTaskLater(plugin,1L);
	    			
	    		}	
	    	
	    	
	    }	
	    else if (p.getItemInHand().getType() == Material.BOW)
	    {
	    	if (rangepower >= 10 )
	    	{
	    		p.getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, 1);
	    		
	    	}	
	    }
	    else if(p.getItemInHand().getType() == Material.GOLD_SWORD)
	    {
	    	if (attackpower >= 10)
	    	{
	    		
	    	}	
	    	else
	    	{

	    		p.sendMessage(ChatColor.RED + Messages.getString("OnPlayerUseWeaponsListener.10")); //$NON-NLS-1$
    			final long time = new java.util.Date().getTime();
    			final long time2 = time + 150;
	    		new BukkitRunnable(){
	    			
	    			@Override
	    			public void run(){
	    				final long time = new java.util.Date().getTime();
	    				if(p.getItemInHand() != null || p.getItemInHand() != new ItemStack(Material.AIR))
	    				{
		    	    		if(p.getInventory().getHeldItemSlot() < 8)
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot + 1);
		    	    			 //$NON-NLS-1$
		    	    			return;
		    	    		}
		    	    		else
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot - 1);
		    	    			 //$NON-NLS-1$
		    	    			return;//$NON-NLS-1$
		    	    		}
	    					
	    				}
	    				else
	    				{
	    					this.cancel();
	    				}
	    				
	    			}
	    			
	    		}.runTaskLater(plugin,1L);
	    		
	    	}
	    	
	    	
	    }	
	    else if(p.getItemInHand().getType() == Material.STONE_SWORD)
	    {
	    	if (attackpower >= 5)
	    	{
	    		
	    	}	
	    	else
	    	{
	    		//p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		//p.updateInventory();
	    		//p.getWorld().dropItem(p.getLocation().add(2.0, 2.0, 2.0), iteminhand);
	    		p.sendMessage(ChatColor.RED + Messages.getString("OnPlayerUseWeaponsListener.11")); //$NON-NLS-1$
    			final long time = new java.util.Date().getTime();
    			final long time2 = time + 150;
	    		
	    		
	    		new BukkitRunnable(){
	    			
	    			@Override
	    			public void run(){
	    				 long time = new java.util.Date().getTime();
	    				if(p.getItemInHand() != null || p.getItemInHand() != new ItemStack(Material.AIR))
	    				{
		    	    		if(p.getInventory().getHeldItemSlot() < 8)
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot + 1);
		    	    			//$NON-NLS-1$
		    	    			return;
		    	    		}
		    	    		else
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot - 1);
		    	    			  //$NON-NLS-1$
		    	    			return;//$NON-NLS-1$
		    	    		}
	    					
	    				}
	    				else
	    				{
	    					this.cancel();
	    				}
	    				
	    			}
	    			
	    		}.runTaskLater(plugin,1L);
	    		
	    	}
	    	
	    	
	    }	
	    else if(p.getItemInHand().getType() == Material.IRON_SWORD)
	    {
	    	if (attackpower >= 15)
	    	{
	    		
	    	}	
	    	else
	    	{

	    		p.sendMessage(ChatColor.RED + Messages.getString("OnPlayerUseWeaponsListener.12")); //$NON-NLS-1$
    			final long time = new java.util.Date().getTime();
    			final long time2 = time + 150;
	    		new BukkitRunnable(){
	    			
	    			@Override
	    			public void run(){
	    				long time = new java.util.Date().getTime();
	    				if(p.getItemInHand() != null || p.getItemInHand() != new ItemStack(Material.AIR))
	    				{
		    	    		if(p.getInventory().getHeldItemSlot() < 8)
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot + 1);
		    	    			 //$NON-NLS-1$
		    	    			return;
		    	    		}
		    	    		else
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot - 1);
		    	    			  //$NON-NLS-1$
		    	    			return;//$NON-NLS-1$
		    	    		}
	    					
	    				}
	    				else
	    				{
	    					this.cancel();
	    				}
	    				
	    			}
	    			
	    		}.runTaskLater(plugin,1L);
	    		
	    	}
	    	
	    	
	    }	
	    else if(p.getItemInHand().getType() == Material.DIAMOND_SWORD)
	    {
	    	if (attackpower >= 20)
	    	{
	    		
	    	}	
	    	else
	    	{
	    		event.setCancelled(true);
	    		p.sendMessage(ChatColor.RED + Messages.getString("OnPlayerUseWeaponsListener.13")); //$NON-NLS-1$
    			final long time = new java.util.Date().getTime();
    			final long time2 = time + 150;
	    		new BukkitRunnable(){
	    			//diamond sword
	    			@Override
	    			public void run(){
	    				 long time = new java.util.Date().getTime();
	    				if(p.getItemInHand() != null || p.getItemInHand() != new ItemStack(Material.AIR))
	    				{
		    	    		if(p.getInventory().getHeldItemSlot() < 8)
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot + 1);
		    	    			//$NON-NLS-1$
		    	    			
		    	    		}
		    	    		else
		    	    		{
		    	    			p.getInventory().setHeldItemSlot(slot - 1);
		    	    			 //$NON-NLS-1$
		    	    		}
	    					
	    				}
	    				else
	    				{
	    					this.cancel();
	    				}
	    				
	    			}
	    			
	    		}.runTaskLater(plugin,1L);
	    		
	    	}
	    	
	    }
	    else if (p.getItemInHand().getType() == Material.STICK)
	    {
	    	ItemStack item = p.getItemInHand();
	    	ItemMeta meta = item.getItemMeta();
	    	String metastring = meta.getDisplayName();
	    
	    	if (metastring != null && metastring.equals("Tornado Wand")) //$NON-NLS-1$
	    	{
	    		
	    		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
	    		{	
	    			
	    			if(pd.getData().getInt("Magic") >= 5) //$NON-NLS-1$
	    			{		
	    			
	    				if(p.getWorld().getTime() >= recastofws.get(playerstring) || startofws.get(playerstring) == 0 
	    						|| recastofws.get(playerstring).equals(null) || startofws.get(playerstring).equals(null)
	    						&& Reitzrpgmain.config.getBoolean("Tornadoes") == true)
	    				{
	    					
		    					
		    					 starttime = p.getWorld().getTime(); //current time in timethread
		    					 endtime = starttime + 20; //end of weaponskill in timethread
		    					 recasttime = endtime + 60;
		    					 startofws.put(playerstring, starttime);
		    					 endofws.put(playerstring, endtime);
		    					 recastofws.put(playerstring,recasttime);
		    					 //wsavailable.put(playerstring, false);
		    					 System.out.println(recastofws);
		    					 p.chat("/reitzrpg tornado"); //$NON-NLS-1$
		    			
	    				}
	    				else
		    				{
		    				Long timeremaining = recastofws.get(playerstring) - p.getWorld().getTime();
		    				p.sendMessage("Recast time is: " + timeremaining); //$NON-NLS-1$
		    				}	
	    			}
	    			else
	    			{ 
	    				
	    				
	    	    		if(p.getInventory().getHeldItemSlot() < 8)
	    	    		{
	    	    			p.getInventory().setHeldItemSlot(slot + 1);
	    	    			 //$NON-NLS-1$
	    	    			
	    	    		}
	    	    		else
	    	    		{
	    	    			p.getInventory().setHeldItemSlot(slot - 1);
	    	    			  //$NON-NLS-1$
	    	    		}
	    				
	    			}
	    		}
	    			
	    			  			    		
	    	}	
	    	else
	    	{
	    		//not the magic stick
	    	}	
	    	
	    	
	    }	
	    
	    else
	    {
	    	//if they dont have an item with a ws or something
	    }	
	 
	    		 
	    		 
	}//end of the item
	

}
