
package com.paulreitz.reitzrpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.util.com.google.common.util.concurrent.AbstractScheduledService.Scheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.paulreitz.reitzrpg.CombatEngine;

public class DistanceLevel implements Listener {
	Reitzrpgmain plugin;  
	CombatEngine combatengine = new CombatEngine();
	CustomWeaponEngine customweaponengine = new CustomWeaponEngine();
	CustomArmorEngine customarmorsengine = new CustomArmorEngine();
    public DistanceLevel(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig!    
    }
    
    
	//int i = plugin.config.getInt("Test"); example how to get info from config
    //start of varibles
   // World world = Bukkit.getServer().getWorld(plugin.config.getString("World")); cannot call config before main makes it?!
  //  Location worldspawn = world.getSpawnLocation();
    
    public static double damageincrease;
    public int level;
    public String outsidemobname;

    
    
	public void  setMonsterLevel(double x,double y,double z,Entity monster)// get the increased difficlty module
	{
		//double increaseddamage = x + y + z ;
		//double spawn = (worldspawn.getX() + worldspawn.getY() + worldspawn.getZ());
		
		 // World world = Bukkit.getServer().getWorld(Reitzrpgmain.config.getString("World")); //use monster for all worlds
		  World world = monster.getLocation().getWorld();
		  Location worldspawn = world.getSpawnLocation();
		double xdifference = Math.abs(x - worldspawn.getX());
		double ydifference = Math.abs(y - worldspawn.getY());
		double zdifference = Math.abs(z - worldspawn.getZ());
		double totaldifference = xdifference + ydifference + zdifference;
		//double distance = Math.abs(increaseddamage - spawn);
		//double increaseddamage2 = distance / config.getInt("Distance-To-MobDMG-Increase");
		double increaseddamage2 = totaldifference / API.distancetomobdamageincrease;
		//String formatter = String.format("%1$.2f", increaseddamage2);
		String formatter = String.format("%1$.0f", increaseddamage2);
		double value = Double.parseDouble(formatter);
		damageincrease = value; //sets the varaible in main to this
		
	}	
	public static double getMonsterLevel(Entity monster)
	{	//start of per world damage 
		String world = monster.getWorld().getName().toString();
		double basedamage = damageincrease;
		Integer worldstart = null;
		//Double worldstart = Reitzrpgmain.config.getDouble(world+".Level");
		try
		{
			worldstart = Integer.parseInt(RpgSystem.WorldList.get(world));
		}
		catch(NumberFormatException e)
		{
			
		}
		if (worldstart == null || worldstart == 0)
		{
			worldstart = 1;
		}
		return damageincrease + worldstart; //returns the value of the variable at top
	}
	public static double getSpawnLevel(Entity monster)
	{
	    List<MetadataValue> values = monster.getMetadata("level");
	    for(MetadataValue val : values)
	    {
	        if(val.getOwningPlugin().getName().equals(Reitzrpgmain.getPlugin().getName()))//was plugin.getName()
	        {
	        	int size = RpgSystem.size;
	        	double minlevel = 0.0;
	        	int level2 = 1;
	        	String world = monster.getWorld().getName();
	        	for(int i = 1;i < size+1;i++)
				{
	        		
	        		if(world == RpgSystem.WorldList.get("Worlds."+i+monster.getWorld().getName()));
	        		{
		        		try{
			        		
			        		String level = RpgSystem.WorldList.get("Worlds."+i+".Level");
			        		if(level != null)
			        		{	
			        			level2 = Integer.parseInt(level);
			        		}
	
			        		if(level2 != 1 || level2 != 0)
			        		{
			        			minlevel = level2;
			        			
			        		}
			        		}
			        		catch(NullPointerException e)
			        		{
			        			
			        		}
	        			
	        		}
	        		
	        		

	        		

				}
	        	if(val.asDouble() > minlevel)
	        	{
	            return val.asDouble();
	        	}
	        	else
	        	{
	        		return minlevel;
	        	}
	        }
	    }
	    //System.out.println("something went wrong?");
	    return 0;    
	}

	
	@EventHandler(ignoreCancelled = true,priority=EventPriority.HIGH) //was highest
	public void onDamage(EntityDamageByEntityEvent event)
	{
		//System.out.println(event.getEntity().getWorld().getName());
		//System.out.println(RpgSystem.WorldList.get(event.getEntity().getWorld().getName()));
		if (event.isCancelled())
		{ return; }
		
		if(event.getDamager() instanceof Monster && event.getDamager().hasMetadata("level") == false
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().getName()) != null)
		{
			//System.out.println("Correcting mob so it has metadata");
			Monster monster = (Monster) event.getDamager();
			Location monsterlocation = monster.getLocation().subtract(0, -1, 0);
			setMonsterLevel(monsterlocation.getX(),monsterlocation.getY(), monsterlocation.getZ(),monster);
			
			String mobname = event.getDamager().getType().toString();
			outsidemobname = mobname;
			//String formatter = String.format("%1$.2f", getMonsterLevel(event.getEntity()));
			String formatter = String.format("%1$.0f", getMonsterLevel(event.getEntity()));
			//String mobnamelevel = mobname + " " + getMonsterLevel();
			double d = Math.random();
			double e = Math.random();

			String mobnamelevel = mobname + " " + "lv: " + formatter;
			//System.out.println(mobnamelevel);
			if(API.fastmobs == true)
			{
			monster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 18000, 1));
			}
			monster.setCustomName(mobnamelevel);
			monster.setCustomNameVisible(true);
			//double health = event.getEntity().getMaxHealth();
			double health = 2.0;
			monster.setMetadata("level", new FixedMetadataValue(plugin, getMonsterLevel(event.getEntity())));
			monster.setMaxHealth(getMonsterLevel(event.getEntity()) * API.getMonsterHealthBonus()); //health formula is now lv *2
			double finalhealth = monster.getMaxHealth();
			monster.setHealth(finalhealth);	
					
		}
	
		//If a monster is attacking a player
		if (event.getDamager() instanceof Monster && event.getEntity() instanceof Player && 
				RpgSystem.WorldList.get(event.getDamager().getWorld().getName()) != null) //added in this to see if the world is active
		{	
				//MONSTER IS ATTACKING PLAYER
				Player player = (Player) event.getEntity();
			    Entity monster = event.getDamager();
				//String formatter = String.format("%1$.2f", getMonsterLevel(event.getDamager()));
				//String formatter = String.format("%1$.0f", getMonsterLevel(event.getDamager())); this was for the levels
				//Double.parseDouble(formatter);
				//double monsterdamage = event.getDamage() +  getSpawnLevel(monster);
				//double monsterdamage = getSpawnLevel(monster);
				double damage = combatengine.calculateMonsterAttackingPlayer(player, monster, event);
				damage = damage - customarmorsengine.CalculateDefenseFromCustomArmor(plugin, player, CustomArmors.size, CustomArmors.list, event);
				event.setDamage(damage);

					
				
        }
		else if (event.getDamager() instanceof Player && event.getEntity() instanceof Monster
				&& !(event.getDamager() instanceof Arrow) && event.getCause() != DamageCause.PROJECTILE
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().getName()) != null) //check to see if world is active in config
		{
			
				//PLAYER ATTACKING MONSTER not using an arrow
				Entity player = event.getDamager();
				Player player2 = (Player) player;
				Entity monster = event.getEntity();
				int damage = combatengine.calculatePlayerAttackingMonster(player, monster, event);
				int weapondamage = customweaponengine.CalculateDamageFromCustomWeapon(plugin, player2, CustomWeapons.size, CustomWeapons.list, event);
				event.setDamage(damage + weapondamage);
				

				
        			
		}
		else if(event.getDamager() instanceof Arrow && event.getCause() == DamageCause.PROJECTILE
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().getName()) != null) //check to see if active in config
		{ //ARROW EVENT
            final Arrow arrow = (Arrow) event.getDamager();
 
            	int weapondamage =0;
            	int damage =combatengine.calculateProjectileAttackEvent(arrow, event.getEntity(), event);
            	if(arrow.getShooter() instanceof Player)
            	{
				weapondamage = customweaponengine.CalculateDamageFromCustomWeapon(plugin, (Player)arrow.getShooter(), CustomWeapons.size, CustomWeapons.list, event);
            	}
            	else
            	{
            		weapondamage = 0;
            	}
				event.setDamage(damage + weapondamage);

		} 


		return;
	
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onFallDamage(EntityDamageEvent event)
	{
		if(event.getCause() == DamageCause.FALL && event.getEntity() instanceof Player
				&& (RpgSystem.WorldList.get(event.getEntity().getWorld().getName().toString()) != null))//check to see if active world
		{
			Player player = (Player) event.getEntity();
			//PlayerData pd = new PlayerData(player.getName());
			PlayerData pd = PlayerJoinListener.users.get(player.getName());
			int defense = pd.getData().getInt("Defense");
			Double damage = event.getDamage();
			double random = Math.random() * 100 + 1;
			
			if(random < defense)
			{
				event.setDamage(0);
				player.sendMessage(ChatColor.GREEN + "You successfully avoid fall damage!");
			}
			else
			{
				
			}
			
			
		}
		
		
	}
		
	@EventHandler(priority = EventPriority.HIGHEST)//was lowest
	public void onMobspawn(CreatureSpawnEvent event)
	{
		
		if(RpgSystem.WorldList.get(event.getEntity().getWorld().getName().toString()) != null) //check to see if rpg world
		{	
			
			
		
		
			Entity monster = event.getEntity();
			if (monster instanceof Monster)
			{	
			Location monsterlocation = monster.getLocation().subtract(0, -1, 0);
			if(monsterlocation.getBlock().getType() == Material.WOOD
				|| monsterlocation.getBlock().getType().getId() == 43
				||monsterlocation.getBlock().getType() == Material.WOOD_STAIRS
				||monsterlocation.getBlock().getType() == Material.BRICK
				||monsterlocation.getBlock().getType() == Material.BRICK_STAIRS
				||monsterlocation.getBlock().getType() == Material.DARK_OAK_STAIRS
				||monsterlocation.getBlock().getType() == Material.ACACIA_STAIRS
				||monsterlocation.getBlock().getType() == Material.WOOL
				||monsterlocation.getBlock().getType().name().contains("WOOD")
				||monsterlocation.getBlock().getType().name().contains("BRICK")
				||monsterlocation.getBlock().getType().name().contains("STAIR"))
			{
				event.setCancelled(true);
				
			}
			setMonsterLevel(monsterlocation.getX(),monsterlocation.getY(), monsterlocation.getZ(),monster);
			
			String mobname = event.getEntity().getType().toString();
			outsidemobname = mobname;
			//String formatter = String.format("%1$.2f", getMonsterLevel(event.getEntity()));
			String formatter = String.format("%1$.0f", getMonsterLevel(event.getEntity()));
			//String mobnamelevel = mobname + " " + getMonsterLevel();
			double d = Math.random();
			double e = Math.random();
			if (d > .1)
			{
			String mobnamelevel = mobname + " " + "lv: " + formatter;
			//System.out.println(mobnamelevel);
			if(API.fastmobs == true)
			{
			event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 18000, 1));
			}
			event.getEntity().setCustomName(mobnamelevel);
			event.getEntity().setCustomNameVisible(true);
			//double health = event.getEntity().getMaxHealth();
			double health = 2.0;
			event.getEntity().setMetadata("level", new FixedMetadataValue(plugin, getMonsterLevel(event.getEntity())));
			event.getEntity().setMaxHealth(getMonsterLevel(event.getEntity()) * API.getMonsterHealthBonus()); //health formula is now lv *2
			double finalhealth = event.getEntity().getMaxHealth();
			event.getEntity().setHealth(finalhealth);	
			}
			else if(d < .1 && d > .02 && e > .5)
			{
				//NOTORIOUS MONSTER

				//String mobnamelevel = mobname + " " + getMonsterLevel();
				formatter = String.format("%1$.0f", getMonsterLevel(event.getEntity())+10);
				String mobnamelevel = ChatColor.YELLOW+ "Notorious " +mobname + " " + "lv: " + formatter;
				//System.out.println(mobnamelevel);
				if(API.fastmobs == true)
				{
				event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 18000, 1));
				}
				event.getEntity().setCustomName(mobnamelevel);
				event.getEntity().setCustomNameVisible(true);
				//double health = event.getEntity().getMaxHealth();
				double health = 2.0;
				event.getEntity().setMetadata("level", new FixedMetadataValue(plugin, getMonsterLevel(event.getEntity())+10));
				event.getEntity().setMaxHealth(health + getMonsterLevel(event.getEntity())+10);
				double finalhealth = event.getEntity().getMaxHealth();
				event.getEntity().setHealth(finalhealth);	
			}	
			else if(d < .02 && e < .26)
			{
				//1% chance KING OF THE LAND
				formatter = String.format("%1$.0f", getMonsterLevel(event.getEntity())+50);
				String mobnamelevel = ChatColor.RED+ "King " +mobname + " " + "lv: " + formatter;
				//System.out.println(mobnamelevel);
				if(API.fastmobs == true)
				{
				event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 18000, 1));
				}			
				event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 18000, 1));
				event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 18000, 1));
				event.getEntity().setCustomName(mobnamelevel);
				event.getEntity().setCustomNameVisible(true);
				//double health = event.getEntity().getMaxHealth();
				double health = 2.0;
				event.getEntity().setMetadata("level", new FixedMetadataValue(plugin, getMonsterLevel(event.getEntity())+50));
				event.getEntity().setMaxHealth(health + getMonsterLevel(event.getEntity())+50);
				double finalhealth = event.getEntity().getMaxHealth();
				event.getEntity().setHealth(finalhealth);
				
			}				
			}	
			}	
				
				
			
			
			
			
			//System.out.println(locationhashmap);
			
			
			
			
		//}	
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)//was lowest
	public void onMobDeath(final EntityDeathEvent event)
	{
		//event.getEntity().remove();
		if(event.getEntity().getLastDamageCause() == null ||
				event.getEntity().getLastDamageCause().getCause() == null ||
				event.getEntity().getLastDamageCause().getCause() == DamageCause.CONTACT
				&& (RpgSystem.WorldList.get(event.getEntity().getWorld().getName().toString()) != null)) //check to see if active world
		{
			return;//no exp or anything for contact damage so mob farms dont work
		}
		else
		{
			Location location = null;
			try{
				location = event.getEntity().getLocation();
			}
			catch(NullPointerException e)
			{
				System.out.println("Unable to get location of mob on death");
				return;
			}
		if (event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof Monster)
		{
	    	if(event.getEntity().getKiller().getPlayer().getGameMode() == GameMode.CREATIVE)
	    	{
	    		
	    		return; //no exp for creative!
	    	}
			final Entity monster = event.getEntity();
			double newexperiencedrop = event.getDroppedExp() + (getSpawnLevel(monster) * 2); //exp multiplier is 3 x the level
			event.setDroppedExp((int) newexperiencedrop);
			//start of exp hologram
			//FileConfiguration config = Reitzrpgmain.config;
			
			
			if(API.mobexpshow == true)
			{
			long time = new java.util.Date().getTime();
			final long timeend = new java.util.Date().getTime()+1500;
			try{
			final Hologram c = new Hologram(ChatColor.GREEN+"EXP: "+" +"+event.getDroppedExp());
			c.show(location);


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

				


			
			
			//end of exp hologram

			for (Player other : Bukkit.getOnlinePlayers())
			{
				  if (other.getLocation().distance(event.getEntity().getKiller().getLocation()) <= 50)
				   {
					  Player killer = event.getEntity().getKiller();
					  //System.out.println("the killer is : " + killer); //who killed it
					  String partyleader = PartySystem.inparty.get(killer.getName()); //get the party leader if they are in a party they should have a leader
					  //System.out.println("The party leader is: " + partyleader); //states the leader
					  PartyEngine party = PartySystem.partylist.get(partyleader); //the leader will have his party in the list
					  //System.out.println("The party is : " + party);
					  if(party != null)
					  {	  
						  List people = party.GetMembersLoop();
						  //System.out.println("Player is in a party");
						  
						 // System.out.println(people);
					  	for(Object peeps : people)
					  	{
					  		
					  		String name = (String) peeps;
					  		try//lets go crazy
					  		{
								Player person = Bukkit.getPlayer(name);
									int experience = event.getDroppedExp();
										//Player player = other;
					
									Integer combatexp = PlayerJoinListener.users.get(person.getName()).getData().getInt("Combat-EXP");
									int newtotal = experience + combatexp;
					
									PlayerJoinListener.users.get(person.getName()).getData().set("Combat-EXP", newtotal);
									ScoreboardStuff.manageScoreboard(person.getPlayer(), "TeamName"); 
					  		}
					  		catch(Error e)
					  		{
							
					  		}
					    }
						int experience = event.getDroppedExp();
						//Player player = other;
	
					Integer combatexp = PlayerJoinListener.users.get(partyleader).getData().getInt("Combat-EXP");
					int newtotal = experience + combatexp;
	
					PlayerJoinListener.users.get(partyleader).getData().set("Combat-EXP", newtotal);
					ScoreboardStuff.manageScoreboard(Bukkit.getPlayer(partyleader), "TeamName"); 
					  	
				    }
					  else
					  {
						     //System.out.println("Player is a lone shark!"); //this also checks that the player is indeed a player not npc
						  	for(Player playercheck : Bukkit.getOnlinePlayers())
						  	{
						  		if(playercheck == killer)
						  		{
						  			System.out.println("A real player is getting exp");
						  			Integer combatexp = PlayerJoinListener.users.get(event.getEntity().getKiller().getName()).getData().getInt("Combat-EXP");
						  			int experience = event.getDroppedExp();
						  			int newtotal = experience + combatexp;
			
						  			PlayerJoinListener.users.get(event.getEntity().getKiller().getName()).getData().set("Combat-EXP", newtotal);
						  			ScoreboardStuff.manageScoreboard(event.getEntity().getKiller(), "TeamName"); 
						  		}
						  		
						  	}
						  	
					  		}

						  
					  
				   }

					
				}
				
		}
		else
		{

			
		}
		
	 }
		
		if(event.getEntity().hasMetadata("Level"))
		{
			event.getEntity().removeMetadata("level", plugin);
		}
		else
		{
			return;
		}	
		
	}	


}
