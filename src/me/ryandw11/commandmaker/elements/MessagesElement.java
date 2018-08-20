package me.ryandw11.commandmaker.elements;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;
import net.md_5.bungee.api.ChatColor;

public class MessagesElement implements Element {
	private CommandAPI cmdAPI;
	public MessagesElement(){
		cmdAPI = new CommandAPI();
	}

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
		if(cmd.getCommand().contains("messages")){
			messages(cmd.getCommand().getStringList("messages"), sender, args);
		}
		return false;
	}
	
	public void messages(List<String> message, CommandSender sender, String[] args){
		for(String s : message){
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', cmdAPI.phaseArgs(s, args)));
		}
	}

}
