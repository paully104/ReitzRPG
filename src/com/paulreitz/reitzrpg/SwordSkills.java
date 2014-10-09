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
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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


public class SwordSkills implements Listener {

	File skills = Reitzrpgmain.swordskills;
	YamlConfiguration skillsconfig = YamlConfiguration.loadConfiguration(skills);
	
	@SuppressWarnings("rawtypes")
	HashMap<String, ArrayList> skilllist = new HashMap<String, ArrayList>();

	
	Reitzrpgmain plugin;
    public SwordSkills(Reitzrpgmain instance) {
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

    	//SPIN ATTACK
    	String[] spinarray = {"Right","Left","Left","Right"};
    	List<String> spinarraylist =new ArrayList<String>(Arrays.asList(spinarray));
    	
    	
    	//RILEY ATTACK
    	String[] rileyarray = {"Right","Right","Right","Right"};
    	List<String> rileyarraylist =new ArrayList<String>(Arrays.asList(rileyarray));
		
    	PlayerData pd = PlayerJoinListener.users.get(name);
		int attack = pd.getData().getInt("Attack");
		if(skilllist.get(name).equals(spinarraylist) && attack >= skillsconfig.getInt("Spin-Attack-Level") 
				&& skillsconfig.getBoolean("Spin-Attack") == true)
		{
			player.sendMessage(ChatColor.GREEN+ name + ChatColor.GRAY+" uses SPIN");
			 Vector vec = new Vector(2, 2, 2);
			 Block target = player.getTargetBlock(null, 10);
			 List<Entity> enemies = player.getNearbyEntities(10, 10, 10);
			 for(Entity monster : enemies)
			 {
				 if(monster instanceof Monster)
				 {
					 Location l = monster.getLocation().subtract(player.getLocation());
				        double distance = monster.getLocation().distance(player.getLocation());
				        Vector v = l.toVector().multiply(2/distance);
				        v.multiply(3);
				        monster.setVelocity(v);
				      //or else no one would hear it
				      //10 is the volume so this is loud enough to hear
				      //1 is the pitch. i wouldnt change it.
				          player.playSound(monster.getLocation(), Sound.ANVIL_BREAK, 10, 1);
				          player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 5));
				          skilllist.remove(name);
				          
				 }
				 
			 }
			 
			
			
		}
		else if(skilllist.get(name).equals(rileyarraylist) && 
				attack >= skillsconfig.getInt("Yelir-Level") && skillsconfig.getBoolean("Yelir") == true)
				{
			
			final long time = new java.util.Date().getTime();
			final long time2 = time + 10000;	
			new BukkitRunnable(){
				
			@Override
			public void run(){

			if(time < time2)
			{
				//default is .01F
				player.setWalkSpeed(.5F);
				long time3 = new java.util.Date().getTime();
				if(time2 > time3)
				{
				player.playSound(player.getLocation(), Sound.AMBIENCE_CAVE, 10, 1);
				
				//player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
				//player.playSound(player.getLocation(), Sound.HORSE_ZOMBIE_DEATH, 10, 1);
				//player.playSound(player.getLocation(), Sound.GLASS, 10, 1);
				//player.playSound(player.getLocation(), Sound.GHAST_SCREAM2, 10, 1);
				World world = player.getWorld();
				Location loc = player.getLocation();
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
				        behind = world.getBlockAt(behind.getX() + 2, behind.getY(), behind.getZ());

				        
				        break;
				    case 2:
				        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() + 2);

				        
				        
				        break;
				    case 3:
				        behind = world.getBlockAt(behind.getX() - 2, behind.getY(), behind.getZ());

				        
				        
				        break;
				    case 4:
				        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - 2);

				       
				        break;
				    case 0:
				        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - 2);

				        break;
				    default:
				        break;
				}
				
				}
				else
				{
					
					skilllist.remove(player.getName());
					player.setWalkSpeed(.2F);
					this.cancel();
				}
				
			
			}
			
			else
			{
				skilllist.remove(player.getName());
				this.cancel();
				
			}
			}

			}.runTaskTimer(plugin, 0L, 1L);
				
				
				}
		if(skilllist.get(name) == null)
		{
			
		}
		else if(skilllist.get(name).size() > 5)
		{
			skilllist.remove(name);
		}

    	
    	
    }	
    @EventHandler
    public void notastickevent(PlayerItemHeldEvent event)
    {
    	if(!event.getPlayer().getItemInHand().equals(Material.STICK))
    	{
    		skilllist.remove(event.getPlayer().getName());
    		
    	}
    	
    }
	
    @EventHandler
    public void magicuserdisconnect(PlayerQuitEvent event)
    {
    	
    	skilllist.remove(event.getPlayer().getName());
    }
    @EventHandler
    public void magicuserkickdisconnect(PlayerKickEvent event)
    {
    
    	skilllist.remove(event.getPlayer().getName());
    }
    
	@EventHandler
	public void magicstickhandler(PlayerInteractEvent event)
	{
		
		
		
		Player player = event.getPlayer();
		String name = player.getName();

		if(player.getItemInHand().getType().name().equalsIgnoreCase("WOOD_SWORD")
				|| player.getItemInHand().getType().name().equalsIgnoreCase("IRON_SWORD")
				|| player.getItemInHand().getType().name().equalsIgnoreCase("GOLD_SWORD")
				|| player.getItemInHand().getType().name().equalsIgnoreCase("DIAMOND_SWORD")
				|| player.getItemInHand().getTypeId() == 267
				|| player.getItemInHand().getTypeId() == 268
				|| player.getItemInHand().getTypeId() == 272
				|| player.getItemInHand().getTypeId() == 276
				|| player.getItemInHand().getTypeId() == 283)
				

  {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				


				ArrayList<String> list = skilllist.get(name);
				if(list == null)
				{
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add("Right");
					skilllist.put(name, list2);
					
				}	
				else
				{
				list.add("Right");
				skilllist.put(name,list);
				
				}
				ComboSystem(name,player);

			}
				
			if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			{
					long time = new java.util.Date().getTime();

				ArrayList<String> list = skilllist.get(name);
				if(list == null)
				{
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add("Left");
					skilllist.put(name, list2);
					
				}	
				else
				{
				list.add("Left");
				skilllist.put(name,list);
				
				}

				ComboSystem(name,player);
				
			}

					
			
		}	
		else
		{
			ArrayList arraylist = skilllist.get(name);
			if(arraylist != null)
			{
			
			if(arraylist.size() > 0)
					{
						skilllist.remove(player.getName());
				
					}
			}
			
		}	
				
		
		
	}	
}
