package com.paulreitz.reitzrpg;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockRightClick implements Listener {
	
	// Add your items to check here, I've just added 2 armor parts
	private List<Material> armorMaterials = Arrays.asList(Material.LEATHER_BOOTS,
			Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET,
			Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET,
			Material.CHAINMAIL_LEGGINGS, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS,
			Material.GOLD_CHESTPLATE, Material.GOLD_HELMET, Material.DIAMOND_BOOTS,
			Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET);
	 
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onArmEquip(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getPlayer().getItemInHand().getType().equals(Material.LEATHER_BOOTS) || e.getPlayer().getItemInHand().getType().equals(Material.LEATHER_CHESTPLATE) ||
					e.getPlayer().getItemInHand().getType().equals(Material.LEATHER_HELMET) || e.getPlayer().getItemInHand().getType().equals(Material.LEATHER_LEGGINGS) || 
					e.getPlayer().getItemInHand().getType().equals(Material.IRON_BOOTS) || e.getPlayer().getItemInHand().getType().equals(Material.IRON_CHESTPLATE) || 
					e.getPlayer().getItemInHand().getType().equals(Material.IRON_HELMET) || e.getPlayer().getItemInHand().getType().equals(Material.IRON_LEGGINGS) ||
					e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_BOOTS) || e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_CHESTPLATE) ||
					e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HELMET) || e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_LEGGINGS) ||
					e.getPlayer().getItemInHand().getType().equals(Material.GOLD_BOOTS) || e.getPlayer().getItemInHand().getType().equals(Material.GOLD_CHESTPLATE) ||
					e.getPlayer().getItemInHand().getType().equals(Material.GOLD_HELMET) || e.getPlayer().getItemInHand().getType().equals(Material.GOLD_LEGGINGS) ||
					e.getPlayer().getItemInHand().getType().equals(Material.CHAINMAIL_BOOTS) || e.getPlayer().getItemInHand().getType().equals(Material.CHAINMAIL_CHESTPLATE) ||
					e.getPlayer().getItemInHand().getType().equals(Material.CHAINMAIL_HELMET) || e.getPlayer().getItemInHand().getType().equals(Material.CHAINMAIL_LEGGINGS)) {
				e.setCancelled(true);
				e.getPlayer().updateInventory();
			}
		}
	}
}
