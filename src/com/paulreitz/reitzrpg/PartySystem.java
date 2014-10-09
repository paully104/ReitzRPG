package com.paulreitz.reitzrpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PartySystem implements CommandExecutor, Listener{
	
	public static HashMap<String, List<String>> partylist= new HashMap<String, List<String>>();
	public static HashMap<String,String> partyleader = new HashMap<String,String>();
	
	   private Reitzrpgmain plugin;
	   
	    public PartySystem(Reitzrpgmain plugin) {
	        this.plugin = plugin;
	    }
	 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// TODO Auto-generated method stub

		if(cmd.getName().equalsIgnoreCase("party") || cmd.getName().equalsIgnoreCase("Party")) 
		{	
			if(args.length == 0)
			{
			sender.sendMessage(ChatColor.GREEN+"Party CMD Center");
			sender.sendMessage(ChatColor.GREEN+"Party add [user]");
			sender.sendMessage(ChatColor.GREEN+"Party remove [user]");
			sender.sendMessage(ChatColor.GREEN+"Party disband");
			
			
			}
			else if( args.length == 1)  
			{
				if(args[0].equalsIgnoreCase("add")) //args0 is the first argument
				{
					sender.sendMessage("Please do /party add name to add a member");
				}
				else if(args[0].equalsIgnoreCase("remove"))
				{
					sender.sendMessage("Please do /party remove name to add a member");
				}
				else if(args[0].equalsIgnoreCase("disband"))
				{
					sender.sendMessage("disbanding the party");
				}
				else if(args[0].equalsIgnoreCase("who"))
				{
					System.out.println(partylist.get(sender.getName()));
					
				}
				else
				{
					
				}
			
			}
			else if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("add"))
				{
						String playertradename = args[1];
						Player playertradename2 = Bukkit.getServer().getPlayer(playertradename);
			        if (playertradename2 == null)
			        {
			            sender.sendMessage("Player is offline"); //$NON-NLS-1$
			            return false;//player is offline
			         }
			        else
			        {
			        	sender.sendMessage("Adding " + playertradename + " to the party!");
			        	List<String>l1 = partylist.get(sender.getName());
			        	if(l1 == null)
			        	{
			        		//party list is empty or doesn't exist
			        		List<String> local = new ArrayList<String>();
			        		local.add(playertradename);
			        		
			        		partylist.put(sender.getName(), local);
			        		partyleader.put(playertradename, sender.getName());
			        		
			        		
			        	}
			        	else
			        	{
			        		l1.add(playertradename);
			        		partylist.put(sender.getName(), l1);
			        		partyleader.put(playertradename, sender.getName());
			        	}
			        	
			        }
					
				}
				else if(args[0].equalsIgnoreCase("remove"))
				{
					
					String playertradename = args[1];
					List<String>l1 = partylist.get(sender.getName());
					if(l1 == null)
					{
						sender.sendMessage("Nobody is in your party");
					}
					
				}
				
			}
			else
			{
				
			}
		}
		else
		{
			sender.sendMessage("DUN DUN DUN");
		}
     
		
		
		
		return false;
	}
	
	

}
