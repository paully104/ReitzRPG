package com.paulreitz.reitzrpg;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Reitzrpgmain extends JavaPlugin implements Listener{


	public static FileConfiguration config;
	public static YamlConfiguration loot_table;
	public static File f;
	public static File swordskills;
	public static File bowskills;
	public static File magicskills;
	public static File worlds_config;
	public static File custom_armors;
	private static Plugin plugin;	
	public void onEnable()
	
	{
		this.getCommand("Party").setExecutor(new PartySystem(this));
		
		this.getCommand("RpgSystem").setExecutor(new RpgSystem(this));
		
		
		
		try {
		    MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		plugin = this; //registers the plugin
		config = getConfig();
		Config.MakeConfig();
		
		API.setFastMobs(this);
		API.setBackpackEnabled();
		API.setMobExpShow();
		API.setBlockExpShow();
		API.setAttackModifier(this);
		API.setDefenseModifier(this);
		API.setDistanceToMobDamageIncrease(this);
		API.setConfiguration(config);
		//custom yml test
		f = new File("plugins/ReitzRPG/Loot_Table.yml"); //$NON-NLS-1$
		swordskills = new File("plugins/ReitzRPG/Sword-Skills_config.yml"); //$NON-NLS-1$
		bowskills = new File("plugins/ReitzRPG/Bow-Skills_config.yml"); //$NON-NLS-1$
		magicskills = new File("plugins/ReitzRPG/Magic-Skills_config.yml");
		worlds_config = new File("plugins/ReitzRPG/Worlds_Config.yml");//$NON-NLS-1$
		custom_armors = new File("plugins/ReitzRPG/Custom_Armors.yml");
		LootTables.LootTableDefault();
		CustomArmors.Create_File();//create the armor files
		FileConfiguration customConfig = YamlConfiguration.loadConfiguration(custom_armors); //load the config
		API.SetArmorConfiguration(customConfig); //set the config
		SwordSkillsConfig.DefaultConfig();
		BowSkillsConfig.DefaultConfig();
		MagicSkillsConfig.AddDefault();
		RpgSystem.Worlds_Config();

		
		//end of custom yml test
		
		
		registerEvents(this, new PlayerJoinListener(),new EntityShootBowListener(),new OnProjectileHitListener(), 
		new OnPlayerUseWeaponsListener(this),new InventoryClickEventListener(),new myCustomInventory(),new PlayerBackPack(this),
		new OnPlayerUseGatheringListener(this)/*,new ChunkUnloadListener(this)*/,new OnBlockBreakMiningListener(this),
		new OnBlockBreakWoodcuttingListener(this), new WalljumpHandler(), new GrapplingHook(), new PlayerTrading(this),
		new PlayerFishEventListener(), new OnBlockBreakDiggingListener(this), new LockDoors(this), new BlockRightClick(),
		new CustomWeapons(this), new RespawningChest(this), new Magic(this), new SwordSkills(this), new CostDoors(this),
		new BowSkills(this), new LootTables(this), new Config(this), new PartySystem(this), new RpgSystem(this),
		new CustomArmors(this));
		
		getLogger().info("ReitzRPG is now enabled"); //$NON-NLS-1$
		PlayerData.setup(this);// calls to PlayerData and initializes the individual configurations
		getServer().getPluginManager().registerEvents(this, this);
		LockDoors.onlinepersistance();
		LockDoors.offlinepersistance();
		CustomRecipes.CustomRecipes();
		CustomWeapons.onEnableLoadWeapons();
		CustomArmors.onEnableLoadWeapons(); //use the config
		
		new ArrayList<String>();
		if(config.getBoolean("Monster-Levels") == true) //$NON-NLS-1$
		{
			registerEvents(this,new DistanceLevel(this));
		}
		else
		{
			registerEvents(this,new BasicEXP());
		}	
		for (Player player : Bukkit.getServer().getOnlinePlayers())
		{
			PlayerData pd = PlayerJoinListener.users.get(player.getName());
		    pd.getData().set("Name", player.getName()); // Use as normal configuration file //$NON-NLS-1$
		    //start of data
	    	Double health = pd.getData().getDouble("Health"); //$NON-NLS-1$
	    	Integer archery = pd.getData().getInt("Archery"); //$NON-NLS-1$
	    	Integer attack = pd.getData().getInt("Attack"); //$NON-NLS-1$
	    	Integer defense = pd.getData().getInt("Defense"); //$NON-NLS-1$
	    	Integer combatexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
	    	Integer magic = pd.getData().getInt("Magic"); //$NON-NLS-1$
	        Integer miningexp = pd.getData().getInt("Mining-EXP"); //$NON-NLS-1$
	        Integer woodcuttingexp = pd.getData().getInt("Woodcutting-EXP"); //$NON-NLS-1$b
	        Integer fishing =  pd.getData().getInt("Fishing"); //$NON-NLS-1$
	        Integer digging = pd.getData().getInt("Digging"); //$NON-NLS-1$
	        Integer diggingexp = pd.getData().getInt("Digging-EXP"); //$NON-NLS-1$
	        Integer fishingexp = pd.getData().getInt("Fishing-EXP"); //$NON-NLS-1$
	        Integer backpacksize = pd.getData().getInt("BackPack-Size"); //$NON-NLS-1$
	        Integer woodcutting = pd.getData().getInt("Woodcutting"); //$NON-NLS-1$
	        Integer mining = pd.getData().getInt("Mining"); //$NON-NLS-1$
	        String backpack = pd.getData().getString("BackPack"); //$NON-NLS-1$
	    	pd.getData().set("Name", player.getName()); //$NON-NLS-1$
	    	if(health == 0.0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Health", 20);
	    		player.setMaxHealth(20);
	    		//pd.save();
	    	}	
	    	else
	    	{
	    		
	    		player.setMaxHealth(health);
	    	}
	    	if(archery == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Archery", 1);	 //$NON-NLS-1$
	    	//pd.save();
	    	}	
	    	if(attack == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Attack", 1); //$NON-NLS-1$
	    		//pd.save();
	    	}	
	    	if(defense == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Defense", 1); //$NON-NLS-1$
	    		//pd.save();
	    		
	    	}
	    	if(combatexp == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Combat-EXP", 0); //$NON-NLS-1$
	    		//pd.save();
	    				
	    	}
	    	if(magic == 0)
	    	{
	    		
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Magic", 1); //$NON-NLS-1$
	    		//pd.save();
	    	}	
	    	if(miningexp == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Mining-EXP", 0); //$NON-NLS-1$
	    		//pd.save();
	    	}	
	    	if(woodcuttingexp == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Woodcutting-EXP", 0); //$NON-NLS-1$
	    		//pd.save();
	    		
	    	}	
	    	if(woodcutting == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Woodcutting", 1); //$NON-NLS-1$
	    		//pd.save();
	    		
	    	}	
	    	if(mining == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Mining", 1); //$NON-NLS-1$
	    		//pd.save();
	    		
	    	}		    	
	    	if(fishing == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Fishing", 1); //$NON-NLS-1$
	    		//pd.save();
	    		
	    	}	
	    	if(digging == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Digging", 1); //$NON-NLS-1$
	    		//pd.save();
	    	}	
	    	if(diggingexp == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Digging-EXP", 0); //$NON-NLS-1$
	    		//pd.save();
	    	}	
	    	if(fishingexp == 0)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("Fishing-EXP", 0); //$NON-NLS-1$
	    		//pd.save();
	    		
	    	}
	    	if(backpacksize == 0 || backpacksize == null)
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("BackPack-Size", 9); //$NON-NLS-1$
	    		PlayerJoinListener.users.get(player.getName()).getData().set("BackPack", "9;"); //$NON-NLS-1$ //$NON-NLS-2$
	    		//pd.save();
	    		
	    	}	
	    	if(backpack == null || backpack.equalsIgnoreCase("")) //$NON-NLS-1$
	    	{
	    		PlayerJoinListener.users.get(player.getName()).getData().set("BackPack", "9;"); //$NON-NLS-1$ //$NON-NLS-2$
	    		PlayerJoinListener.users.get(player.getName()).getData().set("BackPack-Size", 9); //$NON-NLS-1$
	    		//pd.save();
	    	}
	    	ScoreboardStuff.manageScoreboard(player, "TeamName"); //$NON-NLS-1$
			
		}
		
		//start of remove mobs on load
		try
		{
		List<World> worlds = Bukkit.getWorlds();
		System.out.println("Removing all existing monsters to set level data!");
		for(World w : worlds)
		{	
		List<Entity> entities = w.getEntities();
			for ( Entity entity : entities)
			{
					if(entity instanceof Monster)
						{
							entity.removeMetadata("level", this); //$NON-NLS-1$
							entity.remove();
						}
			}
		}	
		}
		catch(NullPointerException e)
		{
			System.out.println("ERROR: Unable to remove mobs!");
		}
		
		
		
	}//end of onEnable
	


	public void onDisable()
	{
		plugin = null; //to stop memory leaks
		getLogger().info("ReitzRPG is now disabled"); //$NON-NLS-1$
	}	
	//listener stuff
	//Much eaisier then registering events in 10 diffirent methods
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
	for (Listener listener : listeners) {
	Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	}
	}
	 
	//To access the plugin variable from other classes
	public static Plugin getPlugin() {
	return plugin;
	}		
	
	//start of command handler stuff
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args)
	{//start of the command handler
		YamlConfiguration swordskillsconfig = YamlConfiguration.loadConfiguration(swordskills);
	    YamlConfiguration bowskillsconfig = YamlConfiguration.loadConfiguration(bowskills);
		YamlConfiguration magicskillsconfig = YamlConfiguration.loadConfiguration(magicskills);
		final Player player = (Player) sender;
		String playername = player.getName();
		player.getDisplayName();
		//PlayerData pd = new PlayerData(playername); this might fix it.
		PlayerData pd = (PlayerJoinListener.users.get(player.getName()));
		if(cmd.getName().equalsIgnoreCase("ReitzRPG") || cmd.getName().equalsIgnoreCase("RPG")) //$NON-NLS-1$ //$NON-NLS-2$
	{
			if(args.length == 0)
			{
				player.sendMessage(ChatColor.GREEN +"[ReitzRPG]"); //$NON-NLS-1$
				player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.103")+ ChatColor.GRAY+ ".20"); //$NON-NLS-1$ //$NON-NLS-2$
				player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.102")+ ChatColor.GRAY+ "Paully104"); //$NON-NLS-1$ //$NON-NLS-2$
				player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.101")+ ChatColor.GRAY+ "/rpg help"); //$NON-NLS-1$ //$NON-NLS-2$
			}	
			else if(args.length == 1)
			{
				
				if(args[0].equalsIgnoreCase("Attack") || args[0].equalsIgnoreCase("ATT")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					//Where the /ReitzRPG Attack stuff happens
					int attack = pd.getData().getInt("Attack"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.100")+ ChatColor.RED+ attack);					 //$NON-NLS-1$
					int attackupgradecost;
					attackupgradecost = 25 * attack * 2;
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.99")+ ChatColor.RED+ attackupgradecost); //$NON-NLS-1$
					
					
					
				}	
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("helps")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					player.sendMessage(ChatColor.GREEN +Messages.getString(Messages.getString("Reitzrpgmain.108"))+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.109")); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN + Messages.getString("Reitzrpgmain.110") + ChatColor.GRAY + Messages.getString("Reitzrpgmain.111")); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.97")+ ChatColor.GRAY+ "/rpg backpack"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.96")+ ChatColor.GRAY+ "/rpg lock"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.95")+ ChatColor.GRAY+ "/rpg unlock"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.94")+ ChatColor.GRAY+ "/rpg sethome"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.92")+ ChatColor.GRAY+ "/rpg setspawn"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.93")+ ChatColor.GRAY+ "/rpg killmobs"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.91")+ ChatColor.GRAY+ "/rpg reload"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.90")+ ChatColor.GRAY+ "/rpg spells"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.89")+ ChatColor.GRAY+ "/rpg swordskills"); //$NON-NLS-1$ //$NON-NLS-2$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.88")+ ChatColor.GRAY+ "/rpg bowskills"); //$NON-NLS-1$ //$NON-NLS-2$
					
					
				}	
				else if(args[0].equalsIgnoreCase("spell") || args[0].equalsIgnoreCase("spells")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.84")+ ChatColor.GRAY+ "R-L-L-L "+ ChatColor.GREEN+Messages.getString("Reitzrpgmain.85") + ChatColor.GRAY+ magicskillsconfig.getInt("Fire-Level"));
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.87")+ ChatColor.GRAY+ "R-R-R-R " + ChatColor.GREEN+"Level: " + ChatColor.GRAY+ magicskillsconfig.getInt("Teleport-Level")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.83")+ ChatColor.GRAY+ "R-L-L-R "+ ChatColor.GREEN+Messages.getString("Reitzrpgmain.86") + ChatColor.GRAY+ magicskillsconfig.getInt("Tornado-Level")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.82")+ ChatColor.GRAY+ "R-L-R-R-R " + ChatColor.GREEN+Messages.getString("Reitzrpgmain.81") + ChatColor.GRAY+ magicskillsconfig.getInt("Lightning-Escape-Level")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					
				}
				else if(args[0].equalsIgnoreCase("pet")) //$NON-NLS-1$
						{
					
			        			final Skeleton wolf = player.getWorld().spawn(player.getLocation(), Skeleton.class);
			        				wolf.setCustomName(ChatColor.GOLD + player.getName() + Messages.getString("Reitzrpgmain.80")); //$NON-NLS-1$
			        					wolf.setCustomNameVisible(true);
			        						PetMaker.makePet(wolf, player.getUniqueId(),player);
			        						//make it attack shit
			        						
			        						new BukkitRunnable(){

			        							@Override
			        							public void run(){

			        								
			        								new java.util.Date().getTime();
			        							
			        								if (wolf.getHealth() > 0)
			        								{
			        									
			        									if(player.getLastDamageCause() == null ||
			        											player.getLastDamageCause().getEntity() == null)
			        									{
			        										return;
			        									}
			        									else
			        									{
			        									wolf.setTarget((LivingEntity) player.getLastDamageCause().getEntity());
			        									}
			        								}
			        								else
			        								{
			        									this.cancel();
			        								}


			        							}

			        							}.runTaskTimer(plugin, 0L, 2L);
			        						
			        						//end of attack phase
			        							return false;
					
						}
				else if(args[0].equalsIgnoreCase("swordskill") || args[0].equalsIgnoreCase("swordskills")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.76")+ ChatColor.GRAY+ "R-R-R-R " + ChatColor.GREEN+Messages.getString("Reitzrpgmain.77") + ChatColor.GRAY+ swordskillsconfig.getInt("Yelir-Level"));
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.79")+ ChatColor.GRAY+ "R-L-L-R " + ChatColor.GREEN+Messages.getString("Reitzrpgmain.78") + ChatColor.GRAY+ swordskillsconfig.getInt("Spin-Attack-Level")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				}
				else if(args[0].equalsIgnoreCase("bowskill") || args[0].equalsIgnoreCase("bowskills")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.73")+ ChatColor.GRAY+ "R-L-L-R " + ChatColor.GREEN+Messages.getString("Reitzrpgmain.74") + ChatColor.GRAY+ bowskillsconfig.getInt("Rapid-Shot")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.72")+ ChatColor.GRAY+ "R-R-R-L " + ChatColor.GREEN+Messages.getString("Reitzrpgmain.75") + ChatColor.GRAY+ bowskillsconfig.getInt("TNT-Shot-Level")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				}
				
				else if(args[0].equalsIgnoreCase("Defence") || args[0].equalsIgnoreCase("Defense")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					//Where the /ReitzRPG Defense stuff happens
					int defense = pd.getData().getInt("Defense"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.69")+ ChatColor.GRAY+ defense); //$NON-NLS-1$
					int attackupgradecost;
					attackupgradecost = 25 * defense * 2;
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.68")+ ChatColor.GRAY+ attackupgradecost); //$NON-NLS-1$
					
				}	
				else if(args[0].equalsIgnoreCase("Range") || args[0].equalsIgnoreCase("Archery")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					int archery = pd.getData().getInt("Archery"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.70")+ ChatColor.GRAY+ archery); //$NON-NLS-1$
					int attackupgradecost;
					attackupgradecost = 25 * archery * 2;
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.71")+ ChatColor.GRAY+ attackupgradecost); //$NON-NLS-1$
					
					
				}
				
				else if(args[0].equalsIgnoreCase("EXP")) //$NON-NLS-1$
				{
					int combatexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.67")+ ChatColor.GRAY+ combatexp); //$NON-NLS-1$
					
				}
				else if(args[0].equalsIgnoreCase("fishing")) //$NON-NLS-1$
				{
					int combatexp = pd.getData().getInt("Fishing"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.66")+ ChatColor.GRAY+ combatexp); //$NON-NLS-1$
					
				}	
				else if(args[0].equalsIgnoreCase("Digging")) //$NON-NLS-1$
				{
					int combatexp = pd.getData().getInt("Digging"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.65")+ ChatColor.GRAY+ combatexp); //$NON-NLS-1$
					
				}
				else if(args[0].equalsIgnoreCase("Mining")) //$NON-NLS-1$
				{
					int combatexp = pd.getData().getInt("Mining"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.64")+ ChatColor.GRAY+ combatexp); //$NON-NLS-1$
					
				}
				else if(args[0].equalsIgnoreCase("Woodcutting")) //$NON-NLS-1$
				{
					int combatexp = pd.getData().getInt("Woodcutting"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.63")+ ChatColor.GRAY+ combatexp); //$NON-NLS-1$
					
				}					
				else if(args[0].equalsIgnoreCase("Health") || args[0].equalsIgnoreCase("HP")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					int healthlevel = pd.getData().getInt("Health"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.62")+ ChatColor.GRAY+ healthlevel); //$NON-NLS-1$
					
				}	
				else if(args[0].equalsIgnoreCase("Magic")) //$NON-NLS-1$
				{
					int magic = pd.getData().getInt("Magic"); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.61")+ ChatColor.GRAY+ magic); //$NON-NLS-1$
					int magicupgradecost = (25 * magic) * 3;
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.60")+ ChatColor.GRAY+ magicupgradecost); //$NON-NLS-1$

				}	
				else if(args[0].equalsIgnoreCase("sethome")) //$NON-NLS-1$
				{
					Location home = player.getLocation();
					player.setBedSpawnLocation(home,true);					
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.59")); //$NON-NLS-1$
					
				}	
				else if(args[0].equalsIgnoreCase("Tornado")) //$NON-NLS-1$ // tornado spell
				{
					if ( pd.getData().getInt("Magic") >= 5 && config.getBoolean("Tornadoes") == true) //$NON-NLS-1$ //$NON-NLS-2$
							{
					 Location location; 
					 Vector vec = new Vector(2, 2, 2);
					 Block target = player.getTargetBlock(null, 100);
					 Block target2 = player.getTargetBlock(null, 105);
					 location = target.getLocation();
					 target2.getLocation().toVector();
					 location.getDirection();
					 //Tornado.spawnTornado(plugin, location, material, data, direction, speed, amount_of_blocks, time, spew, explode);
					 if(config.getBoolean("Tornadoes") == true)
					 {
					 Tornado.spawnTornado(this, location, Material.WEB, (byte) 0, vec, 0.0, 250, (long) 15*15 , false, false);
							}
					 }
					else
					{
						player.sendMessage(Messages.getString("Reitzrpgmain.58")); //$NON-NLS-1$
						
					}	
				}
				else if(args[0].equalsIgnoreCase("potatomagic")) //$NON-NLS-1$
				{
					if ( pd.getData().getInt("Magic") >= 1 && config.getBoolean("Tornadoes") == true) //$NON-NLS-1$ //$NON-NLS-2$
							{
					 Location location; 
					 Vector vec = new Vector(2, 2, 2);
					 Block target = player.getTargetBlock(null, 100);
					 Block target2 = player.getTargetBlock(null, 105);
					 location = target.getLocation();
					 target2.getLocation().toVector();
					 location.getDirection();
					 //Tornado.spawnTornado(plugin, location, material, data, direction, speed, amount_of_blocks, time, spew, explode);
					 Tornado.spawnTornado(this, location, Material.POTATO, (byte) 0, vec, 0.0, 1, (long) 30*30 , false, false);
							}
					else
					{
						player.sendMessage(Messages.getString("Reitzrpgmain.57")); //$NON-NLS-1$
					}	
				}	
				else if(args[0].equalsIgnoreCase("lock")) //$NON-NLS-1$
						{
					
							Block target = player.getTargetBlock(null, 10);
							Block abovetarget = target.getRelative(0, 1, 0);
							
							Block belowtarget = target.getRelative(0, -1, 0);
							
							Block lefttarget = target.getRelative(1,0,0);
							Block leftbelow = target.getRelative(1,-1,0);				
							target.getRelative(1,1,0);
							
							target.getRelative(0,1,1);
							Block righttarget = target.getRelative(0,0,1);
							Block rightbelow = target.getRelative(0,-1,1);
							
							if (target.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| target.getType().name().equals(Material.IRON_DOOR.toString())
									||target.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| target.getType().name().equals(Material.CHEST.toString())
									|| target.getType().name().equals(Material.STONE_BUTTON.toString())
									|| target.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| target.getType().name().equals(Material.STONE_BUTTON.toString())
									|| target.getType().name().equals(Material.STONE_PLATE.toString())
									|| target.getType().name().equals(Material.WOOD_PLATE.toString())
									|| target.getType().name().equals(Material.GOLD_PLATE.toString())
									|| target.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(target) == null && LockDoors.getWhoLocked(target) == null)
								{
								target.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								target.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.56")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.55")); //$NON-NLS-1$ //$NON-NLS-2$
								
								if ( pd.getData().get("Items-Locked") == null) //$NON-NLS-1$
								{
								pd.getData().set("Items-Locked", player.getName()); //$NON-NLS-1$
								pd.getData().set("Items-Locked.Location.x",target.getLocation().getBlockX()); //$NON-NLS-1$
								pd.getData().set("Items-Locked.Location.y",target.getLocation().getBlockY()); //$NON-NLS-1$
								pd.getData().set("Items-Locked.Location.z",target.getLocation().getBlockZ()); //$NON-NLS-1$
								pd.getData().set("Items-Locked.Location.key",player.getItemInHand().getType().name()); //$NON-NLS-1$
								pd.getData().set("Items-Locked.Location.world",player.getWorld().getName().toString()); //$NON-NLS-1$
								pd.getData().set("Items-Locked.Location.count",1); //$NON-NLS-1$
								target.setMetadata("Count", new FixedMetadataValue(plugin,1)); //$NON-NLS-1$
								pd.getData().set("Count", 1); //$NON-NLS-1$
								
								pd.save();
								}
								else
								{
									int i = 2; //orignally 2
									while(!(pd.getData().getInt("Items-Locked"+i+".Location.x") == 0) //$NON-NLS-1$ //$NON-NLS-2$
											&& !(pd.getData().getInt("Items-Locked"+i+".Location.y") == 0) //$NON-NLS-1$ //$NON-NLS-2$
											&& !(pd.getData().getInt("Items-Locked"+i+".Location.z") == 0)) //$NON-NLS-1$ //$NON-NLS-2$
											// set all chests to 0,0,0 if not in use, skip if values are not all 0,0,0
									{
										//loop works now
										
										
										//Items-Locked[i] has code
										i = i+1;
										if(pd.getData().get("Items-Locked"+i) == null) //$NON-NLS-1$
										{
											break;
										}

										
									}
									if(pd.getData().get("Items-Locked"+i+".Location") == null) //$NON-NLS-1$ //$NON-NLS-2$
									{//creating a new value
										pd.getData().set("Items-Locked"+i, player.getName()); //$NON-NLS-1$
										pd.getData().set("Items-Locked"+i+".Location.x",target.getLocation().getBlockX()); //$NON-NLS-1$ //$NON-NLS-2$
										pd.getData().set("Items-Locked"+i+".Location.y",target.getLocation().getBlockY()); //$NON-NLS-1$ //$NON-NLS-2$
									pd.getData().set("Items-Locked"+i+".Location.z",target.getLocation().getBlockZ()); //$NON-NLS-1$ //$NON-NLS-2$
									pd.getData().set("Items-Locked"+i+".Location.key",player.getItemInHand().getType().name()); //$NON-NLS-1$ //$NON-NLS-2$
									pd.getData().set("Items-Locked"+i+".Location.count", i); //$NON-NLS-1$ //$NON-NLS-2$
									pd.getData().set("Count", i); //$NON-NLS-1$
									pd.getData().set("Items-Locked"+i+".Location.world",player.getWorld().getName().toString()); //$NON-NLS-1$ //$NON-NLS-2$
									target.setMetadata("Count", new FixedMetadataValue(plugin, i)); //$NON-NLS-1$
									target.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
									pd.save();
									}
									else if(pd.getData().getInt("Items-Locked"+i+".Location.x") == 0 //$NON-NLS-1$ //$NON-NLS-2$
											&& pd.getData().getInt("Items-Locked"+i+".Location.y") == 0 //$NON-NLS-1$ //$NON-NLS-2$
											&& pd.getData().getInt("Items-Locked"+i+".Location.z") == 0) //$NON-NLS-1$ //$NON-NLS-2$
									{//reusing an old cleared value
										pd.getData().set("Items-Locked"+i, player.getName()); //$NON-NLS-1$
										pd.getData().set("Items-Locked"+i+".Location.x",target.getLocation().getBlockX()); //$NON-NLS-1$ //$NON-NLS-2$
										pd.getData().set("Items-Locked"+i+".Location.y",target.getLocation().getBlockY()); //$NON-NLS-1$ //$NON-NLS-2$
										pd.getData().set("Items-Locked"+i+".Location.z",target.getLocation().getBlockZ()); //$NON-NLS-1$ //$NON-NLS-2$
										pd.getData().set("Items-Locked"+i+".Location.key",player.getItemInHand().getType().name()); //$NON-NLS-1$ //$NON-NLS-2$
										pd.getData().set("Items-Locked"+i+".Location.count", i); //$NON-NLS-1$ //$NON-NLS-2$
										pd.getData().set("Count", i); //$NON-NLS-1$
										pd.getData().set("Items-Locked"+i+".Location.world",player.getWorld().getName().toString()); //$NON-NLS-1$ //$NON-NLS-2$
										target.setMetadata("Count", new FixedMetadataValue(plugin, i)); //$NON-NLS-1$
										target.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
										pd.save();
										
										
									}	
								}
									
								}
								else
								{
									player.sendMessage(ChatColor.RED+Messages.getString("Reitzrpgmain.54")); //$NON-NLS-1$
								}
							}
								

								
								 
							
							else
							{
								player.sendMessage(ChatColor.RED+Messages.getString("Reitzrpgmain.53")); //$NON-NLS-1$
								System.out.println(target.getType().name());
								System.out.println(Material.CHEST.toString());
							}
							
							if (abovetarget.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| abovetarget.getType().name().equals(Material.IRON_DOOR.toString())
									||abovetarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| abovetarget.getType().name().equals(Material.CHEST.toString())
									|| abovetarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| abovetarget.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| abovetarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| abovetarget.getType().name().equals(Material.STONE_PLATE.toString())
									|| abovetarget.getType().name().equals(Material.WOOD_PLATE.toString())
									|| abovetarget.getType().name().equals(Material.GOLD_PLATE.toString())
									|| abovetarget.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(abovetarget) == null && LockDoors.getWhoLocked(abovetarget) == null)
								{
								abovetarget.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								abovetarget.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								}

								
							}
							if (belowtarget.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| belowtarget.getType().name().equals(Material.IRON_DOOR.toString())
									||belowtarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| belowtarget.getType().name().equals(Material.CHEST.toString())
								    || belowtarget.getType().name().equals(Material.STONE_BUTTON.toString())
								    || belowtarget.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| belowtarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| belowtarget.getType().name().equals(Material.STONE_PLATE.toString())
									|| belowtarget.getType().name().equals(Material.WOOD_PLATE.toString())
									|| belowtarget.getType().name().equals(Material.GOLD_PLATE.toString())
									|| belowtarget.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(belowtarget) == null && LockDoors.getWhoLocked(belowtarget) == null)
								{
								belowtarget.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								belowtarget.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								}
								
							}
							if (lefttarget.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| lefttarget.getType().name().equals(Material.IRON_DOOR.toString())
									||lefttarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| lefttarget.getType().name().equals(Material.CHEST.toString())
								    || lefttarget.getType().name().equals(Material.STONE_BUTTON.toString())
								    || lefttarget.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| lefttarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| lefttarget.getType().name().equals(Material.STONE_PLATE.toString())
									|| lefttarget.getType().name().equals(Material.WOOD_PLATE.toString())
									|| lefttarget.getType().name().equals(Material.GOLD_PLATE.toString())
									|| lefttarget.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(lefttarget) == null && LockDoors.getWhoLocked(lefttarget) == null)
								{
								lefttarget.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								lefttarget.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								}
								
							}
							if (righttarget.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| righttarget.getType().name().equals(Material.IRON_DOOR.toString())
									||righttarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| righttarget.getType().name().equals(Material.CHEST.toString())
								    || righttarget.getType().name().equals(Material.STONE_BUTTON.toString())
								    || righttarget.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| righttarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| righttarget.getType().name().equals(Material.STONE_PLATE.toString())
									|| righttarget.getType().name().equals(Material.WOOD_PLATE.toString())
									|| righttarget.getType().name().equals(Material.GOLD_PLATE.toString())
									|| righttarget.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(righttarget) == null && LockDoors.getWhoLocked(righttarget) == null)
								{
								righttarget.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								righttarget.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								}
					
								
							}
							if (rightbelow.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| rightbelow.getType().name().equals(Material.IRON_DOOR.toString())
									||rightbelow.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| rightbelow.getType().name().equals(Material.CHEST.toString())
								    || rightbelow.getType().name().equals(Material.STONE_BUTTON.toString())
								    || rightbelow.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| rightbelow.getType().name().equals(Material.STONE_BUTTON.toString())
									|| rightbelow.getType().name().equals(Material.STONE_PLATE.toString())
									|| rightbelow.getType().name().equals(Material.WOOD_PLATE.toString())
									|| rightbelow.getType().name().equals(Material.GOLD_PLATE.toString())
									|| rightbelow.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(rightbelow) == null && LockDoors.getWhoLocked(rightbelow) == null)
								{
								rightbelow.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								rightbelow.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								}
								
							}
							if (leftbelow.getType().name().equals(Material.WOODEN_DOOR.toString())
									|| leftbelow.getType().name().equals(Material.IRON_DOOR.toString())
									||leftbelow.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| leftbelow.getType().name().equals(Material.CHEST.toString())
								    || leftbelow.getType().name().equals(Material.STONE_BUTTON.toString())
								    || leftbelow.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| leftbelow.getType().name().equals(Material.STONE_BUTTON.toString())
									|| leftbelow.getType().name().equals(Material.STONE_PLATE.toString())
									|| leftbelow.getType().name().equals(Material.WOOD_PLATE.toString())
									|| leftbelow.getType().name().equals(Material.GOLD_PLATE.toString())
									|| leftbelow.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if(LockDoors.getMetaData(leftbelow) == null && LockDoors.getWhoLocked(leftbelow) == null)
								{
								leftbelow.setMetadata("lock", new FixedMetadataValue(plugin, player.getItemInHand().getType().name())); //$NON-NLS-1$
								leftbelow.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
								}
								
							}

					
					
						}
				

				else if(args[0].equalsIgnoreCase("unlock")) //$NON-NLS-1$
						{
							Block target = player.getTargetBlock(null, 10);
							Block abovetarget = target.getRelative(0, 1, 0);
							Block belowtarget = target.getRelative(0, -1, 0);
							if (target.getType().name().equals(Material.WOODEN_DOOR.toString()) 
									|| target.getType().name().equals(Material.IRON_DOOR.toString())
									||target.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
											|| target.getType().name().equals(Material.CHEST.toString())
											|| target.getType().name().equals(Material.WOOD_BUTTON.toString())
											|| target.getType().name().equals(Material.STONE_BUTTON.toString())
											|| target.getType().name().equals(Material.STONE_PLATE.toString())
											|| target.getType().name().equals(Material.WOOD_PLATE.toString())
											|| target.getType().name().equals(Material.GOLD_PLATE.toString())
											|| target.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if ( target.getMetadata("lock") == null) //$NON-NLS-1$
								{
									
								}
								else if (target.getMetadata("lock") != null) //$NON-NLS-1$
									{//theres a lock
										String metadata = LockDoors.getMetaData(target);
										String item = player.getItemInHand().getType().name();
										String owner = LockDoors.getWhoLocked(target);//need to remove the owner's data
										 PlayerData pd2 = new PlayerData(owner);
										
										if(metadata == null)
										{
											
										}
										else if(metadata.equals(item))
										{
											//theres a lock and they have the key
											
											player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.51")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.52")); //$NON-NLS-1$ //$NON-NLS-2$
											target.removeMetadata("lock", this); //$NON-NLS-1$
											target.removeMetadata("wholocked", this); //$NON-NLS-1$
											Integer count = LockDoors.getCount(target);
											if(count == null || count == 1)//the first door locked = null?
											{
											//pd2 = who locked it need to remove their data!
												pd2.getData().set("Items-Locked", 0); //$NON-NLS-1$
												pd2.getData().set("Items-Locked.Location.x",0); //$NON-NLS-1$
												pd2.getData().set("Items-Locked.Location.y",0); //$NON-NLS-1$
												pd2.getData().set("Items-Locked.Location.z",0); //$NON-NLS-1$
												pd2.getData().set("Items-Locked.Location.key",null); //$NON-NLS-1$
												pd2.save();
											}	
											else
											{
											
											pd2.getData().set("Items-Locked"+count, 0); //$NON-NLS-1$
											pd2.getData().set("Items-Locked"+count+".Location.x",0); //$NON-NLS-1$ //$NON-NLS-2$
											pd2.getData().set("Items-Locked"+count+".Location.y",0); //$NON-NLS-1$ //$NON-NLS-2$
											pd2.getData().set("Items-Locked"+count+".Location.z",0); //$NON-NLS-1$ //$NON-NLS-2$
											pd2.getData().set("Items-Locked"+count+".Location.key",null); //$NON-NLS-1$ //$NON-NLS-2$
											pd2.save();
											}
										}
										else
										{
											//they need the correct key
											player.sendMessage(ChatColor.RED+Messages.getString("Reitzrpgmain.50")); //$NON-NLS-1$
										}	
									
									
											
									}	
								
							}
							if (abovetarget.getType().name().equals(Material.WOODEN_DOOR.toString()) 
									|| abovetarget.getType().name().equals(Material.IRON_DOOR.toString())
									||abovetarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| abovetarget.getType().name().equals(Material.CHEST.toString())
									|| abovetarget.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| abovetarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| abovetarget.getType().name().equals(Material.STONE_PLATE.toString())
									|| abovetarget.getType().name().equals(Material.WOOD_PLATE.toString())
									|| abovetarget.getType().name().equals(Material.GOLD_PLATE.toString())
									|| abovetarget.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if ( abovetarget.getMetadata("lock") == null) //$NON-NLS-1$
								{
									//thers no lock to unlock
								}
								else if (abovetarget.getMetadata("lock") != null) //$NON-NLS-1$
									{//theres a lock
										String metadata = LockDoors.getMetaData(abovetarget);
										String item = player.getItemInHand().getType().name();
										if(metadata == null)
										{
											
										}
										else if(metadata.equals(item))
										{
											//theres a lock and they have the key
											abovetarget.removeMetadata("lock", this); //$NON-NLS-1$
											abovetarget.removeMetadata("wholocked", this); //$NON-NLS-1$
											
										}
					
									}
								
							}
							if (belowtarget.getType().name().equals(Material.WOODEN_DOOR.toString()) 
									|| belowtarget.getType().name().equals(Material.IRON_DOOR.toString())
									||belowtarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| belowtarget.getType().name().equals(Material.CHEST.toString())
									|| belowtarget.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| belowtarget.getType().name().equals(Material.STONE_BUTTON.toString())
									|| belowtarget.getType().name().equals(Material.STONE_PLATE.toString())
									|| belowtarget.getType().name().equals(Material.WOOD_PLATE.toString())
									|| belowtarget.getType().name().equals(Material.GOLD_PLATE.toString())
									|| belowtarget.getType().name().equals(Material.IRON_PLATE.toString()))
							{
								if ( belowtarget.getMetadata("lock") == null) //$NON-NLS-1$
								{
									//thers no lock to unlock
								}
								else if (belowtarget.getMetadata("lock") != null) //$NON-NLS-1$
									{//theres a lock
										String metadata = LockDoors.getMetaData(belowtarget);
										String item = player.getItemInHand().getType().name();
										if(metadata == null)
										{
										}
										else if(metadata.equals(item))
										{
											//theres a lock and they have the key
											belowtarget.removeMetadata("lock", this); //$NON-NLS-1$
											belowtarget.removeMetadata("wholocked", this); //$NON-NLS-1$
											
										}
					
									}
								
							}
						}
				
				//UNCOST
				
			//					target.setMetadata("costlock", new FixedMetadataValue(plugin, args[1]));
				//target.setMetadata("wholocked", new FixedMetadataValue(plugin, player.getName()));
				else if(args[0].equalsIgnoreCase("uncost")) //$NON-NLS-1$
				{
					Block target = player.getTargetBlock(null, 10);
					Block abovetarget = target.getRelative(0, 1, 0);
					Block belowtarget = target.getRelative(0, -1, 0);
					if (target.getType().name().equals(Material.WOODEN_DOOR.toString()) 
							|| target.getType().name().equals(Material.IRON_DOOR.toString())
							||target.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
									|| target.getType().name().equals(Material.CHEST.toString())
									|| target.getType().name().equals(Material.WOOD_BUTTON.toString())
									|| target.getType().name().equals(Material.STONE_BUTTON.toString())
									|| target.getType().name().equals(Material.STONE_PLATE.toString())
									|| target.getType().name().equals(Material.WOOD_PLATE.toString())
									|| target.getType().name().equals(Material.GOLD_PLATE.toString())
									|| target.getType().name().equals(Material.IRON_PLATE.toString()))
					{
						if ( target.getMetadata("costlock") == null) //$NON-NLS-1$
						{
							
						}
						else if (target.getMetadata("costlock") != null) //$NON-NLS-1$
							{//theres a lock
								CostDoors.getMetaData(target);
								String owner = CostDoors.getwholocked2(target);//need to remove the owner's data
								 PlayerData pd2 = new PlayerData(owner);
								
								if(owner == null)
								{
									
								}
								else if(owner != null)
								{
									//theres a lock and they have the key
									
									player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.48")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.49")); //$NON-NLS-1$ //$NON-NLS-2$
									target.removeMetadata("costlock", this); //$NON-NLS-1$
									target.removeMetadata("wholocked2", this); //$NON-NLS-1$
									Integer count = LockDoors.getCount(target);
									if(count == null || count == 1)//the first door locked = null?
									{
									//pd2 = who locked it need to remove their data!
										pd2.getData().set("Cost-Locked", 0); //$NON-NLS-1$
										pd2.getData().set("Cost-Locked.Location.x",0); //$NON-NLS-1$
										pd2.getData().set("Cost-Locked.Location.y",0); //$NON-NLS-1$
										pd2.getData().set("Cost-Locked.Location.z",0); //$NON-NLS-1$
										pd2.getData().set("Cost-Locked.Location.key",null); //$NON-NLS-1$
										pd2.save();
									}	
									else
									{
									
									pd2.getData().set("Cost-Locked"+count, 0); //$NON-NLS-1$
									pd2.getData().set("Cost-Locked"+count+".Location.x",0); //$NON-NLS-1$ //$NON-NLS-2$
									pd2.getData().set("Cost-Locked"+count+".Location.y",0); //$NON-NLS-1$ //$NON-NLS-2$
									pd2.getData().set("Cost-Locked"+count+".Location.z",0); //$NON-NLS-1$ //$NON-NLS-2$
									pd2.getData().set("Cost-Locked"+count+".Location.key",null); //$NON-NLS-1$ //$NON-NLS-2$
									pd2.save();
									}
								}
	
							
							
									
							}	
						
					}
					if (abovetarget.getType().name().equals(Material.WOODEN_DOOR.toString()) 
							|| abovetarget.getType().name().equals(Material.IRON_DOOR.toString())
							||abovetarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
							|| abovetarget.getType().name().equals(Material.CHEST.toString())
							|| abovetarget.getType().name().equals(Material.WOOD_BUTTON.toString())
							|| abovetarget.getType().name().equals(Material.STONE_BUTTON.toString())
							|| abovetarget.getType().name().equals(Material.STONE_PLATE.toString())
							|| abovetarget.getType().name().equals(Material.WOOD_PLATE.toString())
							|| abovetarget.getType().name().equals(Material.GOLD_PLATE.toString())
							|| abovetarget.getType().name().equals(Material.IRON_PLATE.toString()))
					{
						if ( abovetarget.getMetadata("costlock") == null) //$NON-NLS-1$
						{
							//thers no lock to unlock
						}
						else if (abovetarget.getMetadata("costlock") != null) //$NON-NLS-1$
							{//theres a lock
								CostDoors.getMetaData(abovetarget);
								String owner = CostDoors.getwholocked2(target);

								if(owner == null)
								{
									
								}
								else
								{
									//theres a lock and they have the key
									abovetarget.removeMetadata("costlock", this); //$NON-NLS-1$
									abovetarget.removeMetadata("wholocked2", this); //$NON-NLS-1$
									
								}
			
							}
						
					}
					if (belowtarget.getType().name().equals(Material.WOODEN_DOOR.toString()) 
							|| belowtarget.getType().name().equals(Material.IRON_DOOR.toString())
							||belowtarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
							|| belowtarget.getType().name().equals(Material.CHEST.toString())
							|| belowtarget.getType().name().equals(Material.WOOD_BUTTON.toString())
							|| belowtarget.getType().name().equals(Material.STONE_BUTTON.toString())
							|| belowtarget.getType().name().equals(Material.STONE_PLATE.toString())
							|| belowtarget.getType().name().equals(Material.WOOD_PLATE.toString())
							|| belowtarget.getType().name().equals(Material.GOLD_PLATE.toString())
							|| belowtarget.getType().name().equals(Material.IRON_PLATE.toString()))
					{
						if ( belowtarget.getMetadata("costlock") == null) //$NON-NLS-1$
						{
							//thers no lock to unlock
						}
						else if (belowtarget.getMetadata("costlock") != null) //$NON-NLS-1$
							{//theres a lock
								Integer cost = CostDoors.getMetaData(belowtarget);
								String owner = CostDoors.getwholocked2(target);
								System.out.println(cost);//10
								System.out.println(owner);//is null?
								player.getItemInHand().getType().name();
								if(cost == null)
								{
								}
								else
								{
									//theres a lock and they have the key
									belowtarget.removeMetadata("costlock", this); //$NON-NLS-1$
									belowtarget.removeMetadata("wholocked2", this); //$NON-NLS-1$
									
								}
			
							}
						
					}
				}
				
				//END OF UNCOST
				else if(args[0].equalsIgnoreCase("FireTornado") && config.getBoolean("Tornadoes") == true) //$NON-NLS-1$ //$NON-NLS-2$
				{
					if ( pd.getData().getInt("Magic") >= 10 ) //$NON-NLS-1$
							{
					 Location location; 
					 Vector vec = new Vector(2, 2, 2);
					 Block target = player.getTargetBlock(null, 100);
					 Block target2 = player.getTargetBlock(null, 105);
					 location = target.getLocation();
					 target2.getLocation().toVector();
					 location.getDirection();
					 //Tornado.spawnTornado(plugin, location, material, data, direction, speed, amount_of_blocks, time, spew, explode);
					 Tornado.spawnTornado(this, location, Material.FIRE, (byte) 0, vec, 0.0, 250, (long) 3*3 , true, true);
							}
					else
					{
						player.sendMessage(Messages.getString("Reitzrpgmain.47")); //$NON-NLS-1$
					}	
				}								
				else if(args[0].equalsIgnoreCase("Setspawn")) //$NON-NLS-1$
				{
					if (player.hasPermission("reitzrpg.setspawn"))  //$NON-NLS-1$
					{
					World world = player.getWorld();
					Location loc = player.getLocation();
					world.setSpawnLocation(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.45")); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.46")); //$NON-NLS-1$
					List<Entity> entities = world.getEntities();
					for ( Entity entity : entities)
					{
					  if(entity.getType() != EntityType.PLAYER)
					  {
						entity.removeMetadata("level", this); //$NON-NLS-1$
					    entity.remove();
					  }
					}
					}
					else
					{
						player.sendMessage(ChatColor.GREEN +"Permission needed: "+ ChatColor.GRAY+ "Reitzrpg.setspawn"); //$NON-NLS-1$ //$NON-NLS-2$
					}	
					
				}	
				else if(args[0].equalsIgnoreCase("killmobs")) //$NON-NLS-1$
				{
					if (player.hasPermission("reitzrpg.killmobs")) //$NON-NLS-1$
					{
					
							World world = player.getWorld();
							List<Entity> entities = world.getEntities();
								for ( Entity entity : entities)
								{
										if(entity.getType() != EntityType.PLAYER)
											{
												entity.removeMetadata("level", this); //$NON-NLS-1$
												entity.remove();
											}
								}

					
					}
					else
					{
						player.sendMessage(ChatColor.GREEN +"Permission needed: "+ ChatColor.GRAY+ "Reitzrpg.killmobs"); //$NON-NLS-1$ //$NON-NLS-2$
					}	
				}
				else if(args[0].equalsIgnoreCase("menu")) //$NON-NLS-1$
				{
							player.openInventory(myCustomInventory.myInventory);
					
				}
				else if(args[0].equalsIgnoreCase("backpack") && API.backpackenabled == true) //$NON-NLS-1$
				{	
					
					int size = pd.getData().getInt("BackPack-Size"); //$NON-NLS-1$
					if(size == 0)
					{
			    		pd.getData().set("BackPack-Size", 9); //$NON-NLS-1$
			    		pd.getData().set("BackPack", "9;"); //$NON-NLS-1$ //$NON-NLS-2$
			    		pd.save();	
			    		size = pd.getData().getInt("BackPack-Size"); //$NON-NLS-1$
					}	
					Inventory PlayerBackPack = Bukkit.createInventory(player, size,"Your Backpack"); //$NON-NLS-1$
					//System.out.println(PlayerBackPack.getName());
					player.openInventory(PlayerBackPack);
			
				}				
				else if(args[0].equalsIgnoreCase("reload")) //$NON-NLS-1$
				{
					if(player.hasPermission("reitzrpg.reloadconfig")) //$NON-NLS-1$
					{
					World world = player.getWorld();
					List<Player> entities = world.getPlayers();
					player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.44")); //$NON-NLS-1$
								for ( Player player2 : entities)
								{

										ScoreboardStuff.manageScoreboard(player2, "TeamName"); //$NON-NLS-1$
										player2.setMaxHealth(pd.getData().getInt("Health")); //$NON-NLS-1$
																				
								}
								
					}
					else
					{
						player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.43")+ ChatColor.GRAY+ "Reitzrpg.reloadconfig"); //$NON-NLS-1$ //$NON-NLS-2$
						
					}

				}
		
			}	

			else if(args.length ==2)
			{
				if(args[0].equalsIgnoreCase("Purchase")) //$NON-NLS-1$
				{	
					if(args[1].equalsIgnoreCase("Attack") || args[1].equalsIgnoreCase("ATT")) //$NON-NLS-1$ //$NON-NLS-2$
					{
						//they are purchasing attack power
						int attackupgradecost;
						int currentlevel;
						int currentexp;
						currentlevel = pd.getData().getInt("Attack"); //$NON-NLS-1$
						currentexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
						attackupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= attackupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.41")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.42")); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Attack", pd.getData().getInt("Attack")+1); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							currentlevel = pd.getData().getInt("Attack"); //$NON-NLS-1$
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.40")+ ChatColor.GRAY+ currentlevel); //$NON-NLS-1$
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-attackupgradecost); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name")); //$NON-NLS-1$
							
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.39")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.38")); //$NON-NLS-1$ //$NON-NLS-2$
							player.sendMessage(ChatColor.GREEN + Messages.getString("Reitzrpgmain.37") +  currentexp + ChatColor.GRAY //$NON-NLS-1$
									+Messages.getString("Reitzrpgmain.36") + attackupgradecost); //$NON-NLS-1$
							
						}
						
					}	
					else if(args[1].equalsIgnoreCase("Defense") || args[1].equalsIgnoreCase("Defense")) //$NON-NLS-1$ //$NON-NLS-2$
					{
						//they are purchasing defense power
						//they are purchasing attack power
						int attackupgradecost;
						int currentlevel;
						int currentexp;
						currentlevel = pd.getData().getInt("Defense"); //$NON-NLS-1$
						currentexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
						attackupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= attackupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.35")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.34")); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Defense", pd.getData().getInt("Defense")+1); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							currentlevel = pd.getData().getInt("Defense"); //$NON-NLS-1$
							player.sendMessage(ChatColor.GREEN +"Defense is level: "+ ChatColor.GRAY+ currentlevel); //$NON-NLS-1$
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-attackupgradecost); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name")); //$NON-NLS-1$
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.33")+ ChatColor.GRAY+ "failed"); //$NON-NLS-1$ //$NON-NLS-2$
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY //$NON-NLS-1$
									+Messages.getString("Reitzrpgmain.32") + attackupgradecost); //$NON-NLS-1$
						}	
						
						
					}
					else if(args[1].equalsIgnoreCase("Range") || args[1].equalsIgnoreCase("Archery")) //$NON-NLS-1$ //$NON-NLS-2$
					{
						//they are purchasing range power
						//they are purchasing attack power
						int attackupgradecost;
						int currentlevel;
						int currentexp;
						currentlevel = pd.getData().getInt("Archery"); //$NON-NLS-1$
						currentexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
						attackupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= attackupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.31")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.30")); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Archery", pd.getData().getInt("Archery")+1); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							currentlevel = pd.getData().getInt("Archery"); //$NON-NLS-1$
							player.sendMessage(ChatColor.GREEN + Messages.getString("Reitzrpgmain.29") + currentlevel); //$NON-NLS-1$
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-attackupgradecost); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name")); //$NON-NLS-1$
						}	
						else
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.28")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.27")); //$NON-NLS-1$ //$NON-NLS-2$
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY //$NON-NLS-1$
									+" needed: " + attackupgradecost); //$NON-NLS-1$
							
						}	
					
					}
					else if(args[1].equalsIgnoreCase("Health") || args[1].equalsIgnoreCase("HP")) //$NON-NLS-1$ //$NON-NLS-2$
					{	int currentlevel;
						int currentexp;
						double healthupgradecost = player.getMaxHealth() * 30;
						currentexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
						
						if (currentexp >= healthupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.26")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.25")); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Health", pd.getData().getDouble("Health")+2); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							currentlevel = pd.getData().getInt("Health"); //$NON-NLS-1$
							player.setMaxHealth(currentlevel);
							player.setHealth(currentlevel);
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.24")+ ChatColor.GRAY+ currentlevel); //$NON-NLS-1$
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-healthupgradecost); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name")); //$NON-NLS-1$
						
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.23")+ ChatColor.GRAY+ "failed"); //$NON-NLS-1$ //$NON-NLS-2$
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY //$NON-NLS-1$
									+Messages.getString("Reitzrpgmain.22") + healthupgradecost); //$NON-NLS-1$
						}	

						
					}
					else if(args[1].equalsIgnoreCase("Magic")) //$NON-NLS-1$
					{
						int currentlevel = pd.getData().getInt("Magic"); //$NON-NLS-1$
						int currentexp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
						int magicupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= magicupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.21")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.20")); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Magic", pd.getData().getInt("Magic")+1); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							currentlevel = pd.getData().getInt("Magic"); //$NON-NLS-1$
							player.sendMessage(ChatColor.BOLD + Messages.getString("Reitzrpgmain.19") + currentlevel); //$NON-NLS-1$
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-magicupgradecost); //$NON-NLS-1$ //$NON-NLS-2$
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name")); //$NON-NLS-1$
						}	
						else
						{
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.18")+ ChatColor.GRAY+ "failed"); //$NON-NLS-1$ //$NON-NLS-2$
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY //$NON-NLS-1$
									+Messages.getString("Reitzrpgmain.17") + magicupgradecost); //$NON-NLS-1$
						}
					}
					else if(args[1].equalsIgnoreCase("Backpack") && API.backpackenabled == true) //$NON-NLS-1$
							{
								int currentsize = pd.getData().getInt("BackPack-Size"); //$NON-NLS-1$
								if(currentsize == 0)
								{
						    		pd.getData().set("BackPack-Size", 9); //$NON-NLS-1$
						    		pd.getData().set("BackPack", "9;"); //$NON-NLS-1$ //$NON-NLS-2$
						    		pd.save();
									currentsize = pd.getData().getInt("BackPack-Size"); //$NON-NLS-1$
								}	
								int exp = pd.getData().getInt("Combat-EXP"); //$NON-NLS-1$
								int upgradecost = (currentsize + 9) * (currentsize*2);
						
								if   ( exp > upgradecost && currentsize < 54 )
								{
									player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.16")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.15")); //$NON-NLS-1$ //$NON-NLS-2$
									pd.getData().set("BackPack-Size", currentsize + 9); //$NON-NLS-1$
									pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-upgradecost); //$NON-NLS-1$ //$NON-NLS-2$
									pd.save();
								}
								else
								{
									player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.14")+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.13")); //$NON-NLS-1$ //$NON-NLS-2$
									player.sendMessage(ChatColor.GREEN + "EXP: " +  exp + ChatColor.GRAY //$NON-NLS-1$
											+Messages.getString("Reitzrpgmain.12") + upgradecost); //$NON-NLS-1$
									
								}	
							
						
							}
				
						
						
					}
			else if(args[0].equalsIgnoreCase("Trade") && API.tradingenabled == true) //$NON-NLS-1$
					{

						String playertradename = args[1];
						Player playertradename2 = Bukkit.getServer().getPlayer(playertradename);
				        if (playertradename2 == null) {
				            sender.sendMessage(args[1] + Messages.getString("Reitzrpgmain.11")); //$NON-NLS-1$
				            return false;
				         }
				        List<Entity> people = playertradename2.getNearbyEntities(10, 10, 10);
				        for(Entity player2 : people)
				        {
				        	if(player2 instanceof Player)
				        	{
				        		String name = ((Player) player2).getName();
				        		if( name == player.getName())
				        		{
									//Inventory playerone = PlayerTrading.TradeMenu;
									Inventory playerone = Bukkit.createInventory(player, 9, ChatColor.BOLD + Messages.getString("Reitzrpgmain.10")); //$NON-NLS-1$
									Inventory playertwo = Bukkit.createInventory(playertradename2, 9, ChatColor.BOLD + Messages.getString("Reitzrpgmain.9")); //$NON-NLS-1$
									playerone.setContents(PlayerTrading.TradeMenu.getContents());
									playertwo.setContents(PlayerTrading.TradeMenu2.getContents());
									//Inventory playertwo = PlayerTrading.TradeMenu2;
								    //player.openInventory(PlayerTrading.TradeMenu);
								    //playertradename2.openInventory(PlayerTrading.TradeMenu2);
									player.openInventory(playerone);
								    playertradename2.openInventory(playertwo);
								    Inventory finalthingy = Bukkit.createInventory(null, 9, ChatColor.BOLD + Messages.getString("Reitzrpgmain.8")); //$NON-NLS-1$
									PlayerTrading.hm.put(player.getName(), finalthingy);
									PlayerTrading.hm.put(playertradename2.getName(), finalthingy);
								    player.sendMessage(Messages.getString("Reitzrpgmain.7")); //$NON-NLS-1$
								    playertradename2.sendMessage(Messages.getString("Reitzrpgmain.6")); //$NON-NLS-1$
				        			
				        		}	
				        		
				        	}	
				        }	
				        	//where the old code was
					    
					
					
					
					}
			else if(args[0].equalsIgnoreCase("forcerespawn")) //$NON-NLS-1$
			{
				String playertradename = args[1];
				Player playertradename2 = Bukkit.getServer().getPlayer(playertradename);
		        if (playertradename2 == null) {
		            sender.sendMessage(args[1] + " is not online!"); //$NON-NLS-1$
		            return false;
		         }
		        playertradename2.setHealth(20.0);
		        playertradename2.teleport(player.getLocation());
				
			}
			else if(args[0].equalsIgnoreCase("teleport") && args[1].equalsIgnoreCase("home")) //$NON-NLS-1$ //$NON-NLS-2$
					{
						Location home = player.getBedSpawnLocation();
						if (home == null)
						{
							player.sendMessage(ChatColor.GREEN +"Teleport: "+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.3")); //$NON-NLS-1$ //$NON-NLS-2$
							player.sendMessage(ChatColor.GREEN +Messages.getString("Reitzrpgmain.4")+ ChatColor.GRAY+ "/rpg sethome"); //$NON-NLS-1$ //$NON-NLS-2$
						}	
						else
						{
							player.teleport(player.getBedSpawnLocation());
							
						}	
				
					}

			
			//START OF COSTLOCK----------------------------------------------------
			else if(args[0].equalsIgnoreCase("costlock")) //$NON-NLS-1$
			{
				//rpg costlock 10
				Block target = player.getTargetBlock(null, 10);
				Block abovetarget = target.getRelative(0, 1, 0);
				
				Block belowtarget = target.getRelative(0, -1, 0);
				

				
				if (target.getType().name().equals(Material.WOODEN_DOOR.toString())
						|| target.getType().name().equals(Material.IRON_DOOR.toString())
						||target.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
						|| target.getType().name().equals(Material.CHEST.toString())
						|| target.getType().name().equals(Material.STONE_BUTTON.toString())
						|| target.getType().name().equals(Material.WOOD_BUTTON.toString())
						|| target.getType().name().equals(Material.STONE_BUTTON.toString())
						|| target.getType().name().equals(Material.STONE_PLATE.toString())
						|| target.getType().name().equals(Material.WOOD_PLATE.toString())
						|| target.getType().name().equals(Material.GOLD_PLATE.toString())
						|| target.getType().name().equals(Material.IRON_PLATE.toString()))
				{
					if(CostDoors.getMetaData(target) == null && CostDoors.getwholocked2(target) == null)
					{
					target.setMetadata("costlock", new FixedMetadataValue(plugin, args[1])); //$NON-NLS-1$
					target.setMetadata("wholocked2", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
					player.sendMessage(ChatColor.GREEN +"Lock: "+ ChatColor.GRAY+ Messages.getString("Reitzrpgmain.5")); //$NON-NLS-1$ //$NON-NLS-2$
					
					if ( pd.getData().get("Cost-Locked") == null) //$NON-NLS-1$
					{
					pd.getData().set("Cost-Locked", player.getName()); //$NON-NLS-1$
					pd.getData().set("Cost-Locked.Location.x",target.getLocation().getBlockX()); //$NON-NLS-1$
					pd.getData().set("Cost-Locked.Location.y",target.getLocation().getBlockY()); //$NON-NLS-1$
					pd.getData().set("Cost-Locked.Location.z",target.getLocation().getBlockZ()); //$NON-NLS-1$
					pd.getData().set("Cost-Locked.Location.key",player.getItemInHand().getType().name()); //$NON-NLS-1$
					pd.getData().set("Cost-Locked.Location.world",player.getWorld().getName().toString()); //$NON-NLS-1$
					pd.getData().set("Cost-Locked.Location.Count2",1); //$NON-NLS-1$
					target.setMetadata("Count2", new FixedMetadataValue(plugin,1)); //$NON-NLS-1$
					pd.getData().set("Count2", 1); //$NON-NLS-1$
					
					pd.save();
					}
					else
					{
						int i = 2; //orignally 2
						while(!(pd.getData().getInt("Cost-Locked"+i+".Location.x") == 0) //$NON-NLS-1$ //$NON-NLS-2$
								&& !(pd.getData().getInt("Cost-Locked"+i+".Location.y") == 0) //$NON-NLS-1$ //$NON-NLS-2$
								&& !(pd.getData().getInt("Cost-Locked"+i+".Location.z") == 0)) //$NON-NLS-1$ //$NON-NLS-2$
								// set all chests to 0,0,0 if not in use, skip if values are not all 0,0,0
						{
							//loop works now
							
							
							//Cost-Locked[i] has code
							i = i+1;
							if(pd.getData().get("Cost-Locked"+i) == null) //$NON-NLS-1$
							{
								break;
							}

							
						}
						if(pd.getData().get("Cost-Locked"+i+".Location") == null) //$NON-NLS-1$ //$NON-NLS-2$
						{//creating a new value
							pd.getData().set("Cost-Locked"+i, player.getName()); //$NON-NLS-1$
							pd.getData().set("Cost-Locked"+i+".Location.x",target.getLocation().getBlockX()); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Cost-Locked"+i+".Location.y",target.getLocation().getBlockY()); //$NON-NLS-1$ //$NON-NLS-2$
						pd.getData().set("Cost-Locked"+i+".Location.z",target.getLocation().getBlockZ()); //$NON-NLS-1$ //$NON-NLS-2$
						pd.getData().set("Cost-Locked"+i+".Location.key",player.getItemInHand().getType().name()); //$NON-NLS-1$ //$NON-NLS-2$
						pd.getData().set("Cost-Locked"+i+".Location.Count2", i); //$NON-NLS-1$ //$NON-NLS-2$
						pd.getData().set("Count2", i); //$NON-NLS-1$
						pd.getData().set("Cost-Locked"+i+".Location.world",player.getWorld().getName().toString()); //$NON-NLS-1$ //$NON-NLS-2$
						target.setMetadata("Count2", new FixedMetadataValue(plugin, i)); //$NON-NLS-1$
						target.setMetadata("wholocked2", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
						target.setMetadata("costlock", new FixedMetadataValue(plugin, args[1])); //$NON-NLS-1$
						pd.save();
						}
						else if(pd.getData().getInt("Cost-Locked"+i+".Location.x") == 0 //$NON-NLS-1$ //$NON-NLS-2$
								&& pd.getData().getInt("Cost-Locked"+i+".Location.y") == 0 //$NON-NLS-1$ //$NON-NLS-2$
								&& pd.getData().getInt("Cost-Locked"+i+".Location.z") == 0) //$NON-NLS-1$ //$NON-NLS-2$
						{//reusing an old cleared value
							pd.getData().set("Cost-Locked"+i, player.getName()); //$NON-NLS-1$
							pd.getData().set("Cost-Locked"+i+".Location.x",target.getLocation().getBlockX()); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Cost-Locked"+i+".Location.y",target.getLocation().getBlockY()); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Cost-Locked"+i+".Location.z",target.getLocation().getBlockZ()); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Cost-Locked"+i+".Location.key",player.getItemInHand().getType().name()); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Cost-Locked"+i+".Location.Count2", i); //$NON-NLS-1$ //$NON-NLS-2$
							pd.getData().set("Count2", i); //$NON-NLS-1$
							pd.getData().set("Cost-Locked"+i+".Location.world",player.getWorld().getName().toString()); //$NON-NLS-1$ //$NON-NLS-2$
							target.setMetadata("Count2", new FixedMetadataValue(plugin, i)); //$NON-NLS-1$
							target.setMetadata("costlock", new FixedMetadataValue(plugin, args[1])); //$NON-NLS-1$
							target.setMetadata("wholocked2", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
							pd.save();
							
							
						}	
					}
						
					}
					else
					{
						player.sendMessage(ChatColor.RED+Messages.getString("Reitzrpgmain.2")); //$NON-NLS-1$
					}
				}
					

					
					 
				
				else
				{
					player.sendMessage(ChatColor.RED+Messages.getString("Reitzrpgmain.1")); //$NON-NLS-1$
					System.out.println(target.getType().name());
					System.out.println(Material.CHEST.toString());
				}
				
				if (abovetarget.getType().name().equals(Material.WOODEN_DOOR.toString())
						|| abovetarget.getType().name().equals(Material.IRON_DOOR.toString())
						||abovetarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
						|| abovetarget.getType().name().equals(Material.CHEST.toString())
						|| abovetarget.getType().name().equals(Material.STONE_BUTTON.toString())
						|| abovetarget.getType().name().equals(Material.WOOD_BUTTON.toString())
						|| abovetarget.getType().name().equals(Material.STONE_BUTTON.toString())
						|| abovetarget.getType().name().equals(Material.STONE_PLATE.toString())
						|| abovetarget.getType().name().equals(Material.WOOD_PLATE.toString())
						|| abovetarget.getType().name().equals(Material.GOLD_PLATE.toString())
						|| abovetarget.getType().name().equals(Material.IRON_PLATE.toString()))
				{
					if(CostDoors.getMetaData(abovetarget) == null && CostDoors.getwholocked2(abovetarget) == null)
					{
						abovetarget.setMetadata("costlock", new FixedMetadataValue(plugin, args[1])); //$NON-NLS-1$
						abovetarget.setMetadata("wholocked2", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
						
					}

					
				}
				if (belowtarget.getType().name().equals(Material.WOODEN_DOOR.toString())
						|| belowtarget.getType().name().equals(Material.IRON_DOOR.toString())
						||belowtarget.getType().name().equals(Material.IRON_DOOR_BLOCK.toString())
						|| belowtarget.getType().name().equals(Material.CHEST.toString())
					    || belowtarget.getType().name().equals(Material.STONE_BUTTON.toString())
					    || belowtarget.getType().name().equals(Material.WOOD_BUTTON.toString())
						|| belowtarget.getType().name().equals(Material.STONE_BUTTON.toString())
						|| belowtarget.getType().name().equals(Material.STONE_PLATE.toString())
						|| belowtarget.getType().name().equals(Material.WOOD_PLATE.toString())
						|| belowtarget.getType().name().equals(Material.GOLD_PLATE.toString())
						|| belowtarget.getType().name().equals(Material.IRON_PLATE.toString()))
				{
					if(CostDoors.getMetaData(belowtarget) == null && CostDoors.getwholocked2(belowtarget) == null)
					{
						belowtarget.setMetadata("costlock", new FixedMetadataValue(plugin, args[1])); //$NON-NLS-1$
						belowtarget.setMetadata("wholocked2", new FixedMetadataValue(plugin, player.getName())); //$NON-NLS-1$
					}
					
				}


		
		
			}
		}		
			//end of cost lock
				
			else if(args.length == 4)
			{
				if(args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("treasure") //$NON-NLS-1$ //$NON-NLS-2$
						|| args[2].equalsIgnoreCase("chest")) //$NON-NLS-1$
				{
					
					
					String name = args[3];
					System.out.println(name);
					Block target =player.getTargetBlock(null, 10);
					if(target.getType().name().equals(Material.CHEST.toString()))
							{
								Chest target2 = (Chest) target.getState();
						RespawningChest.makethechestrefill(target2, name, player);
						
							}
					
					
					
				}
				else if(args.length == 5)
				if(args[0].equalsIgnoreCase("give") && args[2].equalsIgnoreCase("exp")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					//reitzrpg give paully104 exp 10
					String playertradename = args[1];
					String amount = args[3];
					int amount2 = Integer.parseInt(amount);
					Player playertradename2 = Bukkit.getServer().getPlayer(playertradename);
			        if (playertradename2 == null) {
			            sender.sendMessage(args[1] + Messages.getString("Reitzrpgmain.0")); //$NON-NLS-1$
			            return false;
			         }
			        int combatexp = pd.getData().getInt("Combat-EXP")-amount2; //$NON-NLS-1$
			        PlayerData pd2 = new PlayerData(playertradename2.getName());
			        int combatexp2 = pd2.getData().getInt("Combat-EXP")+amount2; //$NON-NLS-1$
			        pd.getData().set("Combat-EXP", combatexp); //$NON-NLS-1$
			        pd.getData().set("Combat-EXP", combatexp2); //$NON-NLS-1$
			        pd.save();
			        pd2.save();
			        
					
				}
			}
				
			}

			
			
	//end of 1st command
		return false;
	}//end of onCommand handler	
	
}//end of the program
