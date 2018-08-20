package me.ryandw11.commandmaker.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ryandw11.commandmaker.CommandMaker;

public class CommandMakerCMD {
	
	private CommandMaker plugin;
	private File cmdFile;
	private YamlConfiguration cmd;
	private String name;
	private CommandSettings cmdS;
	
	public CommandMakerCMD(String s){
		this.plugin = CommandMaker.plugin;
		File cFile = new File(plugin.getDataFolder(), "Commands" + File.separator + s + ".yml");
		File commandFile = null;
		if (!cFile.exists()) {
			cFile.getParentFile().mkdirs();
		    plugin.saveResource("example.yml", false);
		    commandFile = new File(plugin.getDataFolder(), "example.yml");
		    commandFile.renameTo(cFile);
		}
		YamlConfiguration command = new YamlConfiguration();
		if(commandFile == null)
			commandFile = cFile;
		try {
			command.load(cFile);
					
		} catch (IOException | InvalidConfigurationException e) {

			e.printStackTrace();
		}
		cmdFile = commandFile;
		cmd = command;
		name = s;
		cmdS = new CommandSettings(cmd.getString("Settings.NotAPlayerError"), cmd.getString("Settings.PlayerNotOnlineError"));
	}
	
	public File getFile(){
		return cmdFile;
	}
	
	public YamlConfiguration getCommand(){
		return cmd;
	}
	
	public String getName(){
		return name;
	}
	
	public List<String> getAliases(){
		return getCommand().getStringList("Aliases");
	}
	
	public String getDescription(){
		return getCommand().getString("Description");
	}
	
	public String getUsageMessage(){
		return getCommand().getString("UsageMessage");
	}
	
	public String getPermission(){
		return getCommand().getString("Permission");
	}
	
	public boolean isPlayerOnly(){
		return getCommand().getBoolean("PlayerOnly");
	}
	
	public CommandSettings getSettings(){
		return cmdS;
	}
	
}
