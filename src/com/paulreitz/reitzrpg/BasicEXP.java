package com.paulreitz.reitzrpg;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class BasicEXP implements Listener
{

/*	@EventHandler
	public void normalmonsterdeath(EntityDeathEvent event)
	{
	
	if (event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof Monster)
	{
    	if(event.getEntity().getKiller().getPlayer().getGameMode() == GameMode.CREATIVE)
    	{
    		return; //no exp for creative!
    	}
		Entity monster = event.getEntity();
		double newexperiencedrop = event.getDroppedExp();//exp multiplier is 3 x the level
		Player player = event.getEntity().getKiller();
    	if(player.getGameMode() == GameMode.CREATIVE)
    	{
    		return; //no exp for creative!
    	}
		PlayerData pd = new PlayerData(player.getName());
		pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")+newexperiencedrop);
		pd.save();
		ScoreboardStuff.manageScoreboard(player, "TeamName");
		
	}
		
}
*/
}