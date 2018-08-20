package me.ryandw11.commandmaker.elements;

import org.bukkit.command.CommandSender;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;
import net.md_5.bungee.api.ChatColor;

public class MessageElement implements Element {
	
	private CommandAPI cmdB;
	public MessageElement(){
		cmdB = new CommandAPI();
	}

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
		if(cmd.getCommand().contains("message")){
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', cmdB.phaseArgs(cmd.getCommand().getString("message"), args)));
		}
		return false;
	}
	
}
