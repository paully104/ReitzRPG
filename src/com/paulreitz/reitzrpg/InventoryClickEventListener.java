package com.paulreitz.reitzrpg;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;



public class InventoryClickEventListener implements Listener {
	
	  @EventHandler(priority = EventPriority.HIGH)//was lowest
	    public void onInventoryClose(InventoryCloseEvent event) 
	  {
		  PlayerData pd = new PlayerData(event.getPlayer().getName());
		  
	        if (event.getView().getType() == InventoryType.CRAFTING && event.getPlayer() instanceof Player
	        		&& !event.getInventory().getName().equals(myCustomInventory.myInventory))
	        {
	       
	        	Player p = (Player) event.getPlayer();
	        	//						HELMET SECTION                             //
	        	if ( p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType().equals( Material.DIAMOND_HELMET  ) )
	        	{
	        		if( pd.getData().getInt("Defense") < 20)
	        		{
	        			
	        		p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getHelmet() );
	        		p.getInventory().setHelmet( null );
	        		p.sendMessage(ChatColor.RED + "You must be level 20 defense to equip!");
	        		return;
	        		}
	        		else
	        		{
	        			//player puts on item no problem
	        			
	        			return;
	        		}	
	        		
	        	} 
	        	if( p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType().equals( Material.LEATHER_HELMET  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 0)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getHelmet() );
		        		p.getInventory().setHelmet( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 0 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player puts on helmet no problem
	        		}
	        		
	        		
	        	}	
	        	if( p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType().equals( Material.GOLD_HELMET  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 5)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getHelmet() );
		        		p.getInventory().setHelmet( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 5 defense to equip!");
		        		return;
	        		}	
	        		else
	        		{
	        			return;
	        			//player puts on helmet no problem
	        		}
	        		
	        		
	        		
	        	}
	           if( p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType().equals( Material.CHAINMAIL_HELMET  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 10)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getHelmet() );
		        		p.getInventory().setHelmet( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 10 defense to equip!");
		        		return;
		        		
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player puts on helmet no problem
	        		}
	        		
	        		
	        		
	        	}		       	        	
	        	if( p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType().equals( Material.IRON_HELMET  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 15)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getHelmet() );
		        		p.getInventory().setHelmet( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 15 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player puts on helmet no problem
	        		}
	        		
	        		
	        		
	        	}
	        	//                     CHESTPLATE SECTION                                              //
	        	 if ( p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals( Material.DIAMOND_CHESTPLATE  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 20)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getChestplate() );
		        		p.getInventory().setChestplate( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 20 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			//player equips body no problem
	        			return;
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals( Material.LEATHER_CHESTPLATE  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 0)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getChestplate() );
		        		p.getInventory().setChestplate( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 0 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals( Material.GOLD_CHESTPLATE ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 5)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getChestplate() );
		        		p.getInventory().setChestplate( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 5 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals( Material.CHAINMAIL_CHESTPLATE  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 10)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getChestplate() );
		        		p.getInventory().setChestplate( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 10 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals( Material.IRON_CHESTPLATE ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 15)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getChestplate() );
		        		p.getInventory().setChestplate( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 15 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	//                          LEG SECTION               //
	        	 if ( p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType().equals( Material.DIAMOND_LEGGINGS  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 20)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getLeggings() );
		        		p.getInventory().setLeggings( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 20 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType().equals( Material.LEATHER_LEGGINGS  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 0)
	        		{
	        			
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getLeggings() );
		        		p.getInventory().setLeggings( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 0 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType().equals( Material.GOLD_LEGGINGS  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 5)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getLeggings() );
		        		p.getInventory().setLeggings( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 5 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}		        	
	        	 if ( p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType().equals( Material.CHAINMAIL_LEGGINGS  ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 10)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getLeggings() );
		        		p.getInventory().setLeggings( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 10 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getLeggings() != null && p.getInventory().getLeggings().getType().equals( Material.IRON_LEGGINGS ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 15)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getLeggings() );
		        		p.getInventory().setLeggings( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 15 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			//player equips body no problem
	        			return;
	        		}	
	        		
	        		
	        	}
	        	//                   BOOTS SECTION                             //
	        	 if ( p.getInventory().getBoots() != null && p.getInventory().getBoots().getType().equals( Material.DIAMOND_BOOTS ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 20)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getBoots() );
		        		p.getInventory().setBoots( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 20 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}		        	
	        	 if ( p.getInventory().getBoots() != null && p.getInventory().getBoots().getType().equals( Material.LEATHER_BOOTS ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 0)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getBoots() );
		        		p.getInventory().setBoots( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 0 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getBoots() != null && p.getInventory().getBoots().getType().equals( Material.GOLD_BOOTS ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 5)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getBoots() );
		        		p.getInventory().setBoots( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 5 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	 if ( p.getInventory().getBoots() != null && p.getInventory().getBoots().getType().equals( Material.CHAINMAIL_BOOTS ) )
	        	{
	        		if(pd.getData().getInt("Defense") < 10)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getBoots() );
		        		p.getInventory().setBoots( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 10 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}	        	
	        	if ( p.getInventory().getBoots() != null && p.getInventory().getBoots().getType().equals( Material.IRON_BOOTS) )
	        	{
	        		if(pd.getData().getInt("Defense") < 15)
	        		{
	        			p.getWorld().dropItemNaturally( p.getLocation(), p.getInventory().getBoots() );
		        		p.getInventory().setBoots( null );
		        		p.sendMessage(ChatColor.RED + "You must be level 15 defense to equip!");
		        		return;
	        			
	        		}	
	        		else
	        		{
	        			return;
	        			//player equips body no problem
	        		}	
	        		
	        		
	        	}
	        	
	        	
	        }
	        
	           
	    }
}
