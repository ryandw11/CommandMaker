package me.ryandw11.commandmaker.api;

import net.md_5.bungee.api.ChatColor;

public class CommandSettings {
	
	private String notAPlayerError;
	private String playerNotOnlineError;
	
	public CommandSettings(String nape, String pnoe){
		notAPlayerError = nape;
		playerNotOnlineError = pnoe;
	}
	
	public String getNotAPlayerError(){
		return ChatColor.translateAlternateColorCodes('&', notAPlayerError);
	}
	
	public String getPlayerNotOnlineError(){
		return ChatColor.translateAlternateColorCodes('&', playerNotOnlineError);
	}
	
}
