package com.paulreitz.reitzrpg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class RpgSystem implements CommandExecutor, Listener{
	
	    private Reitzrpgmain plugin;
	    public static HashMap<String, Integer> WorldList = new HashMap<String, Integer>();
	    public static File file;
	    public static YamlConfiguration worlds_config;
		
	    public static void Worlds_Config()
	    {
		file = Reitzrpgmain.worlds_config;
	
		worlds_config = YamlConfiguration.loadConfiguration(file);
		worlds_config.addDefault("Worlds.1", null);
		worlds_config.addDefault("Worlds.1.Name", "ReitzRPG");
		worlds_config.addDefault("Worlds.1.Level", 5);
		worlds_config.addDefault("Worlds.2", null);
		worlds_config.addDefault("Worlds.2.Name", "HundredMiles");
		worlds_config.addDefault("Worlds.2.Level", 10);
		worlds_config.addDefault("Worlds.3", null);
		worlds_config.addDefault("Worlds.3.Name", "PvPWorld");
		worlds_config.addDefault("Worlds.3.Level", 15);
		worlds_config.options().copyDefaults(true);
		try {
			worlds_config.save(file);
			int size = worlds_config.getConfigurationSection("Worlds").getKeys(false).size(); //get the size of worlds
			System.out.println(size);
			for(int i = 1;i < size+1;i++)
			{
				System.out.println(worlds_config.getString("Worlds."+i+".Name"));
				WorldList.put(worlds_config.getString("Worlds."+i+".Name"), worlds_config.getInt("Worlds."+i+".Level"));
				
			}
			System.out.println(WorldList);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		}
	    
	   
	    public RpgSystem(Reitzrpgmain plugin) {
	        this.plugin = plugin;
	    }
	    
	    
	    
	    
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// TODO Auto-generated method stub
		//all processing happens here
		if(cmd.getName().equalsIgnoreCase("RpgSystem") || cmd.getName().equalsIgnoreCase("reitzrpgsystem")
				&& sender.hasPermission("reitzrpg.rpgsystem"))
		{
			if(args.length == 0)
			{
			sender.sendMessage(ChatColor.GREEN+"Welcome to the ReitzRpg system menu");
			sender.sendMessage(ChatColor.GREEN+"Here you can change user settings and other internal settings");
			sender.sendMessage(ChatColor.GREEN+"/rpgsystem update [username] [stat] [amount]");
			sender.sendMessage(ChatColor.GREEN+"EXAMPLE: /rpgsystem update paully104 Attack 1");
			sender.sendMessage(ChatColor.GREEN+"/rpgsystem world enable [world] [level]");
			sender.sendMessage(ChatColor.GREEN+"/rpgsystem world disable [world]");
			//                                              0       1        2      3
			}
			if(args.length == 4 && args[0].contains("update"))
			{
				System.out.println(args[0] + "\n" + args[1] + "\n" + args[2] + "\n" + args[3]);
				
					Player player = Bukkit.getServer().getPlayer(args[1]);
			        if (player == null) {
			            sender.sendMessage(args[1] + Messages.getString("Reitzrpgmain.11")); //$NON-NLS-1$
			            return false;
			         }
					PlayerData pd = PlayerJoinListener.users.get(player.getName());
					String stat = args[2].substring(0, 1).toUpperCase() + args[2].substring(1);
					if(stat.contains("Combat-exp") || stat.contains("Combat-Exp"))
							{
						
								stat = "Combat-EXP";//let me fix that for you!
						
							}
					int level = Integer.parseInt(args[3]);
					pd.getData().set(stat, level);
					//pd.save();
					ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
					
				
			}
			
			else if(args.length == 4 && args[0].contains("world") && args[1].contains("enable"))
			{
				String world = args[2];
				int level = Integer.parseInt(args[3]);
				WorldList.put(world, level);
				
				//rpgsystem world enable exampleworld
	            //0      1      2
				
								
			}
			else if(args.length == 3 && args[0].contains("world") && args[1].contains("disable"))
			{
				String world = args[2];
				int level = Integer.parseInt(args[3]);
				WorldList.remove("world");
				
				//rpgsystem world enable exampleworld
	            //0      1      2
				
								
			}
			
		}
		
		
		
		return false;
	}

}
