package me.ryandw11.commandmaker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import me.ryandw11.commandmaker.api.CommandAPI;
import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;
import me.ryandw11.commandmaker.command.CommandH;
import me.ryandw11.commandmaker.elements.ExecuteCommandsElement;
import me.ryandw11.commandmaker.elements.KillElement;
import me.ryandw11.commandmaker.elements.MessageElement;
import me.ryandw11.commandmaker.elements.MessagesElement;
import me.ryandw11.commandmaker.elements.ParticleElement;
import me.ryandw11.commandmaker.elements.SoundElement;
import me.ryandw11.commandmaker.elements.TeleportElement;

public class CommandMaker extends JavaPlugin {
	
	public static List<CommandMakerCMD> cmds = new ArrayList<>();
	public static HashMap<String, Element> elements = new HashMap<>();
	
	public static CommandMaker plugin;
	CommandAPI cmdAPI = new CommandAPI();
	
	@Override
	public void onEnable(){
		plugin = this;
		registerConfig();
		loadFiles();
		cmdAPI.registerElement("message", new MessageElement());
		cmdAPI.registerElement("messages", new MessagesElement());
		cmdAPI.registerElement("kill", new KillElement());
		cmdAPI.registerElement("executecommands", new ExecuteCommandsElement());
		cmdAPI.registerElement("sound", new SoundElement());
		cmdAPI.registerElement("particle", new ParticleElement());
		cmdAPI.registerElement("teleport", new TeleportElement());
		//Bukkit.getServer().getPluginManager().registerEvents(new CommandHandler(), this);
		
		
	}
	
	@Override
	public void onDisable(){
		
	}
	
	private void loadFiles(){
		for(String s : getConfig().getStringList("Commands")){
			CommandMakerCMD cmd = new CommandMakerCMD(s);
			cmds.add(cmd);
			try {
		        final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		        bukkitCommandMap.setAccessible(true);
		        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
		        commandMap.register(s, new CommandH(s, cmd));

		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		}
	}
	
	private void registerConfig() {
		saveDefaultConfig();
	}
	
//	public void loadFiles(){
//		for(String s : getConfig().getStringList("Commands")){
//			File commandFile = new File(getDataFolder(), "Commands" + File.pathSeparator + s +".yml");
//			 if (!commandFile.exists()) {
//		            commandFile.getParentFile().mkdirs();
//		            saveResource(s + ".yml", false);
//		         }
//			 YamlConfiguration command = new YamlConfiguration();
//			 try {
//					command.load(commandFile);
//					
//				} catch (IOException | InvalidConfigurationException e) {
//
//					e.printStackTrace();
//				}
//			 cmds.put(s, command);
//			 cmdsFiles.put(s, commandFile);
//		}
//	}

}
