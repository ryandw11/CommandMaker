package me.ryandw11.commandmaker.elements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;

public class KillElement implements Element {
	
	private CommandAPI cmdAPI;
	public KillElement(){
		cmdAPI = new CommandAPI();
	}

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
			int argNum = cmdAPI.getArgsNumber(cmd.getCommand().getString("kill"));
			if(argNum == -1){
				sender.sendMessage(ChatColor.RED + "There is an error with the command: " + cmd.getName() + "! Problem: kill, arg not found.");
				return true;
			}
			@SuppressWarnings("deprecation")
			Player p = Bukkit.getOfflinePlayer(args[argNum]).getPlayer();
			if(p == null){
				sender.sendMessage(cmd.getSettings().getPlayerNotOnlineError());
				return true;
			}
			if(!p.isOnline()){
				sender.sendMessage(cmd.getSettings().getPlayerNotOnlineError());
				return true;
			}
			p.setHealth(0);
		return false;
	}

}
