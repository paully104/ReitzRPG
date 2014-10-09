package com.paulreitz.reitzrpg;

	
	import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
	 
	public class PlayerBackPack implements Listener
{			
		Reitzrpgmain plugin;
		Inventory backpackinstance;
		
	    public PlayerBackPack(Reitzrpgmain instance) {
	    plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig!     
	    }
	
	    
	    

	        //Inventory PlayerBackPack = Bukkit.createInventory(null, 9, ChatColor.BOLD + "Your Backpack");
	       
	        // The first parameter, is the inventory owner. I make it null to let everyone use it.
	        //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
	        //The third parameter, is the inventory name. This will accept chat colors
	                
	        static {


	        	   }
	    	
	        @EventHandler(priority = EventPriority.LOWEST)
	        public void onInventoryClick(InventoryClickEvent event)
	        {
	        
	        	Player player = (Player) event.getWhoClicked(); // The player that clicked the item
	        	ItemStack clicked = event.getCurrentItem(); // The item that was clicked
	        	Inventory inventory = event.getInventory(); // The inventory that was clicked in
	        	if (inventory.getName().equals("Your Backpack"))
	        	{ 
	        		
	        	}
	        }
	        @EventHandler(priority = EventPriority.LOWEST)
	        public void onInventoryClose(InventoryCloseEvent event)
	        {	
	        	
	        	String player = event.getPlayer().getName();
	        	//PlayerData pd = new PlayerData(player);
	        	PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
	        	Inventory inventory = event.getInventory();
	        	if (inventory.getName().equals("Your Backpack"))
	        			{	

	        				
	        				
	        				
	        				String cheststuff = InventoryStringDeSerializer.InventoryToString(inventory);
	        				//pd.getData().set("BackPack", cheststuff);
	        				PlayerJoinListener.users.get(event.getPlayer().getName()).getData().set("BackPack", cheststuff);
	        				//pd.save();
	        				
	        				
	        			}
	        	
	        	
	        }	
	        @EventHandler(priority = EventPriority.LOWEST)
	        public void onInventoryOpen(InventoryOpenEvent event)
	        {
	        	Inventory inventory = event.getInventory();
	        	String player = event.getPlayer().getName();
	        	PlayerData pd = PlayerJoinListener.users.get(event.getPlayer().getName());
	        	if(inventory.getName().equals("Your Backpack"))


	        		if(pd.getData().getString("BackPack") != null)
	        		{
	        			//the player has successfully set their inventory once
	        			Inventory i = InventoryStringDeSerializer.StringToInventory(pd.getData().getString("BackPack"));
	        			//inventory.setContents(i.getContents());
	        			inventory.setContents(i.getContents());
	        			
	        			
	        		}	
	        		else //their first time setting their inventory or it is blank?
	        		{
	        			System.out.println("CLEAR INVENTORY IS RUNNING?");
	        			System.out.println(pd.getData().getString("BackPack"));
	        			inventory.clear();
	        			
	        		}	
	        			
	        			
	
	        		
	
	        		
	        	
	        	
	        }	
	    
	        
}     	
