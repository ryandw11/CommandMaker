package me.ryandw11.commandmaker.elements;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;

public class SoundElement implements Element{

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
		if(!(sender instanceof Player)){
			Bukkit.getLogger().warning("The sound element only supports players!");
			return true;
		}
		Player p = (Player) sender;
		p.playSound(p.getLocation(), getSound(cmd.getCommand().getString("sound")), 1, 1); //TODO make changeable.
		return false;
	}
	
	public Sound getSound(String s){
		Sound sd;
		try{
			sd = Sound.valueOf(s);
		}
		catch(IllegalArgumentException | NullPointerException e){
			return Sound.BLOCK_ANVIL_BREAK;
		}
		return sd;
	}

}
