package com.paulreitz.reitzrpg;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class BowSkillsConfig {
	
	public static void DefaultConfig()
	{
		File bowskills = Reitzrpgmain.bowskills;
		YamlConfiguration skillsconfig = YamlConfiguration.loadConfiguration(bowskills);
		
		skillsconfig.addDefault("Rapid-Shot", true);
		skillsconfig.addDefault("Rapid-Shot-Level", 5);
		skillsconfig.addDefault("TNT-Shot", true);
		skillsconfig.addDefault("TNT-Shot-Level", 9);
		skillsconfig.options().copyDefaults(true);
		
		try {
			skillsconfig.save(bowskills);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
