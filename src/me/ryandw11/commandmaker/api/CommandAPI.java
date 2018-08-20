package me.ryandw11.commandmaker.api;

import me.ryandw11.commandmaker.CommandMaker;

public class CommandAPI {
	
	/**
	 * Easy method to replace arguments with their text counter part.
	 * @param s
	 * @param args
	 * @return
	 */
	public String phaseArgs(String s, String[] args){
		String a = s;
		for(int i = 0; i < args.length; i += 1){
			a = a.replace("{arg" + (i + 1) + "}", args[i]);
		}
		return a;
	}
	/**
	 * For elements that require the number of said argument.
	 * @param s
	 * @return
	 */
	public int getArgsNumber(String s){
		String st = s;
		st = st.replace("{arg", "");
		st = st.replace("}", "");
		int i = -1;
		try{
			i = Integer.parseInt(st);
			i--;
		}catch(NumberFormatException e){
			
		}
		return i;
	}
	
	
	/**
	 * Register the element for use.
	 * @param e - the class.
	 */
	public void registerElement(String name, Element e){
		CommandMaker.elements.put(name, e);
	}
}
