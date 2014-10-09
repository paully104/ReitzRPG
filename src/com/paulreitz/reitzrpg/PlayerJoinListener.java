package com.paulreitz.reitzrpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener{


	public static HashMap<String, PlayerData> users = new HashMap<String, PlayerData>();
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
	    Player p = event.getPlayer();
	    //PlayerData pd = new PlayerData(p.getName()); // Will automatically create the file if it [the file] doesn't already exist.
	    PlayerData pd = new PlayerData(p.getName());
	    pd.getData().set("Name", p.getName()); // Use as normal configuration file
	    //start of data
    	Double health = pd.getData().getDouble("Health");
    	Integer archery = pd.getData().getInt("Archery");
    	Integer attack = pd.getData().getInt("Attack");
    	Integer defense = pd.getData().getInt("Defense");
    	Integer combatexp = pd.getData().getInt("Combat-EXP");
    	Integer magic = pd.getData().getInt("Magic");
        Integer miningexp = pd.getData().getInt("Mining-EXP");
        Integer woodcuttingexp = pd.getData().getInt("Woodcutting-EXP");
        Integer fishing =  pd.getData().getInt("Fishing");
        Integer digging = pd.getData().getInt("Digging");
        Integer diggingexp = pd.getData().getInt("Digging-EXP");
        Integer fishingexp = pd.getData().getInt("Fishing-EXP");
        Integer backpacksize = pd.getData().getInt("BackPack-Size");
        Integer woodcutting = pd.getData().getInt("Woodcutting");
        Integer mining = pd.getData().getInt("Mining");
        String backpack = pd.getData().getString("BackPack");

	    	pd.getData().set("Name", p.getName());
	    	
	    	if(health == 0.0)
	    	{
	    		pd.getData().set("Health", 20);
	    		p.setMaxHealth(20);
	    		
	    	}	
	    	else
	    	{
	    		
	    		p.setMaxHealth(health);
	    	}
	    	if(archery == 0)
	    	{
	    	pd.getData().set("Archery", 1);	
	    	
	    	}	
	    	if(attack == 0)
	    	{
	    		pd.getData().set("Attack", 1);
	    		
	    	}	
	    	if(defense == 0)
	    	{
	    		pd.getData().set("Defense", 1);
	    		
	    		
	    	}
	    	if(combatexp == 0)
	    	{
	    		pd.getData().set("Combat-EXP", 0);
	    		
	    				
	    	}
	    	if(magic == 0)
	    	{
	    		
	    		pd.getData().set("Magic", 1);
	    		
	    	}	
	    	if(miningexp == 0)
	    	{
	    		pd.getData().set("Mining-EXP", 0);
	    		
	    	}	
	    	if(woodcuttingexp == 0)
	    	{
	    		pd.getData().set("Woodcutting-EXP", 0);
	    		
	    		
	    	}	
	    	if(woodcutting == 0)
	    	{
	    		pd.getData().set("Woodcutting", 1);
	    		
	    		
	    	}	
	    	if(mining == 0)
	    	{
	    		pd.getData().set("Mining", 1);
	    		
	    		
	    	}		    	
	    	if(fishing == 0)
	    	{
	    		pd.getData().set("Fishing", 1);
	    		
	    		
	    	}	
	    	if(digging == 0)
	    	{
	    		pd.getData().set("Digging", 1);
	    		
	    	}	
	    	if(diggingexp == 0)
	    	{
	    		pd.getData().set("Digging-EXP", 0);
	    		
	    	}	
	    	if(fishingexp == 0)
	    	{
	    		pd.getData().set("Fishing-EXP", 0);
	    		
	    		
	    	}
	    	if(backpacksize == 0 || backpacksize == null)
	    	{
	    		pd.getData().set("BackPack-Size", 9);
	    		pd.getData().set("BackPack", "9;");
	    		
	    		
	    	}	
	    	if(backpack == null || backpack.equalsIgnoreCase(""))
	    	{
	    		pd.getData().set("BackPack", "9;");
	    		pd.getData().set("BackPack-Size", 9);
	    		
	    	}	
	    	pd.save();
	    	users.put(event.getPlayer().getName(), pd);
		    //ScoreboardStuff.manageScoreboard(p, "TeamName");
		    
	    	
	    	ScoreboardStuff.manageScoreboard(p, pd.getData().getString("Name"));
	    	//System.out.println(pd.getData().get("Combat-EXP"));
	    	//System.out.println(users.get(event.getPlayer().getName()).getData().getString("Combat-EXP"));
	    	//the crazy system works hurray we can now grab the info from the users section
	    	
	    
	    
	    
	}//end of onPlayerJoin
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerExit(PlayerQuitEvent event)
	{
		Player p = event.getPlayer();
		PlayerData pd2 = new PlayerData(p.getName());
		
		//get all stats from when the player was online
    	Double health = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getDouble("Health");
    	Integer archery = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Archery");
    	Integer attack = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Attack");
    	Integer defense = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Defense");
    	Integer combatexp = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Combat-EXP");
    	Integer magic = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Magic");
        Integer miningexp = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Mining-EXP");
        Integer woodcuttingexp = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Woodcutting-EXP");
        Integer fishing = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Fishing");
        Integer digging = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Digging");
        Integer diggingexp = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Digging-EXP");
        Integer fishingexp = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Fishing-EXP");
        Integer backpacksize =PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("BackPack-Size");
        Integer woodcutting = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Woodcutting");
        Integer mining = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getInt("Mining");
        String backpack = PlayerJoinListener.users.get(event.getPlayer().getName()).getData().getString("BackPack");
        
        //set all files to save locally due to them disconnecting
        
        pd2.getData().set("Health", health);
        pd2.getData().set("Archery", archery);
        pd2.getData().set("Attack", attack);
        pd2.getData().set("Defense", defense);
        pd2.getData().set("Combat-EXP", combatexp);
        pd2.getData().set("Magic", magic);
        pd2.getData().set("Mining-EXP", miningexp);
        pd2.getData().set("Woodcutting-EXP", woodcuttingexp);
        pd2.getData().set("Fishing", fishing);
        pd2.getData().set("Digging", digging);
        pd2.getData().set("Digging-EXP", diggingexp);
        pd2.getData().set("Fishing-EXP", fishingexp);
        pd2.getData().set("BackPack-Size", backpacksize);
        pd2.getData().set("Woodcutting", woodcutting);
        pd2.getData().set("Mining", mining);
        pd2.getData().set("Backpack", backpack);
        pd2.save();
	}
	
	
	
	
}
