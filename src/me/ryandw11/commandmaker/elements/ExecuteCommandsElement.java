package me.ryandw11.commandmaker.elements;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;

public class ExecuteCommandsElement implements Element{
	private CommandAPI cmdAPI;
	public ExecuteCommandsElement(){
		cmdAPI = new CommandAPI();
	}

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
		if(!(sender instanceof Player)){
			Bukkit.getLogger().warning("The execute command element does not support console input!");
			return true;
		}
		Player p = (Player) sender;
		List<String> commands = cmd.getCommand().getStringList("executecommands");
		for(String s : commands){
			Bukkit.dispatchCommand(sender, cmdAPI.phaseArgs(s , args).replace("{player}", p.getDisplayName()));
		}
		return false;
	}

}
