package com.paulreitz.reitzrpg;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerUseGatheringListener implements Listener {
	
	Reitzrpgmain plugin;
    public OnPlayerUseGatheringListener(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    }
	

	
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event)
	{
    	if(event.getPlayer().getGameMode() == GameMode.CREATIVE)
    	{
    		return; //no exp for creative!
    	}
		Player p = event.getPlayer();
		ItemStack iteminhand = p.getInventory().getItemInHand();
		int miningpower, woodcuttingpower;
	    //PlayerData pd = new PlayerData(p.getName());
		PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
	    miningpower = pd.getData().getInt("Mining"); //$NON-NLS-1$
	    woodcuttingpower = pd.getData().getInt("Woodcutting"); //$NON-NLS-1$
	    int diggingpower = pd.getData().getInt("Digging"); //$NON-NLS-1$

	    String playerstring = p.toString();

	 
	    if(p.getItemInHand().getType() == Material.WOOD_PICKAXE)
	    {
	    	
	    }
	    else if(p.getItemInHand().getType() == Material.STONE_PICKAXE)
	    {
	    	if(miningpower >= 5)
	    	{
	    		
	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.13")); //$NON-NLS-1$
	    		
	    	}	
	    	
	    }	
	    else if(p.getItemInHand().getType() == Material.IRON_PICKAXE)
	    {
	    	if(miningpower >= 10)
	    	{
	    		
	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.12")); //$NON-NLS-1$
	    		
	    	}	
	    	
	    }
	    else if(p.getItemInHand().getType() == Material.GOLD_PICKAXE)
	    {
	    	if(miningpower >= 15)
	    	{
	    		
	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.11")); //$NON-NLS-1$
	    	}	
	    	
	    }	
	    else if(p.getItemInHand().getType() == Material.DIAMOND_PICKAXE)
	    {
	    	if(miningpower >= 20)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.10")); //$NON-NLS-1$
	    		
	    	}	
	    	
	    }
	    else if(p.getItemInHand().getType() == Material.WOOD_AXE)
	    {
	    	if(miningpower >= 0)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.9")); //$NON-NLS-1$
	    		
	    	}
	    }	
	    else if(p.getItemInHand().getType() == Material.STONE_AXE)
	    {
	    	if(miningpower >= 5)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(Messages.getString("OnPlayerUseGatheringListener.8")); //$NON-NLS-1$
	    		
	    	}
	    }
	    else if(p.getItemInHand().getType() == Material.IRON_AXE)
	    {
	    	if(miningpower >= 10)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.7")); //$NON-NLS-1$
	    		
	    	}
	    }		    
	    else if(p.getItemInHand().getType() == Material.GOLD_AXE)
	    {
	    	if(miningpower >= 15)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.6")); //$NON-NLS-1$
	    		
	    	}
	    }
	    else if(p.getItemInHand().getType() == Material.DIAMOND_AXE &&
	    		p.getItemInHand().getItemMeta().getDisplayName() == null)
	    {
	    	if(miningpower >= 20)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.5")); //$NON-NLS-1$
	    		
	    	}
	    }
    	else if(p.getItemInHand().getType() == Material.DIAMOND_AXE && 
	    		p.getItemInHand().getItemMeta().getDisplayName().equals("BATTLE AXE OF ARCANE MIGHT")) //$NON-NLS-1$
	    	{
    			
    		
    		
	    	}
	    else if(p.getItemInHand().getType() == Material.WOOD_SPADE)
	    {
	    	if(diggingpower >= 1)
	    	{

	    	}
	    	else
	    	{
	    		p.getInventory().removeItem(p.getInventory().getItemInHand());
	    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
	    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.0")); //$NON-NLS-1$
	    		
	    	}
	    }	
		    else if(p.getItemInHand().getType() == Material.STONE_SPADE)
		    {
		    	if(diggingpower >= 5)
		    	{

		    	}
		    	else
		    	{
		    		p.getInventory().removeItem(p.getInventory().getItemInHand());
		    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
		    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.1")); //$NON-NLS-1$
		    		
		    	}	    	
		    }
		    else if(p.getItemInHand().getType() == Material.IRON_SPADE)
		    {
		    	if(diggingpower >= 10)
		    	{

		    	}
		    	else
		    	{
		    		p.getInventory().removeItem(p.getInventory().getItemInHand());
		    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
		    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.2")); //$NON-NLS-1$
		    		
		    	}
		    }
		    else if(p.getItemInHand().getType() == Material.GOLD_SPADE)
		    {
		    	if(diggingpower >= 15)
		    	{

		    	}
		    	else
		    	{
		    		p.getInventory().removeItem(p.getInventory().getItemInHand());
		    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
		    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.3")); //$NON-NLS-1$
		    		
		    	}
		    }
		    else if(p.getItemInHand().getType() == Material.DIAMOND_SPADE)
		    {
		    	if(diggingpower >= 20)
		    	{

		    	}
		    	else
		    	{
		    		p.getInventory().removeItem(p.getInventory().getItemInHand());
		    		p.getWorld().dropItemNaturally(p.getLocation(), iteminhand);
		    		p.sendMessage(ChatColor.RED +Messages.getString("OnPlayerUseGatheringListener.4")); //$NON-NLS-1$
		    		
		    	}
		    }	    
	    
	}   

}
