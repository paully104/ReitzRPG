package com.paulreitz.reitzrpg;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;



public final class API {
    private API() {}
    
    public static boolean fastmobs;
    public static int attackmod;
    public static int defensemod;
    public static int distancetomobdamageincrease;
    public static boolean backpackenabled;
    public static boolean mobexpshow;
    public static boolean blockexpshow;
    public static boolean tradingenabled;
    public static FileConfiguration configuration;
    public static FileConfiguration armors_configuration;
    
    public static void SetArmorConfiguration(FileConfiguration config)
    {
    	armors_configuration = config;
    }
    public static FileConfiguration getArmorsConfiguration()
    {
    	return armors_configuration;
    }
    	
    
    public static void setConfiguration(FileConfiguration config)
    {
    	configuration = config;
    }
    
    public static FileConfiguration getConfiguration()
    {
    	return configuration;
    }
    
    public static void setTradingEnabled()
    {
    	FileConfiguration config = Reitzrpgmain.config;
    	tradingenabled = config.getBoolean("Trading-Enabled");
    	return;
    }
    
    public static void setBlockExpShow()
    {
    	FileConfiguration config = Reitzrpgmain.config;
    	blockexpshow= config.getBoolean("Block-Exp-Show");
    	return;
    	
    }
    
    public static void setMobExpShow()
    {
    	FileConfiguration config = Reitzrpgmain.config;
    	mobexpshow = config.getBoolean("Mob-Exp-Show");
    	return;
    	
    }
    
    public static void setBackpackEnabled()
    {
    	FileConfiguration config = Reitzrpgmain.config;
    	backpackenabled = config.getBoolean("BackPack-Enabled");
    	return;
    	
    }
    
    public static int getPlayerAttack(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getInt("Attack");
    }
    
    public static void setPlayerAttack(Player player,Integer number) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	pd.getData().set("Attack", number);
    }
    public static int getPlayerDefense(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getInt("Defense");
    }
    public static int getMonsterHealthBonus()
    {
    	FileConfiguration config = Reitzrpgmain.config;
    	int number = config.getInt("Monster-Hp-Multiplier");
    	return number;
    }
    
    public static void setPlayerDefense(Player player,Integer number) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	pd.getData().set("Defense", number);
    }
    public static int getPlayerArchery(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getInt("Archery");
    }
    
    public static void setPlayerArchery(Player player,Integer number) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	pd.getData().set("Archery", number);
    }
    public static int getPlayerMagic(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getInt("Magic");
    }
    
    public static void setPlayerMagic(Player player,Integer number) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	pd.getData().set("Magic", number);
    }
    public static double getPlayerHealth(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getDouble("Health");
    }
    public static void setPlayerHealth(Player player,Double number) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	pd.getData().set("Health", number);
    }
    public static int getPlayerBackpackSize(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getInt("BackPack-Size");
    }
    
    public static void setPlayerBackpackSize(Player player,Integer number) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	if(number%9 == 0 && number < 54)
    	{
    	pd.getData().set("BackPack-Size", number);
    	}
    	else
    	{ System.out.println("Backpack size must be less then 54 and divisible by 9!");
    	}
    }
    public static String getPlayerBackpackContent(Player player) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
        return pd.getData().getString("Backpack");
    }
    
    public static void setPlayerBackpackContent(Player player,String backpackcontent) {
    	//PlayerData pd = new PlayerData(player.getName());
    	PlayerData pd = PlayerJoinListener.users.get(player.getName());
    	pd.getData().set("BackPack", backpackcontent);
    }
    
    public static void setFastMobs(Reitzrpgmain plugin)
    {
    	Plugin plugin2 = plugin.getPlugin();
    	FileConfiguration config = plugin2.getConfig();
    	Boolean fastmob = config.getBoolean("Fast-Mobs");
    	fastmobs = fastmob;  	
    }
    public static void setAttackModifier(Reitzrpgmain plugin)
    {
    	Plugin plugin2 = plugin.getPlugin();
    	FileConfiguration config = plugin2.getConfig();
    	int attack_mod = config.getInt("Attack_Modifier");
    	attackmod = attack_mod;  	
    }
    public static void setDefenseModifier(Reitzrpgmain plugin)
    {
    	Plugin plugin2 = plugin.getPlugin();
    	FileConfiguration config = plugin2.getConfig();
    	int defense_mod = config.getInt("Defense_Modifier");
    	defensemod = defense_mod;  	
    } 
    public static void setDistanceToMobDamageIncrease(Reitzrpgmain plugin)
    {
    	Plugin plugin2 = plugin.getPlugin();
    	FileConfiguration config = plugin2.getConfig();
    	int distance = config.getInt("Distance-To-MobDMG-Increase");
    	distancetomobdamageincrease = distance;  	
    }    
    
    

}