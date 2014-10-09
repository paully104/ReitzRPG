package com.paulreitz.reitzrpg;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class MagicSkillsConfig {
	
	public static void AddDefault()
	{
		File magicskills = Reitzrpgmain.magicskills;
		YamlConfiguration skillsconfig = YamlConfiguration.loadConfiguration(magicskills);
		
		skillsconfig.addDefault("Teleport", true);
		skillsconfig.addDefault("Teleport-Level", 3);
		skillsconfig.addDefault("Tornado", true);
		skillsconfig.addDefault("Tornado-Level", 5);
		
		skillsconfig.addDefault("Fire", true);
		skillsconfig.addDefault("Fire-Level", 1);
		skillsconfig.addDefault("Lightning-Escape", true);
		skillsconfig.addDefault("Lightning-Escape-Level", 7);
		skillsconfig.options().copyDefaults(true);
		
		try {
			skillsconfig.save(magicskills);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
