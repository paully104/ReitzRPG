package com.paulreitz.reitzrpg;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
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


public class Magic implements Listener {

	File magicskills = Reitzrpgmain.magicskills;
	YamlConfiguration skillsconfig = YamlConfiguration.loadConfiguration(magicskills);
	
	@SuppressWarnings("rawtypes")
	HashMap<String, ArrayList> hashmap = new HashMap<String, ArrayList>();
	HashMap<String, Long> lastattack = new HashMap<String, Long>();
	
	Reitzrpgmain plugin;
    public Magic(Reitzrpgmain instance) {
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
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
   
        if (entity.getType() == EntityType.ARROW) {
          //entity.setVelocity(new Vector(0,0,0));
          entity.setMetadata("Remove", new FixedMetadataValue(plugin, true));
        }
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onmagicdamageevent(EntityDamageByEntityEvent event)
	{
		
		if(event.getDamager() instanceof Arrow && event.getEntity() instanceof Monster)
		{
			final Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() instanceof Player)
			{
			Player player = (Player) arrow.getShooter();
			String name = player.getName();
			PlayerData pd = PlayerJoinListener.users.get(name);
			if(player == event.getEntity())
			{
				event.setCancelled(true);
				return;//if they shoot themselves lol
			}
			if(player.getItemInHand().getType() == Material.STICK && event.getCause() == DamageCause.PROJECTILE)
			{
				
				int magicattack = pd.getData().getInt("Magic");
				if(magicattack- DistanceLevel.getMonsterLevel(event.getEntity()) > 1)
				{
					System.out.println("This event is happening" + magicattack + "" + DistanceLevel.getMonsterLevel(event.getEntity()));
					event.setDamage(magicattack- DistanceLevel.getMonsterLevel(event.getEntity()));
					return;
				}
				else
				{
				event.setDamage(1);
				}
			}
			else
			{
				return;
			}
			}
			
		}
		//magic pvp stuff
		else if(event.getDamager() instanceof Arrow && event.getEntity() instanceof Player)
		{
			final Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() instanceof Player)
			{
			Player player = (Player) arrow.getShooter();
			Player target = (Player) event.getEntity();
			String name = player.getName();
			String targetname = target.getName();
			PlayerData pd = PlayerJoinListener.users.get(name);
			PlayerData pd2 = PlayerJoinListener.users.get(targetname);
			if(player == event.getEntity())
			{
				event.setCancelled(true);
				return;//if they shoot themselves lol
			}
			if(player.getItemInHand().getType() == Material.STICK && event.getCause() == DamageCause.PROJECTILE)
			{
			int magicattack = pd.getData().getInt("Magic");
			int magicdefense = pd2.getData().getInt("Defense");
			if(magicattack - magicdefense > 1)
			{
			event.setDamage(magicattack- magicdefense);
			}
			else
			{
				event.setDamage(1);
			}
			}
			else
			{
				return;
			}
			}
			
		}
		
		
		
	}
	//COMBO SYSTEM
    public void ComboSystem(String name,final Player player)
    {
    	//fire spell
    	String[] firearray = {"Right","Left","Left","Left"};
    	List<String> firelist =new ArrayList<String>(Arrays.asList(firearray));
    	
    	//tornado combo
		String[] myStringArray = {"Right","Left","Left","Right"};
		List<String> stringList =new ArrayList<String>(Arrays.asList(myStringArray));
		//teleport como
		String[] teleportcombo = {"Right","Right","Right","Right"};
		List<String> teleportlist =new ArrayList<String>(Arrays.asList(teleportcombo));
		
		//lightning aoe
		String[] lightning_aoe = {"Right","Left","Right","Right","Right"};
		List<String> lightningList =new ArrayList<String>(Arrays.asList(lightning_aoe));
		
		PlayerData pd = PlayerJoinListener.users.get(name);
		int magic = pd.getData().getInt("Magic");
		if(hashmap.get(name).equals(stringList) && magic >= skillsconfig.getInt("Tornado-Level") 
				&& player.getFoodLevel() >= 3 && skillsconfig.getBoolean("Tornado")== true)
		{
			 Vector vec = new Vector(2, 2, 2);
			 Block target = player.getTargetBlock(null, 100);
			 Location location = target.getLocation();
			
			Tornado.spawnTornado(plugin, location, Material.WEB, (byte) 0, vec, 0.0, 250, (long) 15*15 , false, false);
			player.setFoodLevel(player.getFoodLevel()-3);
			hashmap.remove(name);
			
		}
		else if(hashmap.get(name).equals(teleportlist) && magic >= skillsconfig.getInt("Teleport-Level") 
				&& player.getFoodLevel() >= 1 && skillsconfig.getBoolean("Teleport")==true)
		{
			 Block target = player.getTargetBlock(null, 25);
			 Location location = target.getLocation();
			 player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			 player.teleport(location);
			 player.setFoodLevel(player.getFoodLevel()-1);
			hashmap.remove(name);
			
		}
		else if(hashmap.get(name).equals(lightningList) && magic >= skillsconfig.getInt("Lightning-Escape-Level")
				&& player.getFoodLevel() >= 1 && skillsconfig.getBoolean("Lightning-Escape")== true)
		{
			player.setNoDamageTicks(1200);
			
			new BukkitRunnable(){
			
			@Override
			public void run(){

			if(player.getFoodLevel() > 5)
			{
				
				List<Block> blocks = Magic.getNearbyBlocks(player.getLocation(), 3);
				for(Block block2 : blocks)
				{
					if(block2.getLocation() != player.getLocation())
					{
										
					player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,1200,1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1200,1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,1200,1));
					
					player.getWorld().strikeLightning(block2.getLocation());
					}
					
					
				}
				player.setFoodLevel(player.getFoodLevel()-4);
			
			}
			
			else
			{
				hashmap.remove(player.getName());
				this.cancel();
				
			}
			}

			}.runTaskTimer(plugin, 0L, 1L);
			
			
			
			//end of crazy
			
		}
		else if(hashmap.get(name).equals(firelist) && magic >= skillsconfig.getInt("Fire-Level")
				&& skillsconfig.getBoolean("Fire")==true)
		{
			//logic for a fire spell
			new BukkitRunnable(){
				
			@Override
			public void run(){

			if(player.getFoodLevel() > 3)
			{
				
				FallingBlock fire = player.getWorld().spawnFallingBlock(player.getEyeLocation().add(player.getLocation().getDirection().normalize()), Material.FIRE, (byte)0);
				fire.setVelocity(player.getLocation().getDirection().multiply(2));
				player.setFoodLevel(player.getFoodLevel()-1);
					
			
			}
			
			else
			{
				hashmap.remove(player.getName());
				this.cancel();
				
			}
			}

			}.runTaskTimer(plugin, 0L, 1L);
			

		}
		else if(hashmap.get(name).size() > 5)
		{
			hashmap.remove(player.getName());
		}
    	
    	
    }	
    @EventHandler
    public void notastickevent(PlayerItemHeldEvent event)
    {
    	if(!event.getPlayer().getItemInHand().equals(Material.STICK))
    	{
    		hashmap.remove(event.getPlayer().getName());
    		
    	}
    	
    }
	
    @EventHandler
    public void magicuserdisconnect(PlayerQuitEvent event)
    {
    	lastattack.remove(event.getPlayer().getName());
    	hashmap.remove(event.getPlayer().getName());
    }
    @EventHandler
    public void magicuserkickdisconnect(PlayerKickEvent event)
    {
    	lastattack.remove(event.getPlayer().getName());
    	hashmap.remove(event.getPlayer().getName());
    }
    
	@EventHandler
	public void magicstickhandler(PlayerInteractEvent event)
	{
		
		
		
		Player player = event.getPlayer();
		String name = player.getName();
		if(player.getItemInHand().getType() == Material.STICK)
		{
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				long time = new java.util.Date().getTime();
				if(lastattack.get(name) == null || (lastattack.get(name) + 1000) <=  time)
				{
					lastattack.put(name, new java.util.Date().getTime());
				ArrayList<String> list = hashmap.get(name);
				if(list == null)
				{
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add("Right");
					hashmap.put(name, list2);
					
				}	
				else
				{
				list.add("Right");
				hashmap.put(name,list);
				
				}

				final Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
				arrow.setShooter(((LivingEntity) player));
				arrow.setVelocity(player.getEyeLocation().getDirection().multiply(2));
				


				new BukkitRunnable(){

				@Override
				public void run(){

				if(!(arrow.isOnGround()) || arrow.isDead() == false || arrow.isValid() == true){
					//going full retard
										
					//end of retard

				arrow.getWorld().playEffect(arrow.getLocation(), Effect.STEP_SOUND, Material.ANVIL);
				} 
				else if(arrow.isOnGround() || arrow.isValid() == false || arrow.isDead() == true
				|| arrow.isEmpty() == true || arrow == null){
					arrow.removeMetadata("Remove",plugin);
					this.cancel();
				}
				if(arrow.getVelocity().getX() == 0 && arrow.getVelocity().getY() == 0
						|| arrow.getVelocity().getZ() == 0)
				{arrow.removeMetadata("Remove",plugin);
					
					this.cancel();
				}
				if(arrow.getVelocity() == null || removeArrow(arrow) == true)
				{
					arrow.removeMetadata("Remove",plugin);
					this.cancel();
				}
				}

				}.runTaskTimer(plugin, 0L, 1L);
				ComboSystem(name,player);
				
			}	
				else
				{
					event.setCancelled(true);
				}
			}	
			if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			{
					long time = new java.util.Date().getTime();
				if(lastattack.get(name) == null || (lastattack.get(name) + 1000) <=  time)
				{
					lastattack.put(name, new java.util.Date().getTime());
				ArrayList<String> list = hashmap.get(name);
				if(list == null)
				{
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add("Left");
					hashmap.put(name, list2);
					
				}	
				else
				{
				list.add("Left");
				hashmap.put(name,list);
				
				}

				final Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
				arrow.setShooter(((LivingEntity) player));
				arrow.setVelocity(player.getEyeLocation().getDirection().multiply(2));
				

				new BukkitRunnable(){

				@Override
				public void run(){

				if(!(arrow.isOnGround()) || arrow.isDead() == false || arrow.isValid() == true)
				{
				arrow.getWorld().playEffect(arrow.getLocation(), Effect.STEP_SOUND, Material.WOOL);
				} 
				else if(arrow.isOnGround() || arrow.isValid() == false || arrow.isDead() == true
				|| arrow.isEmpty() == true || arrow == null){
				arrow.removeMetadata("Remove",plugin);
				this.cancel();
				}
				if(arrow.getVelocity().getX() == 0 && arrow.getVelocity().getY() == 0
						|| arrow.getVelocity().getZ() == 0)
				{
					arrow.removeMetadata("Remove",plugin);
					this.cancel();
				}
				if(arrow.getVelocity() == null || removeArrow(arrow) == true)
				{
					arrow.removeMetadata("Remove",plugin);
					this.cancel();
					
				}
				}

				}.runTaskTimer(plugin, 0L, 1L);
				ComboSystem(name,player);
				
			}
				else
				{
					event.setCancelled(true);
				}
					
			}
		}	
		else
		{
			ArrayList arraylist = hashmap.get(name);
			if(arraylist != null)
			{
			
			if(arraylist.size() > 0)
					{
						hashmap.remove(player.getName());
				
					}
			}
			
		}	
				
		
		
	}	
}
