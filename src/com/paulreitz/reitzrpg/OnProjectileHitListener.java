package com.paulreitz.reitzrpg;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class OnProjectileHitListener implements Listener {
	
	@EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
   
        if (entity.getType() == EntityType.ARROW) {
            entity.remove();//stops arrows from lagging the system
        }
    }
	
	

}
