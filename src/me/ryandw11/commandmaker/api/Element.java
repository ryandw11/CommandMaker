package me.ryandw11.commandmaker.api;

import org.bukkit.command.CommandSender;

public interface Element {
	/**
	 * Process an element
	 * @param sender
	 * @param args
	 * @param cmd
	 * @return Return true to cancel. Return false to finish process.
	 */
	public boolean onElement(CommandSender sender, String[] args, CommandMakerCMD cmd);
}
