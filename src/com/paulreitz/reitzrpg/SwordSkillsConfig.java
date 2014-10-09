package com.paulreitz.reitzrpg;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class SwordSkillsConfig {
	
	public static void DefaultConfig()
	{
		File skills = Reitzrpgmain.swordskills;
		YamlConfiguration skillsconfig = YamlConfiguration.loadConfiguration(skills);
		
		skillsconfig.addDefault("Spin-Attack", true);
		skillsconfig.addDefault("Spin-Attack-Level", 2);
		skillsconfig.addDefault("Yelir", true);
		skillsconfig.addDefault("Yelir-Level", 0);
		
		
		
		
		skillsconfig.options().copyDefaults(true);
		try {
			skillsconfig.save(skills);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
