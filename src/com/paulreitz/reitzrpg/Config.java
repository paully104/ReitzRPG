package com.paulreitz.reitzrpg;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class Config implements Listener
{
	public static FileConfiguration config;
	public static Reitzrpgmain plugin;
	
    public Config(Reitzrpgmain instance) {
        plugin = instance;//tells us what the plugin is to use, in this case using the FileConfig! 
        
    } 
	
	public static void MakeConfig()
	{
	
		  
	config = Reitzrpgmain.getPlugin().getConfig();
	config.options().header("Player attack = attack/attack modifier\n)" //$NON-NLS-1$
			+ "Player defence = defence/defence modifier\n" //$NON-NLS-1$
			+ "for empty spots use a 0 on CustomWeapons\n" //$NON-NLS-1$
			+ "please report bugs on the bukkit dev page!"); //$NON-NLS-1$
	config.options().copyHeader(true);
	config.addDefault("BackPack-Enabled", true);
	config.addDefault("Trading-Enabled", true);
	config.addDefault("Monster-Levels", true); //$NON-NLS-1$
	config.addDefault("Monster-Hp-Multiplier", 2);
	config.addDefault("world", null); //$NON-NLS-1$
	config.addDefault("world.Level", 1.00); //$NON-NLS-1$
	config.addDefault("Distance-To-MobDMG-Increase", 300); //$NON-NLS-1$
	config.addDefault("Attack_Modifier", 1); //$NON-NLS-1$
	config.addDefault("Defense_Modifier", 1); //$NON-NLS-1$
	config.addDefault("Fast-Mobs", true); //$NON-NLS-1$
	config.addDefault("Block-Exp-Show", true); //$NON-NLS-1$
	config.addDefault("Mob-Exp-Show", true); //$NON-NLS-1$
	config.addDefault("Tornadoes", false);
	//custom weapon part
	config.addDefault("CustomWeapons", null); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword", null); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Topleft", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Topmid", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Topright", 280); //$NON-NLS-1$
	
	config.addDefault("CustomWeapons.Awesome_Sword.Midleft", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Midmid", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Midright", 280); //$NON-NLS-1$

	config.addDefault("CustomWeapons.Awesome_Sword.Botleft", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Botmid", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Botright", 280); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Mindamage", 3); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Maxdamage", 6); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Material", 268);
	config.addDefault("CustomWeapons.Awesome_Sword.Enchantment", "ARROW_DAMAGE");
	config.addDefault("CustomWeapons.Awesome_Sword.EnchantmentLevel", 1);
	config.addDefault("CustomWeapons.Awesome_Sword.Level", 10); //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Lore", null);	 //$NON-NLS-1$
	config.addDefault("CustomWeapons.Awesome_Sword.Lore.Top", "\"TOP\""); //$NON-NLS-1$ //$NON-NLS-2$
	config.addDefault("CustomWeapons.Awesome_Sword.Lore.Mid", "\"MID\""); //$NON-NLS-1$ //$NON-NLS-2$
	config.addDefault("CustomWeapons.Awesome_Sword.Lore.Bot", "\"BOT\""); //$NON-NLS-1$ //$NON-NLS-2$
	config.addDefault("CustomWeapons.Awesome_Sword.Name", "Awesome Sword"); //$NON-NLS-1$ //$NON-NLS-2$
	config.addDefault("TreasureChests.Chest_Example.Time",10);		 //$NON-NLS-1$
	config.addDefault("TreasureChests.Chest_Example.RespawnTime",10);	 //$NON-NLS-1$
	config.addDefault("TreasureChests.Chest_Example.Contents","9;");				 //$NON-NLS-1$ //$NON-NLS-2$
	config.options().copyDefaults(true);
	Reitzrpgmain.getPlugin().saveConfig();
}
	}
