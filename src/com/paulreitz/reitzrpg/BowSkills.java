package com.paulreitz.reitzrpg;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;



public class BowSkills implements Listener {

	File bowskills = Reitzrpgmain.bowskills;
	YamlConfiguration skillsconfig = YamlConfiguration.loadConfiguration(bowskills);
	
	@SuppressWarnings("rawtypes")
	HashMap<String, ArrayList> bowlist = new HashMap<String, ArrayList>();

	
	Reitzrpgmain plugin;
    public BowSkills(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    }
	public boolean removeArrow(Arrow arrow)
	{
	    List<MetadataValue> values = arrow.getMetadata("Remove");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(plugin.getName()))
	        {
	        	
	            return val.asBoolean();
	        }
	    }
	    //System.out.println("something went wrong?");
	    return false;    
	}
	//get nearby blocks
    public static List<Block> getNearbyBlocks(Location location, int Radius)
    {
        List<Block> Blocks = new ArrayList<Block>();
        
        for (int X = location.getBlockX() - Radius ; X <= location.getBlockX() + Radius ; X++)
        {
            for (int Y = location.getBlockY() - Radius ; Y <= location.getBlockY() + Radius ; Y++)
            {
                for (int Z = location.getBlockZ() - Radius ; Z <= location.getBlockZ() + Radius ; Z++)
                {
                    Block block = location.getWorld().getBlockAt(X, Y, Z);
                    if (!block.isEmpty())
                    {
                        Blocks.add(block);
                    }
                }
            }
        }
        
        return Blocks;
    }

	//COMBO SYSTEM
    public void ComboSystem(String name,final Player player)
    {


    	//Triple Shot
    	String[] triplearray = {"Right","Left","Left","Right"};
    	List<String> triplearraylist =new ArrayList<String>(Arrays.asList(triplearray));
    	//bomb array
    	String[] bombarray = {"Right","Right","Right","Left"};
    	List<String> bombarraylist =new ArrayList<String>(Arrays.asList(bombarray));
 
		
    	PlayerData pd = PlayerJoinListener.users.get(name);
		int archery = pd.getData().getInt("Archery");
		if(bowlist.get(name).equals(triplearraylist) && archery >= skillsconfig.getInt("Rapid-Shot-Level")
				&& skillsconfig.getBoolean("Rapid-Shot")== true)
		{
			player.sendMessage(ChatColor.GREEN+ name + ChatColor.GRAY+" uses Rapid Bow");
			final long timeend = new java.util.Date().getTime()+1250;
	
			
			 
			 //start
			 
				new BukkitRunnable(){

				@Override
				public void run(){

					
					final long time = new java.util.Date().getTime();
				
					if(player.getInventory().contains(262) && time<timeend)
					{
						
					final Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
					arrow.setShooter(((LivingEntity) player));
					arrow.setVelocity(player.getEyeLocation().getDirection().multiply(2));
					player.getInventory().removeItem(new ItemStack(Material.ARROW,1));
					player.updateInventory();
					}
					else
					{
						this.cancel();
					}


				}

				}.runTaskTimer(plugin, 0L, 2L);




				
				
			}	
		
		
		if(bowlist.get(name).equals(bombarraylist) && archery >= skillsconfig.getInt("TNT-Shot-Level")
				&& skillsconfig.getBoolean("TNT-Shot") == true)
		{
			player.sendMessage(ChatColor.GREEN+ name + ChatColor.GRAY+" uses TNT arrow");
			final long timeend = new java.util.Date().getTime()+300;
	
			
			 
			 //start
			 
				new BukkitRunnable(){

				@Override
				public void run(){

					
					final long time = new java.util.Date().getTime();
				
					if(player.getInventory().contains(262) && time<timeend)
					{
						
						//Creates a new TNTPrimed entity
						Entity tnt = player.getPlayer().getWorld().spawn(player.getPlayer().getEyeLocation(), TNTPrimed.class);
						//Sets it to detonate after 40 ticks (2 seconds)
						((TNTPrimed)tnt).setFuseTicks(10);
						 
						//Sets its velocity to the player's direction
						//Alternatively:
						//Does the above, but scales up its velocity so it will move 5 times faster
						tnt.setVelocity(player.getPlayer().getLocation().getDirection().multiply(2.0));
						player.getInventory().removeItem(new ItemStack(Material.ARROW,1));
						player.updateInventory();
					}
					else
					{
						this.cancel();
					}


				}

				}.runTaskTimer(plugin, 0L, 2L);




				
				
			}	




		if(bowlist.get(name) == null)
		{
			
		}
		else if(bowlist.get(name).size() > 5)
		{
			bowlist.remove(name);
		}

    	
    	
    }	
    


    @EventHandler
    public void notaBowevent(PlayerItemHeldEvent event)
    {
    	if(!event.getPlayer().getItemInHand().equals(Material.BOW))
    	{
    		bowlist.remove(event.getPlayer().getName());
    		
    	}
    	
    }
	
    @EventHandler
    public void magicuserdisconnect(PlayerQuitEvent event)
    {
    	
    	bowlist.remove(event.getPlayer().getName());
    }
    @EventHandler
    public void magicuserkickdisconnect(PlayerKickEvent event)
    {
    
    	bowlist.remove(event.getPlayer().getName());
    }
    
	@EventHandler
	public void magicBowhandler(PlayerInteractEvent event)
	{
		
		
		
		Player player = event.getPlayer();
		String name = player.getName();

		if(player.getItemInHand().getType().name().equalsIgnoreCase("BOW")
				|| player.getItemInHand().getTypeId() == 261)

				

  {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				


				ArrayList<String> list = bowlist.get(name);
				if(list == null)
				{
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add("Right");
					bowlist.put(name, list2);
					
				}	
				else
				{
				list.add("Right");
				bowlist.put(name,list);
				
				}
				ComboSystem(name,player);

			}
				
			if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			{
					long time = new java.util.Date().getTime();

				ArrayList<String> list = bowlist.get(name);
				if(list == null)
				{
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add("Left");
					bowlist.put(name, list2);
					
				}	
				else
				{
				list.add("Left");
				bowlist.put(name,list);
				
				}

				ComboSystem(name,player);
				
			}

					
			
		}	
		else
		{
			ArrayList arraylist = bowlist.get(name);
			if(arraylist != null)
			{
			
			if(arraylist.size() > 0)
					{
						bowlist.remove(player.getName());
				
					}
			}
			
		}	
				
		
		
	}	
}
