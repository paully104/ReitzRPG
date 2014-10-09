package com.paulreitz.reitzrpg;


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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
 
public class myCustomInventory implements Listener {
        public static Inventory myInventory = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Stat Menu");
        // The first parameter, is the inventory owner. I make it null to let everyone use it.
        //The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
        //The third parameter, is the inventory name. This will accept chat colors
                
        static {
        		//ITEM STACK FOR EXIT INVENTORY
    			ItemStack exit = new ItemStack(Material.RAILS);
    			ItemMeta exit_meta = exit.getItemMeta();
    			exit_meta.setDisplayName("EXIT");
    			exit.setItemMeta(exit_meta);        	
        	
        		//ITEM STACK FOR ATTACK LEVEL
        		ItemStack attack_level = new ItemStack(Material.WOOD_SWORD);
        		ItemMeta attack_level_meta = attack_level.getItemMeta();
        		attack_level_meta.setLore(null);
        		attack_level_meta.setDisplayName("Attack Level");
        		attack_level.setItemMeta(attack_level_meta);
        		//ITEM STACK FOR DEFENCE LEVEL
        		ItemStack defence_level = new ItemStack(Material.LEATHER_CHESTPLATE);
        		ItemMeta defence_level_meta = attack_level.getItemMeta();
        		defence_level_meta.setDisplayName("Defense Level");
        		defence_level.setItemMeta(defence_level_meta);
        		//ITEM STACK FOR Archery LEVEL
        		ItemStack archery_level = new ItemStack(Material.STRING);
        		ItemMeta archery_level_meta = attack_level.getItemMeta();
        		archery_level_meta.setDisplayName("Archery Level");
        		archery_level.setItemMeta(archery_level_meta);        		
        		//ITEM STACK FOR MAGIC LEVEL
        		ItemStack magic_level = new ItemStack(Material.POTION);
        		ItemMeta magic_level_meta = attack_level.getItemMeta();
        		magic_level_meta.setDisplayName("Magic Level");
        		magic_level.setItemMeta(magic_level_meta);
        		//ITEMSTACK FOR FISHING       		
        		myInventory.setItem(0, exit);
                myInventory.setItem(1, attack_level);
                myInventory.setItem(2, defence_level);
                myInventory.setItem(3, archery_level);
                myInventory.setItem(4, magic_level);
                //Woodcutting EXP
        		ItemStack woodcutting_exp = new ItemStack(Material.WOOD_AXE);
        		ItemMeta woodcutting_exp_meta = woodcutting_exp.getItemMeta();
        		woodcutting_exp_meta.setDisplayName("Woodcutting Exp");
        		woodcutting_exp.setItemMeta(woodcutting_exp_meta);
        		//DIGGING EXP
        		ItemStack digging_exp = new ItemStack(Material.WOOD_SPADE);
        		ItemMeta digging_exp_meta = digging_exp.getItemMeta();
        		digging_exp_meta.setDisplayName("Digging Exp");
        		digging_exp.setItemMeta(digging_exp_meta);
        		//MINING EXP
        		ItemStack mining_exp = new ItemStack(Material.WOOD_PICKAXE);
        		ItemMeta mining_exp_meta = mining_exp.getItemMeta();
        		mining_exp_meta.setDisplayName("Mining Exp");
        		mining_exp.setItemMeta(mining_exp_meta);
        		myInventory.setItem(5, woodcutting_exp);
                myInventory.setItem(6, digging_exp);
                myInventory.setItem(7, mining_exp);
                //ITEM STACK FOR ATTACK UPGRADE
        		ItemStack attackup_level = new ItemStack(Material.DIAMOND_SWORD);
        		ItemMeta attackup_level_meta = attackup_level.getItemMeta();
        		attackup_level_meta.setLore(null);
        		attackup_level_meta.setDisplayName("Purchase Attack Up");
        		attackup_level.setItemMeta(attackup_level_meta);
        		//ITEM STACK FOR DEFENCE UPGRADE
        		ItemStack defenceup_level = new ItemStack(Material.DIAMOND_CHESTPLATE);
        		ItemMeta defenceup_level_meta = defenceup_level.getItemMeta();
        		defenceup_level_meta.setLore(null);
        		defenceup_level_meta.setDisplayName("Purchase Defense Up");
        		defenceup_level.setItemMeta(defenceup_level_meta);
        		//ITEM STACK FOR ARCHERY UPGRADE
        		ItemStack archeryup_level = new ItemStack(Material.BOW);
        		ItemMeta archeryup_level_meta = archeryup_level.getItemMeta();
        		archeryup_level_meta.setLore(null);
        		archeryup_level_meta.setDisplayName("Purchase Archery Up");
        		archeryup_level.setItemMeta(archeryup_level_meta);
        		//ITEM STACK FOR MAGIC UPGRADE
        		ItemStack magicup_level = new ItemStack(Material.EYE_OF_ENDER);
        		ItemMeta magicup_level_meta = magicup_level.getItemMeta();
        		magicup_level_meta.setLore(null);
        		magicup_level_meta.setDisplayName("Purchase Magic Up");
        		magicup_level.setItemMeta(magicup_level_meta);
        		myInventory.setItem(18, attackup_level);
                myInventory.setItem(19, defenceup_level);
                myInventory.setItem(20, archeryup_level);
                myInventory.setItem(21, magicup_level);
                //ITEM STACK FOR HP
        		ItemStack healthup_level = new ItemStack(Material.GOLDEN_APPLE);
        		ItemMeta healthup_level_meta = magicup_level.getItemMeta();
        		healthup_level_meta.setLore(null);
        		healthup_level_meta.setDisplayName("Purchase Health Up");
        		healthup_level.setItemMeta(healthup_level_meta);
        		myInventory.setItem(22, healthup_level);
        		//ITEMSTACK FOR BACKPACK
        		ItemStack backpack = new ItemStack(Material.CHEST);
        		ItemMeta backpack_meta = backpack.getItemMeta();
        		backpack_meta.setLore(null);
        		backpack_meta.setDisplayName("Purchase Backpack Space");
        		backpack.setItemMeta(backpack_meta);
        		myInventory.setItem(23, backpack);
        }
        @EventHandler(priority = EventPriority.LOWEST)
        public void onInventoryClick(InventoryClickEvent event)
        {
        	
        	Inventory inventory = event.getInventory(); // The inventory that was clicked in
        	if (inventory.getName().equals(myInventory.getName()))	
        	{ 
        			
            	Player player = (Player) event.getWhoClicked(); // The player that clicked the item
            	PlayerData pd = new PlayerData(player.getName());
            	ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        		// The inventory is our custom Inventory
            		if(clicked == null || clicked.getType() == null)
            		{
            			
            		}	
            		else if (clicked.getType() == Material.RAILS)
        			{ // The item that the player clicked it dirt
        				event.setCancelled(true); // Make it so the dirt is back in its original spot
        				player.closeInventory(); // Closes there inventory
        				//player.getInventory().addItem(new ItemStack(Material.DIRT, 1)); // Adds dirt
        			}	
        			else if(clicked.getType() ==Material.WOOD_SWORD)
        			{
        					event.setCancelled(true);
        					player.closeInventory();
        					int playerattack;
        					
        					playerattack = pd.getData().getInt("Attack");
        					player.sendMessage("Your attack is level: " + playerattack);
        	
        			}	
        			else if(clicked.getType() ==Material.LEATHER_CHESTPLATE)
        			{
        					event.setCancelled(true);
        					player.closeInventory();
        					int playerdefence;
        					
        					playerdefence = pd.getData().getInt("Defense");
        					player.sendMessage("Your defence is level: " + playerdefence);
        	
        			}
        			else if(clicked.getType() ==Material.WOOD_AXE)
        			{
        					event.setCancelled(true);
        					player.closeInventory();
        					
        					int  miningpower = pd.getData().getInt("Woodcutting");
        				    int miningexp = pd.getData().getInt("Woodcutting-EXP");
        				   int mininglevelupcost = (miningpower * 100) + 1;
        				   player.sendMessage(ChatColor.GREEN +"Woodcutting exp: "
        				   + ChatColor.GRAY+ miningexp + " " + ChatColor.GREEN+"Needed for level up: " + mininglevelupcost);
        	
        			}
        			else if(clicked.getType() ==Material.WOOD_SPADE)
        			{
        					event.setCancelled(true);
        					player.closeInventory();
        					
        					int  miningpower = pd.getData().getInt("Digging");
        				    int miningexp = pd.getData().getInt("Digging-EXP");
        				   int mininglevelupcost = (miningpower * 100) + 1;
        				   player.sendMessage(ChatColor.GREEN +"Digging exp: "
        				   + ChatColor.GRAY+ miningexp + " " +ChatColor.GREEN+"Needed for level up: " + mininglevelupcost);
        	
        			}
        			else if(clicked.getType() ==Material.WOOD_PICKAXE)
        			{
        					event.setCancelled(true);
        					player.closeInventory();
        					
        					int  miningpower = pd.getData().getInt("Mining");
        				    int miningexp = pd.getData().getInt("Mining-EXP");
        				   int mininglevelupcost = (miningpower * 100) + 1;
        				   player.sendMessage(ChatColor.GREEN +"Mining exp: "
        				   + ChatColor.GRAY+ miningexp + " " +ChatColor.GREEN+"Needed for level up: " + mininglevelupcost);
        	
        			}
        			else if(clicked.getType() ==Material.STRING)
        			{
        					
        					event.setCancelled(true);
        					player.closeInventory();
        					int playerarchery;
        					
        					playerarchery = pd.getData().getInt("Archery");
        					player.sendMessage("Your archery is level: " + playerarchery);
        	
        			}
        			else if(clicked.getType() ==Material.POTION)
        			{
        				event.setCancelled(true);
    					player.closeInventory();
    					int playerarchery;
    					
    					playerarchery = pd.getData().getInt("Magic");
    					player.sendMessage("Your magic is level: " + playerarchery);
        				
        			}
        			else if(clicked.getType() == Material.DIAMOND_SWORD)
        			{
        				event.setCancelled(true);
    					player.closeInventory();
						//they are purchasing attack power
						int attackupgradecost;
						int currentlevel;
						int currentexp;
						currentlevel = pd.getData().getInt("Attack");
						currentexp = pd.getData().getInt("Combat-EXP");
						attackupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= attackupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +"Attack upgrade: "+ ChatColor.GRAY+ "successful");
							pd.getData().set("Attack", pd.getData().getInt("Attack")+1);
							pd.save();
							currentlevel = pd.getData().getInt("Attack");
							player.sendMessage(ChatColor.GREEN +"Attack is level: "+ ChatColor.GRAY+ currentlevel);
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-attackupgradecost);
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
							
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +"Attack upgrade: "+ ChatColor.GRAY+ "failed");
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY
									+" needed: " + attackupgradecost);
							
						}
        				
        			}
        			else if(clicked.getType() == Material.DIAMOND_CHESTPLATE)
        			{
        				event.setCancelled(true);
    					player.closeInventory();
						int attackupgradecost;
						int currentlevel;
						int currentexp;
						currentlevel = pd.getData().getInt("Defense");
						currentexp = pd.getData().getInt("Combat-EXP");
						attackupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= attackupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +"Defense upgrade: "+ ChatColor.GRAY+ "successful");
							pd.getData().set("Defense", pd.getData().getInt("Defense")+1);
							pd.save();
							currentlevel = pd.getData().getInt("Defense");
							player.sendMessage(ChatColor.GREEN +"Defense is level: "+ ChatColor.GRAY+ currentlevel);
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-attackupgradecost);
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +"Defense upgrade: "+ ChatColor.GRAY+ "failed");
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY
									+" needed: " + attackupgradecost);
						}		
        				
        				
        				
        			}
        			else if(clicked.getType() == Material.EYE_OF_ENDER)
        			{
        				event.setCancelled(true);
    					player.closeInventory();
        				
						int currentlevel = pd.getData().getInt("Magic");
						int currentexp = pd.getData().getInt("Combat-EXP");
						int magicupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= magicupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +"Magic upgrade: "+ ChatColor.GRAY+ "succesful");
							pd.getData().set("Magic", pd.getData().getInt("Magic")+1);
							pd.save();
							currentlevel = pd.getData().getInt("Magic");
							player.sendMessage(ChatColor.BOLD + "Your magic is level " + currentlevel);
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-magicupgradecost);
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
						}	
						else
						{
							player.sendMessage(ChatColor.GREEN +"Magic upgrade: "+ ChatColor.GRAY+ "failed");
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY
									+" needed: " + magicupgradecost);
						}
        				
        				
        			}	
        			else if(clicked.getType() == Material.BOW)
        			{
        				event.setCancelled(true);
    					player.closeInventory();
						int attackupgradecost;
						int currentlevel;
						int currentexp;
						currentlevel = pd.getData().getInt("Archery");
						currentexp = pd.getData().getInt("Combat-EXP");
						attackupgradecost = (25 * currentlevel) * 3;
						if ( currentexp >= attackupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +"Archery upgrade: "+ ChatColor.GRAY+ "succesful");
							pd.getData().set("Archery", pd.getData().getInt("Archery")+1);
							pd.save();
							currentlevel = pd.getData().getInt("Archery");
							player.sendMessage(ChatColor.GREEN + "Archery is level " + currentlevel);
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-attackupgradecost);
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
						}	
						else
						{
							player.sendMessage(ChatColor.GREEN +"Archery upgrade: "+ ChatColor.GRAY+ "failed");
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY
									+" needed: " + attackupgradecost);
							
						}	
        				
        				
        			}
        			else if(clicked.getType() == Material.GOLDEN_APPLE)
        			{
        				event.setCancelled(true);
    					player.closeInventory();
    					int currentlevel;
						int currentexp;
						double healthupgradecost = player.getMaxHealth() * 30;
						currentexp = pd.getData().getInt("Combat-EXP");
						
						if (currentexp >= healthupgradecost)
						{
							player.sendMessage(ChatColor.GREEN +"Health upgrade: "+ ChatColor.GRAY+ "succesful");
							pd.getData().set("Health", pd.getData().getDouble("Health")+2);
							pd.save();
							currentlevel = pd.getData().getInt("Health");
							player.setMaxHealth(currentlevel);
							player.setHealth(currentlevel);
							player.sendMessage(ChatColor.GREEN +"Health is level: "+ ChatColor.GRAY+ currentlevel);
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-healthupgradecost);
							pd.save();
							ScoreboardStuff.manageScoreboard(player, pd.getData().getString("Name"));
						
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +"Health upgrade: "+ ChatColor.GRAY+ "failed");
							player.sendMessage(ChatColor.GREEN + "EXP: " +  currentexp + ChatColor.GRAY
									+" needed: " + healthupgradecost);
						}		

        				
        				
        			}
        			else if(clicked.getType() == Material.CHEST && API.backpackenabled == true)
        			{
    					event.setCancelled(true);
    					player.closeInventory();
    
						int currentsize = pd.getData().getInt("BackPack-Size");
						if(currentsize == 0)
						{
				    		pd.getData().set("BackPack-Size", 9);
				    		pd.getData().set("BackPack", "9;");
				    		pd.save();
							currentsize = pd.getData().getInt("BackPack-Size");
							
						}	
						int exp = pd.getData().getInt("Combat-EXP");
						int upgradecost = (currentsize + 9) * (currentsize*2);
				
						if   ( exp > upgradecost && currentsize < 54 )
						{
							player.sendMessage(ChatColor.GREEN +"Backpack upgrade: "+ ChatColor.GRAY+ "succesful");
							pd.getData().set("BackPack-Size", currentsize + 9);
							pd.getData().set("Combat-EXP", pd.getData().getInt("Combat-EXP")-upgradecost);
							pd.save();
						}
						else
						{
							player.sendMessage(ChatColor.GREEN +"Backpack upgrade: "+ ChatColor.GRAY+ "failed");
							player.sendMessage(ChatColor.GREEN + "EXP: " +  exp + ChatColor.GRAY
									+" needed: " + upgradecost);
							
						}	
        				
        				
        			}	
        			else
        			{
        				
        			}
        			if(event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || event.getAction() == InventoryAction.HOTBAR_SWAP || 
        					event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY || event.getAction() == InventoryAction.COLLECT_TO_CURSOR
        					|| event.getAction() == InventoryAction.DROP_ALL_CURSOR || event.getAction() == InventoryAction.DROP_ALL_SLOT
        					|| event.getAction() == InventoryAction.PICKUP_ONE || event.getAction() == InventoryAction.PLACE_ONE
        					)
        			{
        				System.out.println("Player tried to take items?!");
        				player.setItemOnCursor(null);
        			    event.setCancelled(true);
        			    
        			    
        			}


        			
        			
        	}
        }
}

