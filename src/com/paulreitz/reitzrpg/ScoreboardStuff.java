package com.paulreitz.reitzrpg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreboardStuff {
	Reitzrpgmain plugin;
    public ScoreboardStuff(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig!    
    }

    	public Team thisteam;
    	
    	public void setTeam(Team team)
    	{
    		thisteam = team;
    	}
    	public Team getTeam()
    	{
    		return thisteam;
    	}	
    
    
        public static  void manageScoreboard(Player player, String teamname)
        {
        	
        	//PlayerData pd = new PlayerData(player.getName());
        	PlayerData pd = PlayerJoinListener.users.get(player.getName().toString());

        	ScoreboardManager manager = Bukkit.getScoreboardManager();//part 1
        	
        	Scoreboard board = manager.getNewScoreboard();//part 2
        	
        	Team team = board.registerNewTeam(teamname);
        	//Adding players
        	team.addPlayer(player);
        	 
        	
        	 
        	//Adding prefixes (shows up in player list before the player's name, supports ChatColors)
        	//team.setPrefix("prefix");
        	 
        	//Adding suffixes (shows up in player list after the player's name, supports ChatColors)
        	//team.setSuffix(" Attack: ");
        	 
        	//Setting the display name
        	team.setDisplayName(teamname+ Messages.getString("ScoreboardStuff.0")); //$NON-NLS-1$
        	 
        	//Making invisible players on the same team have a transparent body
        	team.setCanSeeFriendlyInvisibles(true);
        	 
        	//Making it so players can't hurt others on the same team
        	team.setAllowFriendlyFire(true);
        	
        	Objective objective = board.registerNewObjective("test1", "dummy"); //$NON-NLS-1$ //$NON-NLS-2$

        	
        	//Setting where to display the scoreboard/objective (either SIDEBAR, PLAYER_LIST or BELOW_NAME)
        	objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        	 
        	//Setting the display name of the scoreboard/objective
        	String displayname = player.getDisplayName();
        	displayname = ChatColor.stripColor(displayname);
        	String displayname2 = displayname.substring(0, 1).toUpperCase() + displayname.substring(1);
        	objective.setDisplayName(ChatColor.GOLD+displayname2);
        	
        	Score archery = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.3"))); //$NON-NLS-1$
        	archery.setScore(pd.getData().getInt("Archery"));  //$NON-NLS-1$
        	
        	Score attack = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.5"))); //$NON-NLS-1$
        	attack.setScore(pd.getData().getInt("Attack")); //Integer only! //$NON-NLS-1$
        	
        	Score defence = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.7"))); //$NON-NLS-1$
        	defence.setScore(pd.getData().getInt("Defense")); //$NON-NLS-1$
        	    
        	Score magic = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.9"))); //$NON-NLS-1$
    	    magic.setScore(pd.getData().getInt("Magic")); //$NON-NLS-1$
    	    
    	    Score mining = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.11"))); //$NON-NLS-1$
    	    mining.setScore(pd.getData().getInt("Mining")); //$NON-NLS-1$
    	    
    	    Score woodcutting = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.13"))); //$NON-NLS-1$
    	    woodcutting.setScore(pd.getData().getInt("Woodcutting")); //$NON-NLS-1$

    	    Score combat_exp = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.15"))); //$NON-NLS-1$
    	    combat_exp.setScore(pd.getData().getInt("Combat-EXP")); //$NON-NLS-1$
    	    
    	    Score digging = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.17"))); //$NON-NLS-1$
    	    digging.setScore(pd.getData().getInt("Digging"));    //$NON-NLS-1$
    	    
    	    Score fishing = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + Messages.getString("ScoreboardStuff.19"))); //$NON-NLS-1$
    	    fishing.setScore(pd.getData().getInt("Fishing")); //$NON-NLS-1$
    	       	       	
        	player.setScoreboard(board);
        	

        	
        	
        	
        	
        }	
        

    
}
