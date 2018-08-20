package me.ryandw11.commandmaker.elements;

import org.bukkit.command.CommandSender;

import me.ryandw11.commandmaker.CommandMaker;
import me.ryandw11.commandmaker.api.CommandMakerCMD;

public class CommandBuilder {
	/*
	 * Return false if everything is ok. Return true to cancel.
	 */
	public boolean build(CommandSender sender, String s, String[] args, CommandMakerCMD cmd){
		for(String sel : cmd.getCommand().getConfigurationSection("").getKeys(false)){
			if(CommandMaker.elements.containsKey(sel)){
				if(CommandMaker.elements.get(sel).onElement(sender, args, cmd)){
					return true;
				}
			}
		}
		return false;
	}
}
