package com.paulreitz.reitzrpg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RpgSystem implements CommandExecutor, Listener{
	
	   private Reitzrpgmain plugin;
	   
	    public RpgSystem(Reitzrpgmain plugin) {
	        this.plugin = plugin;
	    }
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// TODO Auto-generated method stub
		//all processing happens here
		if(cmd.getName().equalsIgnoreCase("RpgSystem") || cmd.getName().equalsIgnoreCase("reitzrpgsystem"))
		{
			if(args.length == 0)
			{
			sender.sendMessage(ChatColor.GREEN+"Welcome to the ReitzRpg system menu");
			sender.sendMessage(ChatColor.GREEN+"Here you can change user settings and other internal settings");
			sender.sendMessage(ChatColor.GREEN+"/rpgsystem update [username] [stat] [amount]");
			sender.sendMessage(ChatColor.GREEN+"EXAMPLE: /rpgsystem update paully104 attack 1");
			//                                                        0       1        2      3
			}
			if(args.length == 4)
			{
				System.out.println(args[0] + "\n" + args[1] + "\n" + args[2] + "\n" + args[3]);
				
					Player player = Bukkit.getServer().getPlayer(args[1]);
			        if (player == null) {
			            sender.sendMessage(args[1] + Messages.getString("Reitzrpgmain.11")); //$NON-NLS-1$
			            return false;
			         }
					PlayerData pd = PlayerJoinListener.users.get(player.getName());
					pd.getData().set(args[2], pd.getData().getInt(args[3]));
					//pd.save();
					pd.getData().set("Attack", 27);
					ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
					
				
			}
			
		}
		
		
		
		return false;
	}

}
