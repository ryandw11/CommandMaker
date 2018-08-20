package me.ryandw11.commandmaker.elements;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;
import net.md_5.bungee.api.ChatColor;

public class TeleportElement implements Element {
	
	CommandAPI cmdAPI;
	public TeleportElement(){
		cmdAPI = new CommandAPI();
	}

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
		if(cmd.getCommand().contains("teleport.pitch") && cmd.getCommand().contains("teleport.yaw")){
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getOfflinePlayer(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.target").replace("{player}", sender.getName()), args)).getPlayer();
			if(target == null){
				sender.sendMessage(cmd.getSettings().getPlayerNotOnlineError());
				return true;
			}
			if(!target.isOnline()){
				sender.sendMessage(cmd.getSettings().getPlayerNotOnlineError());
				return true;
			}
			World w = Bukkit.getWorld(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.world"), args));
			int x = 0;
			int y = 0;
			int z = 0;
			float yaw = 0;
			float pitch = 0;
			try{
				x = Integer.parseInt(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.x"), args));
				y = Integer.parseInt(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.y"), args));
				z = Integer.parseInt(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.z"), args));
				yaw = Float.parseFloat(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.yaw"), args));
				pitch = Float.parseFloat(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.pitch"), args));
			}catch(NumberFormatException e){
				sender.sendMessage(ChatColor.RED + "Invaild integers.");
				return true;
			}
			target.teleport(new Location(w, x, y, z, yaw, pitch));
		}else{
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getOfflinePlayer(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.target").replace("{player}", sender.getName()), args)).getPlayer();
			if(target == null){
				sender.sendMessage(cmd.getSettings().getPlayerNotOnlineError());
				return true;
			}
			if(!target.isOnline()){
				sender.sendMessage(cmd.getSettings().getPlayerNotOnlineError());
				return true;
			}
			World w = Bukkit.getWorld(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.world"), args));
			int x = 0;
			int y = 0;
			int z = 0;
			try{
				x = Integer.parseInt(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.x"), args));
				y = Integer.parseInt(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.y"), args));
				z = Integer.parseInt(cmdAPI.phaseArgs(cmd.getCommand().getString("teleport.z"), args));
			}catch(NumberFormatException e){
				sender.sendMessage(ChatColor.RED + "Invaild integers.");
				return true;
			}
			target.teleport(new Location(w, x, y, z));
		}
		return false;
	}

}
