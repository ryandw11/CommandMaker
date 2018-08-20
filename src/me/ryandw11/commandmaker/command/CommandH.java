package me.ryandw11.commandmaker.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.elements.CommandBuilder;
import net.md_5.bungee.api.ChatColor;

public class CommandH extends BukkitCommand {
	
	CommandMakerCMD cmd;
	CommandAPI cmdB;
	List<String> cmdArgs;

	public CommandH(String name, CommandMakerCMD cmd) {
		super(name);
		this.description = cmd.getDescription();
	    this.usageMessage = cmd.getUsageMessage();
	    this.setAliases(cmd.getAliases());
	    this.cmd = cmd;
	    cmdB = new CommandAPI();
	}

	@Override
	public boolean execute(CommandSender sender, String s, String[] args) {
		if(cmd.isPlayerOnly()){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only!");
				return true;
			}
		}
		
		if(cmd.getCommand().contains("permission")){ // handle permissions
			if(!sender.hasPermission(cmd.getCommand().getString("permission"))){
				if(cmd.getCommand().contains("permission-deny")){
					sender.sendMessage(ChatColor.translateAlternateColorCodes( '&', cmd.getCommand().getString("permission-deny")));
					return true;
				}
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
				return true;
			}
		}
		
		if(cmd.getCommand().contains("Argument")){
			if(args.length < cmd.getCommand().getInt("Argument.number")){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', cmd.getCommand().getString("Argument.not-enough-args")));
				return true;
			}
		}
		
		CommandBuilder cmdb = new CommandBuilder();
		if(cmdb.build(sender, s, args, cmd)){
			Bukkit.getLogger().warning("Warning: The command " + cmd.getName() + " has stopped working due to an error with an element!");
			return true;
		}
		
		return false;
	}
	

}
