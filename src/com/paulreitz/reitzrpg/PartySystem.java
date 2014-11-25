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

import com.paulreitz.reitzrpg.PartyEngine;

public class PartySystem implements CommandExecutor, Listener{
	
	public static HashMap<String, PartyEngine> partylist= new HashMap<String, PartyEngine>(); //leader is key members are in party
	public static HashMap<String,String> inparty = new HashMap<String,String>(); //person is key and contains leader
	
	   private Reitzrpgmain plugin;
	   
	    public PartySystem(Reitzrpgmain plugin) {
	        this.plugin = plugin;
	    }
	 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args)
	{
		// TODO Auto-generated method stub
		

		if(cmd.getName().equalsIgnoreCase("party") || cmd.getName().equalsIgnoreCase("Party")) 
		{	
			
			if(args.length == 0)
			{
			sender.sendMessage(ChatColor.GREEN+"Party CMD Center");
			sender.sendMessage(ChatColor.GREEN+"Party create");
			sender.sendMessage(ChatColor.GREEN+"Party add [user]");
			sender.sendMessage(ChatColor.GREEN+"Party remove [user]");
			sender.sendMessage(ChatColor.GREEN+"Party disband");
			
			
			}
			else if( args.length == 1)  
			{
				
				if(args[0].equalsIgnoreCase("create"))
				{
					if (partylist.get(sender.getName()) == null)
					{
					sender.sendMessage("Creating Party, you are the leader!");
					PartyEngine party = null;
					party = new PartyEngine();
					Player player = (Player) sender;
					party.CreateParty(player);
					partylist.put(sender.getName(), party);
					}
					else
					{
						sender.sendMessage("You already have a party!");
						
					}
				}
				
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
					Player player = Bukkit.getPlayer(sender.getName());
					PartyEngine party = partylist.get(sender.getName());
					party.DisbandParty(player);
					partylist.put(sender.getName(), party);
					inparty.remove(player.getName());
				}
				else if(args[0].equalsIgnoreCase("who"))
				{
					PartyEngine party = partylist.get(sender.getName());
					sender.sendMessage(party.GetMembersString());
					System.out.println(party.GetMembersString());
					
				}
			}
			
				else if(args.length == 2)
				{

					System.out.println(args[0] + " " + args[1]);
					if(args[0].equalsIgnoreCase("add"))
					{
						
						String playername = args[1];

						Player player = Bukkit.getPlayer(playername);// get the player name
						if(player != null)
						{
						//System.out.println(player);
						PartyEngine party = partylist.get(sender.getName()); // get the party?
						//System.out.println(party);
						party.AddMember((Player)sender,player); //add the member to the party
						partylist.put(sender.getName(), party);
						//System.out.println(partylist);
						inparty.put(player.getName(), party.GetLeader());
						//System.out.println(inparty + "THIS IS WHATS MESSING UP");
						}
						else
						{
							System.out.println("Player is not available");
						}
						
						

						
					}
					if(args[0].equalsIgnoreCase("remove"))
					{
						String playername = args[1];
						try
						{
						Player player = Bukkit.getPlayer(playername);
						PartyEngine party = partylist.get(sender.getName());
						party.RemoveMember(player);
						partylist.put(sender.getName(), party);
						inparty.remove(player.getName());
						
						}
						catch(Error e)
						{
							
						}
						
					}
					
					
				}
			
			
			}

		
		return true; //needed for commands
	}


}
