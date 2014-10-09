package com.paulreitz.reitzrpg;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class EntityShootBowListener implements Listener {
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onEntityShootBow(EntityShootBowEvent event)
    {
		Entity p = event.getEntity();
		
		//Player player = (Player) event.getEntity();
		//String player2 = player.getDisplayName();

		
		
        if (event.getEntityType().toString().equals("PLAYER"))
        {
        	Player player = (Player) event.getEntity();
			String player2 = player.getDisplayName();
			PlayerData pd = new PlayerData(player2);
			Entity arrow = event.getProjectile();
            if (event.getForce() >= 0.0) //== 1.0 means fully charged
            {

                int arrowcount = pd.getData().getInt("Archery");
                if ( arrowcount > 8 )
                {
                	arrowcount = 8;//set max arrows
                
                }	
                for (int i = 1; i < arrowcount; i++)
                {

                	Vector velocity = event.getProjectile().getVelocity();
                	double speed = velocity.length();
                	Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
                	// you can tune the following value for different spray. Higher number means less spray.
                	//double spray = 3.5D;
                	double spray = 10.5D;
                	double arrowformat = i + 0.00;
                	  Arrow arrowtest = player.launchProjectile(Arrow.class);
                	  arrowtest.setVelocity(new Vector(direction.getX() + (Math.random() - 0.5) / spray, direction.getY() + (Math.random() - 0.5) / spray, direction.getZ() + (Math.random() - 0.5) / spray).normalize().multiply(speed));
                	 // arrowtest.setVelocity(new Vector(direction.getX() +  arrowformat/10, direction.getY() + arrowformat/10 , direction.getZ() + arrowformat/10).multiply(speed));

                    
                }
                if(arrow.toString() != null && arrow.toString() == "Cheap Arrow")
                {
                	ItemStack arrow2 = (ItemStack) arrow;
                	arrow2.setAmount(1);
                	player.getInventory().remove(arrow2);
                	player.updateInventory();
                	
                }	
             
            }
        
        else if(event.getEntity() instanceof Monster)
        {
        	int arrowcount;
        	arrowcount = 2;
        	
        	for (int i = 1; i < arrowcount; i++)
            {

            	Vector velocity = event.getProjectile().getVelocity();
            	double speed = velocity.length();
            	Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
            	// you can tune the following value for different spray. Higher number means less spray.
            	//double spray = 3.5D;
            	double spray = 10.5D;
            	double arrowformat = i + 0.00;
            	  Arrow arrowtest = player.launchProjectile(Arrow.class);
            	  arrowtest.setVelocity(new Vector(direction.getX() + (Math.random() - 0.5) / spray, direction.getY() + (Math.random() - 0.5) / spray, direction.getZ() + (Math.random() - 0.5) / spray).normalize().multiply(speed));
            	 // arrowtest.setVelocity(new Vector(direction.getX() +  arrowformat/10, direction.getY() + arrowformat/10 , direction.getZ() + arrowformat/10).multiply(speed));
            	 
            	
            	
            	
            	//end of stuff
                
            }
        }	
		}
    }//endofentitybowshoot
	
	
	
	

}
