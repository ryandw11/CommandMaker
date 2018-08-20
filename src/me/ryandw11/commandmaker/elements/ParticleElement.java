package me.ryandw11.commandmaker.elements;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ryandw11.commandmaker.api.CommandMakerCMD;
import me.ryandw11.commandmaker.api.Element;

public class ParticleElement implements Element {

	@Override
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd) {
		if(!(sender instanceof Player)){
			Bukkit.getLogger().warning("The particle element is for players only!");
			return true;
		}
		Player p = (Player) sender;
		p.getWorld().spawnParticle(getParticle(cmd.getCommand().getString("particle")), p.getLocation(), 100); //TODO make set able.
		return false;
	}
	
	public Particle getParticle(String s){
		Particle sd;
		try{
			sd = Particle.valueOf(s);
		}
		catch(IllegalArgumentException | NullPointerException e){
			return Particle.FLAME;
		}
		return sd;
	}

}
