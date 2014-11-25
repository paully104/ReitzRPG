package com.paulreitz.reitzrpg;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.paulreitz.reitzrpg.CustomWeaponEngine;
import com.paulreitz.reitzrpg.CustomArmorEngine;

public class CombatEngine {
	
private Entity attacking_entity;
private Entity defending_entity;
private CustomArmorEngine customarmorengine;


public void setAttacking_Entity(Entity e)
{
	attacking_entity = e;
}
public void setDefending_Entity(Entity e)
{
	defending_entity = e;
}




public int calculatePlayerAttackingMonster(Entity player, Entity monster,EntityDamageByEntityEvent event)
{
	Player attacking = (Player)player;
	Entity defending = monster;
	int damage = 0;
	for(Player other : Bukkit.getOnlinePlayers())
	{	
		
		if(other == attacking)
		{	
			System.out.println("A real player is attacking a monster");
		//the source of the damage is the basic attack, so we will grab the attack stat!
		//System.out.println("A player is attacking a monster event");
		PlayerData pd = PlayerJoinListener.users.get(attacking.getName());
		int attack_damage = pd.getData().getInt("Attack");
		double defending_power = DistanceLevel.getSpawnLevel(monster);
		damage = (int) (attack_damage - defending_power);
		if(damage < 1)
			{	
			damage = 1;
			}
		}
		
	}

				
	

	
	
	return damage;
}

public int calculateMonsterAttackingPlayer(Entity player, Entity monster,EntityDamageByEntityEvent event)
{
	Player defending = (Player)player;
	Entity attacking = monster;
	LivingEntity test = (LivingEntity) attacking;
	String name = test.getCustomName();
	String substring = name.substring(Math.max(name.length() - 2, 0));
	//System.out.println(substring);
	int damage = 0;

		//System.out.println("A Monster is attacking a Player event");
		//the source of the damage is the basic attack, so we will grab the attack stat!
		for(Player other : Bukkit.getOnlinePlayers())
		{	
			if(other == defending)
			{	
					PlayerData pd = PlayerJoinListener.users.get(defending.getName()); //grab the playerdata
				int defending_power = pd.getData().getInt("Defense"); //grab player defence
					double attack_damage = DistanceLevel.getSpawnLevel(attacking); //grab monster attack
						if(attack_damage == 0)
							{
								attack_damage = Integer.parseInt(substring);
							}
							damage =  (int) (attack_damage - defending_power); 
							if(damage < 1)
							{
								damage = 1;
							}
		 //calculate damage
			}
			else
			{
				//PLAYER IS ACTUALLY A NPC 
			}
			}

				return damage;
	
	
}
public int calculateProjectileAttackEvent(Arrow arrow, Entity defending, EntityDamageByEntityEvent event)
{
	int damage = 0;
	Entity attacking = (Entity) arrow.getShooter();
	
	if(attacking instanceof Player && defending instanceof Monster)
	{
		//System.out.println("Player VS Monster [ARROWEVENT]");
		//a player is attacking a monster with a projectile attack
		Player player_attacking = (Player) attacking;
		for(Player playercheck : Bukkit.getOnlinePlayers())
		{	
			if(playercheck == player_attacking)
			{
				    PlayerData pd = PlayerJoinListener.users.get(player_attacking.getName()); //grab the playerdata
					int archery = pd.getData().getInt("Archery"); //grab player defence
					double defense = DistanceLevel.getSpawnLevel(defending); //grab monster attack
					damage =  (int) (archery - defense); 
					if(damage < 1)
					{
			        damage = 1;
					}
					return damage;
					}
		}
		
	}
	else if(attacking instanceof Monster && defending instanceof Player)
	{
		//System.out.println("Monster VS Player [ARROWEVENT]");
		//player is getting hit by a projectile attack
		Player player_defending = (Player) defending;
		for(Player playercheck : Bukkit.getOnlinePlayers())
		{	
			if(playercheck == player_defending)
			{
					PlayerData pd = PlayerJoinListener.users.get(player_defending.getName()); //grab the playerdata
					int defense = pd.getData().getInt("Defense"); //grab player defence
					double attack = DistanceLevel.getSpawnLevel(attacking); //grab monster attack
					damage =  (int) (attack - defense); 
					if(damage < 1)
					{
						damage = 1;
					}
					return damage;
			}
		}
		
		
	}
	else if(attacking instanceof Player && defending instanceof Player)
	{
		//PVP
		
		if(damage < 1)
		{
			damage = 1;
		}
		return damage;
		
	}
	
	
	
	
	return damage;
	
}







}//end of file