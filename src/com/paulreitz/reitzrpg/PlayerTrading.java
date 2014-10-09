package com.paulreitz.reitzrpg;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerTrading implements Listener{
	
	Reitzrpgmain plugin;
	static Inventory inventoryinstance;
	static HashMap<String, Inventory> hm = new HashMap<String, Inventory>();
    public PlayerTrading(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    }


	public static Inventory TradeMenu = Bukkit.createInventory(null, 9, ChatColor.BOLD + "LEFT-SIDE  3 Items");
	public static Inventory TradeMenu2 = Bukkit.createInventory(null, 9, ChatColor.BOLD + "RIGHT-SIDE 3 Items");
	public static Inventory FinalMenu = Bukkit.createInventory(null, 9, ChatColor.BOLD + "Final Trade Menu");
    public  static Inventory FinalMenuReset = Bukkit.createInventory(null, 9, ChatColor.BOLD + "Final Trade Menu");
    static{
	ItemStack trade = new ItemStack(Material.GRASS);
	ItemMeta trade_meta = trade.getItemMeta();
	trade_meta.setLore(null);
	trade_meta.setDisplayName("Put Up For Trade");
	trade.setItemMeta(trade_meta);
	ItemStack border = new ItemStack(Material.DIRT);
	ItemMeta border_meta = border.getItemMeta();
	border_meta.setLore(null);
	border_meta.setDisplayName("|");
	border.setItemMeta(border_meta);
	TradeMenu.setItem(3, trade);
	TradeMenu.setItem(4, border);
	TradeMenu.setItem(5, border);
	TradeMenu.setItem(6, border);
	TradeMenu.setItem(7, border);
	//blocks arent getting placed at 5-6-7 on left side?
	TradeMenu.setItem(8, border);
	TradeMenu2.setItem(0, border);
	TradeMenu2.setItem(1, border);
	TradeMenu2.setItem(2, border);
	TradeMenu2.setItem(3, border);
	TradeMenu2.setItem(4, border);
	TradeMenu2.setItem(5, trade);
	//agree
	ItemStack agree = new ItemStack(Material.GRASS);
	ItemMeta agree_meta = agree.getItemMeta();
	agree_meta.setLore(null);
	agree_meta.setDisplayName("Agree To Trade #1");
	agree.setItemMeta(agree_meta);
	//AGREE #2
	ItemStack agree2 = new ItemStack(Material.GRASS);
	ItemMeta agree2_meta = agree2.getItemMeta();
	agree2_meta.setLore(null);
	agree2_meta.setDisplayName("Agree To Trade #2");
	agree2.setItemMeta(agree2_meta);
	//CANCEL
	ItemStack cancel = new ItemStack(Material.RAILS);
	ItemMeta cancel_meta = cancel.getItemMeta();
	cancel_meta.setLore(null);
	cancel_meta.setDisplayName("CANCEL TRADE");
	cancel.setItemMeta(cancel_meta);
	
	FinalMenu.setItem(3, agree);
	FinalMenu.setItem(4, cancel);
	FinalMenu.setItem(5, agree2);
	FinalMenuReset.setItem(3, agree);
	FinalMenuReset.setItem(4, cancel);
	FinalMenuReset.setItem(5, agree2);
	// Left-Side Player A = slot 0 accept 1-2-3 are for trading, 4-8 blocked
	// Right-Side Player B = slot 8 accept 5-6-7 are for trading 0-4 blocked
	//Final Menu has a accept that needs confirmation from both parties at slot 4-5
    }
    
    public void openfinalmenu(final Player player)
    {
    	plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    		
    		  public void run() {
    			  
    			  ItemStack one = FinalMenu.getItem(3);
    			  ItemStack two = FinalMenu.getItem(4);
    			  ItemStack three = FinalMenu.getItem(5);
    			  Inventory inquestion = (hm.get(player.getName()));
    			  inquestion.setItem(3, one);
    			  inquestion.setItem(4, two);
    			  inquestion.setItem(5, three);
    			  player.openInventory(hm.get(player.getName()));
    			 
    			  //FinalMenu.clear();
    		  }
    		  
    		}, 1L);

    	
    }	
    	

    	
    
    @EventHandler
    public void inventoryclosevent2(InventoryCloseEvent event)
    {
    	
    	ItemStack cleanup = new ItemStack(Material.AIR);
    	
    	if(event.getInventory().getName().equals(TradeMenu.getName()))
    	{//LEFT SIDE
    		
    		
    		event.getInventory().setItem(0, cleanup);
    		event.getInventory().setItem(1, cleanup);
    		event.getInventory().setItem(2, cleanup);
    	}
    	else if(event.getInventory().getName().equals(TradeMenu2.getName()))
    			{//RIGHT SIDE

    		event.getInventory().setItem(8, cleanup);
    		event.getInventory().setItem(7, cleanup);
    		event.getInventory().setItem(6, cleanup);
    		
    			}
    	else if(event.getInventory().getName().equals(FinalMenu.getName()))
    	{
			ItemStack tradeaccept0 = event.getInventory().getItem(0);
			ItemStack tradeaccept1 = event.getInventory().getItem(1);
			ItemStack tradeaccept2 = event.getInventory().getItem(2);
			ItemStack tradeaccept6 = event.getInventory().getItem(6);
			ItemStack tradeaccept7 = event.getInventory().getItem(7);
			ItemStack tradeaccept8 = event.getInventory().getItem(8);
			String playerleft;
			String playerright;
			Player playerleft2;
			Player playerright2;
			//String string = "004-034556";
			//String[] parts = string.split("-");
			//String part1 = parts[0]; // 004
			//String part2 = parts[1]; // 034556
			if (tradeaccept0 == null)
			{
			}
			else
			{
				// 0 , 1 ,2 go back to the left side guy
			playerleft = tradeaccept0.getItemMeta().getDisplayName();
			String[] stringsplit = playerleft.split(" ");
			playerleft2 = Bukkit.getPlayer(stringsplit[0]);
			ItemMeta rename = tradeaccept0.getItemMeta();
			rename.setDisplayName(stringsplit[1]);
			tradeaccept0.setItemMeta(rename);
			playerleft2.getInventory().addItem(tradeaccept0);
			event.getInventory().setItem(0, cleanup);
			
			}
			if (tradeaccept1 == null)
			{
			}
			else
			{
				// 0 , 1 ,2 go back to the left side guy
			playerleft = tradeaccept1.getItemMeta().getDisplayName();
			String[] stringsplit = playerleft.split(" ");
			playerleft2 = Bukkit.getPlayer(stringsplit[0]);
			ItemMeta rename = tradeaccept1.getItemMeta();
			rename.setDisplayName(stringsplit[1]);
			tradeaccept1.setItemMeta(rename);
			playerleft2.getInventory().addItem(tradeaccept1);
			event.getInventory().setItem(1, cleanup);
			}
			if (tradeaccept2 == null)
			{
			}
			else
			{
				// 0 , 1 ,2 go back to the left side guy
			playerleft = tradeaccept2.getItemMeta().getDisplayName();
			String[] stringsplit = playerleft.split(" ");
			playerleft2 = Bukkit.getPlayer(stringsplit[0]);
			ItemMeta rename = tradeaccept2.getItemMeta();
			rename.setDisplayName(stringsplit[1]);
			tradeaccept2.setItemMeta(rename);
			playerleft2.getInventory().addItem(tradeaccept2);
			event.getInventory().setItem(2, cleanup);
			}
			if (tradeaccept6 == null)
			{
			}
			else
			{
				// 0 , 1 ,2 go back to the left side guy
			playerright = tradeaccept6.getItemMeta().getDisplayName();
			String[] stringsplit = playerright.split(" ");
			playerright2 = Bukkit.getPlayer(stringsplit[0]);
			ItemMeta rename = tradeaccept6.getItemMeta();
			rename.setDisplayName(stringsplit[1]);
			tradeaccept6.setItemMeta(rename);
			playerright2.getInventory().addItem(tradeaccept6);
			event.getInventory().setItem(6, cleanup);
			}
			if (tradeaccept7 == null) 
			{
			}
			else
			{
				// 0 , 1 ,2 go back to the left side guy
			playerright = tradeaccept7.getItemMeta().getDisplayName();
			String[] stringsplit = playerright.split(" ");
			playerright2 = Bukkit.getPlayer(stringsplit[0]);
			ItemMeta rename = tradeaccept7.getItemMeta();
			rename.setDisplayName(stringsplit[1]);
			tradeaccept7.setItemMeta(rename);
			playerright2.getInventory().addItem(tradeaccept7);
			event.getInventory().setItem(7, cleanup);
			}
			if (tradeaccept8 == null)
			{
			}
			else
			{
				// 0 , 1 ,2 go back to the left side guy
			playerright = tradeaccept8.getItemMeta().getDisplayName();
			String[] stringsplit = playerright.split(" ");
			playerright2 = Bukkit.getPlayer(stringsplit[0]);
			ItemMeta rename = tradeaccept8.getItemMeta();
			rename.setDisplayName(stringsplit[1]);
			tradeaccept8.setItemMeta(rename);
			playerright2.getInventory().addItem(tradeaccept8);
			event.getInventory().setItem(8, cleanup);
			}   		
    		event.getInventory().setContents(FinalMenuReset.getContents()); //reset it 

    		
    	}	
    	
    	
    }	
	
    
    @EventHandler
    public void inventorymove(InventoryClickEvent event)
    {	
    	if(event.getInventory().getName().equals(TradeMenu.getName()))
    			{
    				//LEFT SIDE 
    				
    				ItemStack items = event.getCurrentItem();
    				
    				if(items == null)
    				{
    					
    					//event.getWhoClicked().getInventory().remove(items);
    					
    				}
	
    				 //player.getInventory().addItem(ItemStack); , add to their inventory no problem
    			
    				else if (items.getType() == Material.GRASS && 
    						items.getItemMeta().getDisplayName().equals("Put Up For Trade"))
    				{
    					
    					Inventory test = event.getInventory();
    					
    					//ItemStack item1 = TradeMenu.getItem(1); //now using clones?
    					 ItemStack item1 = event.getInventory().getItem(0);
    					 ItemStack item2 = event.getInventory().getItem(1);
    					 ItemStack item3 = event.getInventory().getItem(2);
    					
    					//ItemStack[] teststack = test.getContents();   					
    					//System.out.println(teststack + " this is the contents of the stuff");  					
    					//FinalMenu.setContents(teststack);

    						if( item1 == null)
    						{
    						}
    						else{
    							ItemMeta itemMeta1 = item1.getItemMeta();
    						itemMeta1.setDisplayName(event.getWhoClicked().getName() + " " + item2.getType().toString());
    						item1.setItemMeta(itemMeta1);
    						}
    						if( item2 == null)
    						{
    						}
    						else
    						{
    							 ItemMeta itemMeta2 = item2.getItemMeta();
    						itemMeta2.setDisplayName(event.getWhoClicked().getName() + " " + item2.getType().toString());
    						item2.setItemMeta(itemMeta2);
    						}
    						if( item3 == null)
    						{
    						}
    						else
    						{
    							ItemMeta itemMeta3 = item3.getItemMeta();
    						itemMeta3.setDisplayName(event.getWhoClicked().getName() + " " + item2.getType().toString());
    						item3.setItemMeta(itemMeta3);
    						}
    						FinalMenu.setItem(0, item1);
    						FinalMenu.setItem(1, item2);
    						FinalMenu.setItem(2, item3);
    						Inventory superdupertest = hm.get(event.getWhoClicked().getName());
    						superdupertest.setItem(0, item1);
    						superdupertest.setItem(1, item2);
    						superdupertest.setItem(2, item3);
    						
    					
    					
    					event.getWhoClicked().closeInventory();//SYNC ISSUE
    						//TEST OF DELAY

    					items = null;
    					openfinalmenu((Player) event.getWhoClicked());
    					
    					
    							
    	    					//event.getWhoClicked().openInventory(FinalMenu);

    					
    					
    					
    					
    				}	
    				else
    				{
    					
    					
    				}
    				
    				
    			}
    	if(event.getInventory().getName().equals(TradeMenu2.getName()))
		{
    		//RIGHT SIDE SLOTS 4-7 = WHERE THEY PUT STUFF
    		
			ItemStack items = event.getCurrentItem();
			
			if(items == null)
			{
				
				//event.getWhoClicked().getInventory().remove(items);
				
			}

			 //player.getInventory().addItem(ItemStack); , add to their inventory no problem
		
			else if (items.getType() == Material.GRASS && 
					items.getItemMeta().getDisplayName().equals("Put Up For Trade"))
			{
				
				Inventory test = event.getInventory();
				
				 //ItemStack item1 = TradeMenu2.getItem(6);
				 //ItemStack item2 = TradeMenu2.getItem(7);
				 //ItemStack item3 = TradeMenu2.getItem(8);
				 ItemStack item1 = event.getInventory().getItem(6);
				 ItemStack item2 = event.getInventory().getItem(7);
				 ItemStack item3 = event.getInventory().getItem(8);
				 
				
				 
				 
				
					
					
					
					if( item1 == null)
					{
					}
					else{
						ItemMeta itemMeta1 = item1.getItemMeta();
						itemMeta1.setDisplayName(event.getWhoClicked().getName() + " " + item1.getType().toString());
						item1.setItemMeta(itemMeta1);
					}
					if( item2 == null)
					{
					}
					else
					{
						ItemMeta itemMeta2 = item2.getItemMeta();
						itemMeta2.setDisplayName(event.getWhoClicked().getName() + " " + item2.getType().toString());
						item2.setItemMeta(itemMeta2);
					}
					if( item3 == null)
					{
					}
					else
					{
						 ItemMeta itemMeta3 = item3.getItemMeta();
						 itemMeta3.setDisplayName(event.getWhoClicked().getName() + " " + item3.getType().toString());
						 item3.setItemMeta(itemMeta3);
					}
				
				FinalMenu.setItem(6, item1);
				FinalMenu.setItem(7, item2);
				FinalMenu.setItem(8, item3); 
				Inventory superdupertest = hm.get(event.getWhoClicked().getName());
				superdupertest.setItem(6, item1);
				superdupertest.setItem(7, item2);
				superdupertest.setItem(8, item3);
				
				event.getWhoClicked().closeInventory();//SYNC ISSUE
				//TEST OF DELAY
				items = null;
						openfinalmenu((Player) event.getWhoClicked());

		
    			    	//event.getWhoClicked().openInventory(FinalMenu);
				
				
				
			}	
			else
			{
				
				
			}
			
			
		}
    	if(event.getInventory().getName().equals(FinalMenu.getName()))
    	{
    		event.setCancelled(true);
    		event.setResult(Result.DENY);
    		
			ItemStack items = event.getCurrentItem();
			
			if(items == null || items.equals(Material.AIR))
			{
				//do nothing because they clicked outside
				
				
			}

			else if (items.getType() == Material.GRASS && 
					items.getItemMeta().getDisplayName().equals("Agree To Trade #1")  
					/*items.getItemMeta().getDisplayName().equals(event.getWhoClicked()*/)
			{	
				System.out.println("TRADE 1 EVENT HAPPENED");
				 ItemMeta itemMeta = items.getItemMeta();
				itemMeta.setDisplayName(event.getWhoClicked().getName().toString());
				ItemStack block = new ItemStack(Material.WOOL);
				block.setItemMeta(itemMeta);
				//FinalMenu.setItem(3, block); //clone update
				event.getInventory().setItem(3, block);
				ItemStack[] updatetest = event.getInventory().getContents();
				//FinalMenu.clear();
				//FinalMenu.setContents(updatetest);
				//FinalMenu.getItem(3).setItemMeta(itemMeta);
				event.getInventory().setContents(updatetest);
				event.getInventory().getItem(3).setItemMeta(itemMeta); //clone testing
				
			}	
			else if (items.getType() == Material.GRASS && 
					items.getItemMeta().getDisplayName().equals("Agree To Trade #2"))
			{
				System.out.println("TRADE 2 EVENT HAPPENED");
				 ItemMeta itemMeta = items.getItemMeta();
				itemMeta.setDisplayName(event.getWhoClicked().getName().toString());
				ItemStack block = new ItemStack(Material.WOOL);
				block.setItemMeta(itemMeta);
				//FinalMenu.getItem(5).setItemMeta(itemMeta);//add the player name meta
				//FinalMenu.setItem(5, block); clone testing
				event.getInventory().getItem(5).setItemMeta(itemMeta);//add the player name meta
				event.getInventory().setItem(5, block);
				ItemStack[] updatetest = event.getInventory().getContents();
				//FinalMenu.clear();
				event.getInventory().setContents(updatetest);
				event.getInventory().getItem(5).setItemMeta(itemMeta);
				
			}	
			else
			{
				
			}
			if(event.getInventory().getItem(3) == null //changing all FinalMenu to event.getinventory
				|| event.getInventory().getItem(5) == null)
			{
				//do nothing for they have no meta
				
				
			}
			else if (event.getInventory().getItem(4).getType() == Material.SKULL && 
					items.getItemMeta().getDisplayName().equals("Cancel Trade"))
			{
				
			}
			else if(event.getInventory().getItem(3).getItemMeta().getDisplayName() != null
					|| event.getInventory().getItem(5).getItemMeta().getDisplayName() != null)
						{
							if (event.getInventory().getItem(3).getItemMeta().getDisplayName() != 
									event.getInventory().getItem(5).getItemMeta().getDisplayName()
									&& ! event.getInventory().getItem(3).getItemMeta().getDisplayName().equals("Agree To Trade #1") 
									&& ! event.getInventory().getItem(5).getItemMeta().getDisplayName().equals("Agree To Trade #2"))
							{

								ItemStack tradeaccept0 = event.getInventory().getItem(0);
								ItemStack tradeaccept1 = event.getInventory().getItem(1);
								ItemStack tradeaccept2 = event.getInventory().getItem(2);
								ItemStack tradeaccept6 = event.getInventory().getItem(6);
								ItemStack tradeaccept7 = event.getInventory().getItem(7);
								ItemStack tradeaccept8 = event.getInventory().getItem(8);
								String playerleft = event.getInventory().getItem(3).getItemMeta().getDisplayName();
								String playerright = event.getInventory().getItem(5).getItemMeta().getDisplayName();
								Player playerleft2 = Bukkit.getPlayerExact(playerleft);
								Player playerright2 = Bukkit.getPlayerExact(playerright);


								//start of exchange
								if(tradeaccept0 == null)
								{
								}
								else{
									playerright2.getInventory().addItem(tradeaccept0);
									event.getInventory().remove(tradeaccept0);
									}
								
								if(tradeaccept1 == null)
								{
									
								}
								else
								{
									playerright2.getInventory().addItem(tradeaccept1);
									event.getInventory().remove(tradeaccept1);
								}
								if(tradeaccept2 == null)
								{
									
								}
								else
								{
									playerright2.getInventory().addItem(tradeaccept2);
									event.getInventory().remove(tradeaccept2);
								}
								//
								if(tradeaccept6 == null)
								{
								}
								else
								{
									playerleft2.getInventory().addItem(tradeaccept6);
									event.getInventory().remove(tradeaccept6);
								}
								if(tradeaccept7 == null)
								{
								}
								else
								{
									playerleft2.getInventory().addItem(tradeaccept7);
									event.getInventory().remove(tradeaccept7);
									
									
								}
								if(tradeaccept8 == null)
								{
									
								}
								else
								{
									playerleft2.getInventory().addItem(tradeaccept8);
									event.getInventory().remove(tradeaccept8);
								
								
								event.getInventory().setContents(FinalMenuReset.getContents());
								playerleft2.closeInventory();
								playerright2.closeInventory();
								//FinalMenu.setContents(FinalMenuReset.getContents());
								List<HumanEntity> Players = event.getInventory().getViewers();
								for(HumanEntity playerz : Players)
								{
									playerz.closeInventory();
									hm.remove(playerz.getName());
									
								}	
								}
							}	
					
							
						}	
				
				
				
				
    	}	
    	
    	
    	
    }
    

    


}
