package com.paulreitz.reitzrpg;

import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PartyEngine 
{
	
	private String leader; //example Paully104 is the party leader
	private ArrayList<String> members = new ArrayList();
	
	
	public void CreateParty(Player player)
	{
		leader = player.getName();
		//this creates the party
	
	}
	
	public void AddMember(Player sender,Player member)
	{
		try
		{
		members.add(member.getName()); //add the members
		
		}
		catch(NullPointerException e)
		{
			sender.sendMessage("The player you have specified is not online!");
		}
		
		
	}
	public void DisbandParty(Player player)
	{
		leader = null; //removes the party
		members.clear();
		
	}
	
	public void RemoveMember(Player player)
	{
		members.remove(player.getName());
		
	}
	public String GetLeader()
	{
		return leader;
	}
	public String GetMembersString()
	{
		
		return "Leader: "+ leader + " members: " + members;
	}
	public List GetMembersLoop()
	{
		return members;
		
	}

	
	
	
	
}
