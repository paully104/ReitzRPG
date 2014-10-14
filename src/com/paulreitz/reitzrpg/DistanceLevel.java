
package com.paulreitz.reitzrpg;

import java.util.HashMap;
import java.util.List;

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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class DistanceLevel implements Listener {
	Reitzrpgmain plugin;  
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
		//Double worldstart = Reitzrpgmain.config.getDouble(world+".Level");
		Integer worldstart = RpgSystem.WorldList.get(world);
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
	        	
	            return val.asDouble();
	        }
	    }
	    //System.out.println("something went wrong?");
	    return 0;    
	}

	
	@EventHandler(ignoreCancelled = true,priority=EventPriority.HIGHEST) //was highest
	public void onDamage(EntityDamageByEntityEvent event)
	{
		if (event.isCancelled()) { return; }
	
		//If a monster is attacking a player
		if (event.getDamager() instanceof Monster && event.getEntity() instanceof Player && 
				RpgSystem.WorldList.get(event.getDamager().getWorld().toString()) != null) //added in this to see if the world is active
		{	
				//MONSTER IS ATTACKING PLAYER
				Entity player = event.getEntity();
				Player player2 = (Player) player;
				PlayerData pd = new PlayerData(player2.getName());
				int defence = pd.getData().getInt("Defense");
				int defencemodifier = API.defensemod;
				Entity monster = event.getDamager();
				//String formatter = String.format("%1$.2f", getMonsterLevel(event.getDamager()));
				String formatter = String.format("%1$.0f", getMonsterLevel(event.getDamager()));
				Double.parseDouble(formatter);
				//double monsterdamage = event.getDamage() +  getSpawnLevel(monster);
				double monsterdamage = getSpawnLevel(monster);

				
					if(event.getDamager().getType().name().equalsIgnoreCase("Creeper"))
					{
						double creeperdamage = (monsterdamage*2.5)-(defence/defencemodifier);
						if(creeperdamage < 2.00)
						{
							event.setDamage(2.00);
							return;
						}	
						else
						{
							event.setDamage(creeperdamage);
							return;
						}	
						
					}
					else if(event.getDamager().getType().name().equalsIgnoreCase("Zombie"))
					{
						double zombiedamage = (monsterdamage)-(defence/defencemodifier);
						if(zombiedamage < 1.00)
						{
							event.setDamage(1.00);
							return;
						}	
						else
						{
							event.setDamage(zombiedamage);
							return;
						}	
						
					}
					else if(event.getDamager().getType().name().equalsIgnoreCase("Skeleton"))
					{
						double creeperdamage = (monsterdamage)-(defence/defencemodifier);
						if(creeperdamage < 1.00)
						{
							event.setDamage(1);
							return;
						}	
						else
						{
							event.setDamage((monsterdamage)-(defence/defencemodifier));
							return;
						}
					}	
						else if(event.getDamager().getType().name().equalsIgnoreCase("Spider"))
						{
							double creeperdamage = (monsterdamage)-(defence/defencemodifier);
							if(creeperdamage < 1.00)
							{
								event.setDamage(1);
								return;
							}	
							else
							{
								event.setDamage((monsterdamage)-(defence/defencemodifier));
								return;
							}
						
						
						
						}
						else if(event.getDamager().getType().name().equalsIgnoreCase("Pigman"))
						{
							double creeperdamage = (monsterdamage)-(defence/defencemodifier);
							if(creeperdamage < 1.00)
							{
								event.setDamage(1);
								return;
							}	
							else
							{
								event.setDamage((monsterdamage)-(defence/defencemodifier));
								return;
							}
						
						
						
						}
					else if(event.getDamager().getType().name().equalsIgnoreCase("Enderman"))
					{
						double enderdamage = (monsterdamage*2)-(defence/defencemodifier);
						if (enderdamage < 1)
						{
							event.setDamage(1);
							return;
						}	
						else
						{
							event.setDamage(enderdamage);
							return;
						}	
					}	
					else
					{
						if(monsterdamage < 1.0)
						{
						event.setDamage(1);//higher modifier more damage taken 
						return;
						}
						else
						{
							event.setDamage((monsterdamage) - (defence/defencemodifier));
							return;
						}	
						
					}	
					
				
        }
		else if (event.getDamager() instanceof Player && event.getEntity() instanceof Monster
				&& !(event.getDamager() instanceof Arrow) && event.getCause() != DamageCause.PROJECTILE
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().toString()) != null) //check to see if world is active in config
		{
			
				//PLAYER ATTACKING MONSTER not using an arrow
				Entity player = event.getDamager();
				Player player2 = (Player) player;
				PlayerData pd = PlayerJoinListener.users.get(player2.getName());
				int attack = pd.getData().getInt("Attack");
				int attackmodifier = API.attackmod;
				Entity monster = event.getEntity();
				int newdamage = attack/attackmodifier;
				int newdamage2 = (int) (newdamage - getSpawnLevel(monster));
				if(newdamage2 < 1)
				{
					event.setDamage(1);
					return;
				}
				if(newdamage2 > 1 && player2.getItemInHand() == null)
				{
					event.setDamage(newdamage2/2);
					return;
				}
				else
				{
					
					event.setDamage(newdamage2);
					return;
				}
				
				
        			
		}
		else if(event.getDamager() instanceof Arrow && !(event.getEntity() instanceof Player)
				&& event.getEntity() instanceof Monster && event.getCause() == DamageCause.PROJECTILE
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().toString()) != null) //check to see if active in config
		{ //Monster getting shot by an arrow by a player
            final Arrow arrow = (Arrow) event.getDamager();
 
            if(arrow.getShooter() instanceof Player)
            {
            	
            	int attackmodifier = API.attackmod;
            	Player player = (Player) arrow.getShooter();
            	if(player.getItemInHand().getType() == Material.BOW)
            	{
            		//PlayerData pd = new PlayerData(player.getName()); old system
            		PlayerData pd = PlayerJoinListener.users.get(player.getName());
            	if((pd.getData().getInt("Archery")/attackmodifier)+1- getSpawnLevel(event.getEntity()) > 1)
            			{
            	event.setDamage((pd.getData().getInt("Archery")/attackmodifier)+1- getSpawnLevel(event.getEntity()));
            	return;
            			}
            	else
            		{
            		event.setDamage(1);
            		return;
            		}
            	}
            	else
            	{
            		return;
            	}
            	
            }
            
		}
		else if(event.getDamager() instanceof Arrow && (event.getEntity() instanceof Player) && event.getCause() == 
				DamageCause.PROJECTILE
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().toString()) != null) //check to see if world is active
		{
			final Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() instanceof Player && event.getEntity() instanceof Player)
			{
				//player shooting player with a bow.
			int attackmodifier = API.attackmod;
        	Player player = (Player) arrow.getShooter();//person who is shooting the bow
        	Player target = (Player) event.getEntity(); //person who is getting shot
        	PlayerData pd = PlayerJoinListener.users.get(player.getName());//changed from old way
        	PlayerData pd2 = PlayerJoinListener.users.get(target.getName());
        	if(player.getItemInHand().getType() == Material.BOW)
        	{
        	int damage = pd.getData().getInt("Archery");
        	int defense = pd2.getData().getInt("Defense");
        	if((damage - defense) > 1)
        	{
        	event.setDamage(damage - defense);
        	return;
        	}
        	else
        		event.setDamage(1);
        	return;
        	
			}
        	else
        	{
        		return;
        	}
			}
			return;
			
			
		}
		else if(event.getDamager() instanceof Player && event.getEntity() instanceof Player
				&& event.getCause() == DamageCause.ENTITY_ATTACK && event.getCause() != DamageCause.PROJECTILE
				&& RpgSystem.WorldList.get(event.getDamager().getWorld().toString()) != null)
		{
			
			Player damager = (Player) event.getDamager();
			Player target = (Player) event.getEntity();
        	//PlayerData pd = new PlayerData(damager.getName());
        	//PlayerData pd2 = new PlayerData(target.getName());
			PlayerData pd = PlayerJoinListener.users.get(damager.getName());
			PlayerData pd2 = PlayerJoinListener.users.get(target.getName());
        	int damage = pd.getData().getInt("Attack");
        	int armor = pd.getData().getInt("Defense");
        	if((damage - armor) > 1)
        	{
        		event.setDamage(damage-armor);
        	}
        	else
        	{
        		event.setDamage(1);
        	}
			
			
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
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMobDeath(final EntityDeathEvent event)
	{
		if(event.getEntity().getLastDamageCause() == null ||
				event.getEntity().getLastDamageCause().getCause() == null ||
				event.getEntity().getLastDamageCause().getCause() == DamageCause.CONTACT
				&& (RpgSystem.WorldList.get(event.getEntity().getWorld().getName().toString()) != null)) //check to see if active world
		{
			return;//no exp or anything for contact damage so mob farms dont work
		}
		Location location = event.getEntity().getLocation();
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
			
			
			
			
			java.util.List<Entity> nearby = event.getEntity().getNearbyEntities(25,25,25);
			for (Entity entity : nearby)
			{
				if (entity instanceof Player)
				{
					
					String leader = PartySystem.partyleader.get(event.getEntity().getKiller().getName());
					List followers = PartySystem.partylist.get(leader);
					//Player p = event.getEntity().getKiller();
					Player p = (Player) entity;
			    	if(p.getGameMode() == GameMode.CREATIVE)
			    	{
			    		return; //no exp for creative!
			    	}
			    	//if leader is null they have no party leader they are solo
			    	//if followers is null it means once again they are solo
			    	if(leader == null || followers == null || followers.contains(p.getName()))
			    	{
			    	
			    		//PlayerData pd = new PlayerData(p.getName());
			    		PlayerData pd = PlayerJoinListener.users.get(p.getName());
			    		int player_experience = pd.getData().getInt("Combat-EXP");
			    		int newexp_total = (int) (newexperiencedrop + player_experience);
			    		//pd.getData().set("Combat-EXP", newexp_total);
			    		PlayerJoinListener.users.get(p.getName()).getData().set("Combat-EXP", newexp_total);
			    		//pd.save();
			    		ScoreboardStuff.manageScoreboard(p, "TeamName");//update exp?
			    		event.getEntity().removeMetadata("level", plugin);
						if(event.getEntity().getCustomName() != null &&
							event.getEntity().getCustomName().contains("King"))
							{
								
								p.getInventory().addItem(new ItemStack(Material.DIAMOND));
								p.getInventory().addItem(new ItemStack(Material.DIAMOND));
								p.getInventory().addItem(new ItemStack(Material.DIAMOND));
						
						
							}
						
			    	}
				}	
			    	
			    	
			}
		}
		if(event.getEntity().getKiller() instanceof Player &&
				event.getEntity().getLastDamageCause().getCause() == DamageCause.PROJECTILE
				&& (RpgSystem.WorldList.get(event.getEntity().getWorld().getName().toString()) != null))
		{
			if(event.getEntity() instanceof Monster)
			{
			Entity monster = event.getEntity();
			double newexperiencedrop = event.getDroppedExp() + (getSpawnLevel(monster) * 3); //exp multiplier is 3 x the level
			event.setDroppedExp((int) newexperiencedrop);

			Player player = (Player)event.getEntity().getKiller();
			PlayerData pd = PlayerJoinListener.users.get(player.getName());
			int player_experience = pd.getData().getInt("Combat-EXP");
			int newexp_total = (int) (newexperiencedrop + player_experience);
			//pd.getData().set("Combat-EXP", newexp_total);
			PlayerJoinListener.users.get(player.getName()).getData().set("Combat-EXP", newexp_total);
			//pd.save();
			ScoreboardStuff.manageScoreboard(player, "TeamName");//update exp?
			
			}
			
			
			
		}
		if(event.getEntity().getLastDamageCause().getCause() == DamageCause.BLOCK_EXPLOSION
				&& (RpgSystem.WorldList.get(event.getEntity().getWorld().getName().toString()) != null))
		{//tnt arrow exp
			
			java.util.List<Entity> nearby = event.getEntity().getNearbyEntities(25,25,25);
			for (Entity entity : nearby)
			{
				if (entity instanceof Player)
				{
					String leader = PartySystem.partyleader.get(event.getEntity().getKiller());
					List followers = PartySystem.partylist.get(leader);
					
					//Player p = event.getEntity().getKiller();
					Player p = (Player) entity;
			    	if(p.getGameMode() == GameMode.CREATIVE)
			    	{
			    		return; //no exp for creative!
			    	}
			    	if(leader == null || followers == null || followers.contains(p.getName()))
			    	{
					Entity monster = event.getEntity();
					double newexperiencedrop = event.getDroppedExp() + (getSpawnLevel(monster) * 2); //exp multiplier is 3 x the level
					event.setDroppedExp((int) newexperiencedrop);
					PlayerData pd = new PlayerData(p.getName());
					int player_experience = pd.getData().getInt("Combat-EXP");
					int newexp_total = (int) (newexperiencedrop + player_experience);
					//pd.getData().set("Combat-EXP", newexp_total);
					PlayerJoinListener.users.get(p.getName()).getData().set("Combat-EXP", newexp_total);
					//pd.save();
					ScoreboardStuff.manageScoreboard(p, "TeamName");//update exp?
					event.getEntity().removeMetadata("level", plugin);
					
			    	}
				}
			}
			
			
		}
		else if(event.getEntity().hasMetadata("Level"))
		{
			event.getEntity().removeMetadata("level", plugin);
		}
		

		
		
	}	


}
