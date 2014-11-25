package com.paulreitz.reitzrpg;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CustomWeapons implements Listener
{
	static Plugin plugin = Reitzrpgmain.getPlugin();
	static List<String>list;
	static int size;
	
    public CustomWeapons(Reitzrpgmain instance) {
        plugin = instance;   
    }

	public static  void onEnableLoadWeapons()
	{
	//Set<String> homes = plugin.getConfig().getConfigurationSection("CustomWeapons").getKeys(false);//true outputs all subkeys
	Set<String> homes = API.getConfiguration().getConfigurationSection("CustomWeapons").getKeys(false);
	/*List<String>*/ list = new ArrayList<String>(homes);
	size = homes.size();
	
	for(int i = 0; i<size; i++)
	{
		String weapon = list.get(i);
		//int material = plugin.getConfig().getInt("CustomWeapons."+weapon+".Material"); //268 woodensword
	    //String name = plugin.getConfig().getString("CustomWeapons."+weapon+".Name");
		int material = API.getConfiguration().getInt("CustomWeapons."+weapon+".Material"); //268 woodensword
	    String name = API.getConfiguration().getString("CustomWeapons."+weapon+".Name");
	    if (name == null)
	    {
	    	name = "ERROR";
	    	
	    }	
		//ItemStack material2 = new ItemStack(material);
		Material a = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Topleft"));
		Material b = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Topmid"));
		Material c = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Topright"));
		Material d = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Midleft"));
		Material e = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Midmid"));
		Material f = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Midright"));
		Material g = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Botleft"));
		Material h = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Botmid"));
		Material i2 = Material.getMaterial(plugin.getConfig().getInt("CustomWeapons."+weapon+".Botright"));
		
		String enchantment = plugin.getConfig().getString("CustomWeapons."+weapon+".Enchantment");
		int enchantlevel = plugin.getConfig().getInt("CustomWeapons."+weapon+".EnchantmentLevel");
	    ItemStack local = new ItemStack(material);
	    
	    if(enchantment != null && enchantlevel != 0)
	    {
	   local.addUnsafeEnchantment(Enchantment.getByName(enchantment), enchantlevel);
	    }

	    ItemMeta local_meta = local.getItemMeta();
	    
	    local_meta.setDisplayName(name);
	    String lore1 = plugin.getConfig().getString("CustomWeapons."+weapon+".Lore.Top");	    
	    String lore2 = plugin.getConfig().getString("CustomWeapons."+weapon+".Lore.Mid");
	    String lore3 = plugin.getConfig().getString("CustomWeapons."+weapon+".Lore.Bot");
	    int min = plugin.getConfig().getInt("CustomWeapons."+weapon+".Mindamage");
		int max = plugin.getConfig().getInt("CustomWeapons."+weapon+".Maxdamage");
		int level = plugin.getConfig().getInt("CustomWeapons."+weapon+".Level");
		String levelamount = "Level " + level + " required"; 
	    String damageamount = min + " - " + max + " Attack Damage ";
	    if(lore1 != null && lore2 != null && lore3 != null && damageamount != null
	    		&& levelamount != null)
	    {
	    	String colorlore1 = ChatColor.translateAlternateColorCodes('&', lore1);
	    	String colorlore2 = ChatColor.translateAlternateColorCodes('&', lore2);
	    	String colorlore3 = ChatColor.translateAlternateColorCodes('&', lore3);
	    local_meta.setLore(Arrays.asList(colorlore1,colorlore2,colorlore3,damageamount,levelamount));
	    }
	    else if(lore1 == null && lore2 == null && lore3 == null)
	    {

	    	local_meta.setLore(Arrays.asList(damageamount,levelamount));
	    	
	    }	
	    else if(lore1 != null && lore2 == null && lore3 == null)
	    {
	    	String colorlore1 = ChatColor.translateAlternateColorCodes('&', lore1);
	    	local_meta.setLore(Arrays.asList(colorlore1,damageamount,levelamount));
	    	
	    }
	    else if(lore1 != null && lore2 != null && lore3 == null)
	    {
	    	String colorlore1 = ChatColor.translateAlternateColorCodes('&', lore1);
	    	String colorlore2 = ChatColor.translateAlternateColorCodes('&', lore2);
	    	local_meta.setLore(Arrays.asList(colorlore1,colorlore2,damageamount,levelamount));
	    	
	    }			    
	    local.setItemMeta(local_meta);
	 
	 
	    ShapedRecipe local_recipe = new ShapedRecipe(new ItemStack(local));
	    
	    local_recipe.shape(new String[] {"ABC", "DEF", "GHI"});
	    if( !a.equals(Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('A', a);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("A");
	    }	
	    if( !b.equals(Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('B', b);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("B");
	    }	
	    if( !c.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('C', c);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("C");	    	
	    }	
	    if( !d.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('D', d);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("D");	
	    }	
	    if( !e.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('E', e);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("E");	
	    }	
	    if( !f.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('F', f);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("F");	
	    }	
	    if( !g.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('G', g);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("G");	
	    }	
	    if( !h.equals( Material.getMaterial(0)))
	    {
	    local_recipe.setIngredient('H', h);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("H");	
	    }	
	    if( !i2.equals( Material.getMaterial(0)))
	    {	    
	    local_recipe.setIngredient('I', i2);
	    }
	    else
	    {
	    	local_recipe.getIngredientMap().remove("I");	
	    }	
	    Bukkit.getServer().addRecipe(local_recipe);

		
		
	}	
	
	}//end of on enable the weapons for creation
	
/*	
	@EventHandler(ignoreCancelled = true,priority = EventPriority.HIGHEST)//was lowest now Highest
	public void onCustomDamageEvent(EntityDamageByEntityEvent event)
	{
		if (event.isCancelled()) { return; }
		//Set<String> homes = plugin.getConfig().getConfigurationSection("CustomWeapons").getKeys(false);//true outputs all subkeys
		//List<String> list = new ArrayList<String>(homes);
		//int size = homes.size();
		if ( event.getDamager() instanceof Player)
		{
			for(int i = 0; i<size; i++)
			{
				String weapon = list.get(i);
				 
			    String name = plugin.getConfig().getString("CustomWeapons."+weapon+".Name");
			    Player player = (Player) event.getDamager();
			    if (player.getItemInHand().hasItemMeta() == false
			    		|| player.getItemInHand().getItemMeta().hasDisplayName() == false
			    		|| !(player.getItemInHand().getItemMeta().getDisplayName()).equals(name))
			    		{
			    			return;
			    		}
			    else if (player.getItemInHand().hasItemMeta() == true
			    		&& ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).equals(name))
			    		{
			    			//Min + (int)(Math.random() * ((Max - Min) + 1))
			    			PlayerData pd = new PlayerData(player.getName());
			    			int min = plugin.getConfig().getInt("CustomWeapons."+weapon+".Mindamage");
			    			int max = plugin.getConfig().getInt("CustomWeapons."+weapon+".Maxdamage");
			    			int level = plugin.getConfig().getInt("CustomWeapons."+weapon+".Level");
			    			Random random = new Random();
			    			int damage = random.nextInt(max - min) +min;
			    			int playerdamage = damage + (pd.getData().getInt("Attack")/plugin.getConfig().getInt("Attack_Modifier"));
			    			if(pd.getData().getInt("Attack") < level)
			    			{
			    				event.setCancelled(true);
			    				event.setDamage(0);
			    				
			    				
			    			}	
			    			if(pd.getData().getInt("Attack") >= level)
			    			{
			    			event.setDamage(playerdamage);
			    			
			    			}

			    			
			    		}	
			}   
			
			
		}	
		
		
	}
	*/
	@EventHandler
	public void onCustomWeaponEquip(EntityDamageByEntityEvent event)//playerinteractevent
	{
		//Set<String> homes = plugin.getConfig().getConfigurationSection("CustomWeapons").getKeys(false);//true outputs all subkeys
		//List<String> list = new ArrayList<String>(homes);
		//int size = homes.size();
		
		for(int i=0; i<size; i++)
		{
			String weapon = list.get(i);
			String name = plugin.getConfig().getString("CustomWeapons."+weapon+".Name");
			int level = plugin.getConfig().getInt("CustomWeapons."+weapon+".Level");
			if(event.getDamager() instanceof Player)
			{	
			Player player = (Player) event.getDamager();
			//PlayerData pd = new PlayerData(player.getName()); old system changed OCtober 13th,2014
			PlayerData pd = PlayerJoinListener.users.get(player.getName());
			if(player.getItemInHand() == null || player.getItemInHand().hasItemMeta() == false
				|| player.getItemInHand().getItemMeta().getDisplayName() == null)
			{
				
			}	
			else if (player.getItemInHand().hasItemMeta() == true
		    		&& ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).equals(name))
		    		{
						 if(pd.getData().getInt("Attack") < level)
						 {
							 event.setCancelled(true);
							 //player can't equip
							 final int slot = player.getInventory().getHeldItemSlot();
							 player.sendMessage(ChatColor.RED + "You must be level: " + level + " to equip!");
							 
							 if(player.getInventory().getHeldItemSlot() < 8)
			    	    		{
			    	    			player.getInventory().setHeldItemSlot(slot + 1);
			    	    			//$NON-NLS-1$
			    	    			return;
			    	    		}
			    	    		else
			    	    		{
			    	    			player.getInventory().setHeldItemSlot(slot - 1);
			    	    			return;
			    	    			 //$NON-NLS-1$
			    	    		}
							
						 } 
				
				
		    		}
			
		}	
		}
		
		
	}	
	

}
