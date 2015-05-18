package net.savagereborn.RedJaVa;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * This is the SRChatColor Bukkit plugin. This bukkit plugin changes a players chat plugin accordingly to
 * correct permission of srchat.setcolor. Command: /setcolor
 * @author Jacob Valdiviez
 *
 */

public class SRChatColor extends JavaPlugin{
	private JavaPlugin plugin;
	/**
	 * This field contains the prefix for the plugin messages
	 */
	private String prefix = ChatColor.GOLD + "[SrChatColor] ";
	/**
	 * This field contains all the words a user can use to set a color to his chat
	 */
	private String colorMenu = ChatColor.translateAlternateColorCodes('&', "{Color} -> Default, &0Black, &1DarkBlue, &2DarkGreen, &3DarkAqua, &4DarkRed, &5Purple, &6Gold,"
			+ "&7Gray, &8DarkGray, &9Indigo, &aGreen, &bAqua, &cRed, &dPink, &eYellow, &fWhite");
	/**
	 * This field contains the chat color changer
	 * @see ChatColorChanger
	 */
	private ChatColorChanger chatColorChanger;
	/**
	 * This method is called when the plugin starts up
	 */
	@Override
	public void onEnable(){
		this.plugin = this;
		this.chatColorChanger = new ChatColorChanger(this.plugin);
		this.plugin.getServer().getPluginManager().registerEvents(this.chatColorChanger, this);
		this.getCommand("setcolor").setExecutor(this);
		File configFile = new File(this.getDataFolder() + "/config.yml");
		if(!configFile.exists()){
			this.saveDefaultConfig();
		}
		getLogger().info("SRChatColor has been enabled!");
	}
	/**
	 * This method is called when the plugin is disabled
	 */
	@Override
	public void onDisable(){
		getConfig().options().copyDefaults(true);
		this.saveConfig();
		getLogger().info("SRChatColor has been disabled!");
	}
	/**
	 * This method handles the /setcolor command
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player;
		try{
			player = (Player)sender;
		}catch(Exception e){
			sender.sendMessage(prefix + ChatColor.RED + "You must be online in order to do this!");
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("setcolor") && player.hasPermission("srchat.setcolor")){
			if(args.length == 1){
				String color = convertStringWord(args[0]);
				//if the color is not recognized as a valid word or alphanumeric text
				if(color.equals(" ")){
					player.sendMessage(this.prefix + ChatColor.RED + " Didn't understand color");
					player.sendMessage(ChatColor.GREEN + "Use: " + this.colorMenu);
					return true;
				}
				//if the user wants his color to be reseted
				if(color.equals("default")){
					getConfig().set(player.getName(),null);
					player.sendMessage(ChatColor.GREEN + "Changed your chat color to " + ChatColor.RESET + " default");
				}
				else{
					getConfig().set(player.getName(), color);
					player.sendMessage(ChatColor.GREEN + "Changed your chat color to " + ChatColor.translateAlternateColorCodes('&', color) + args[0]);
				}
			}
			else{
				//if the user didn't enter the command in a correct way
				displayMenu(player);
			}
			return true;
		}
		return false; 
	}
	/**
	 * This method displays the help menu for the bukkit plugin
	 * @param player
	 */
	private void displayMenu(Player player){
		player.sendMessage(this.prefix + ChatColor.RED + " You did it wrong!");
		player.sendMessage(ChatColor.GREEN + "Correct Usage: /setcolor {color}");
		player.sendMessage(ChatColor.GREEN + this.colorMenu);
	}
	/**
	 * This method converts a given word that is a color into alphanumerical text used
	 * to convert chat into color
	 * @param color
	 * @return String
	 */
	private String convertStringWord(String color){
		if(color.equalsIgnoreCase("black") || color.equals("&0"))
			return "&0";
		if(color.equalsIgnoreCase("darkblue") || color.equals("&1"))
			return "&1";
		if(color.equalsIgnoreCase("darkgreen") || color.equals("&2"))
			return "&2";
		if(color.equalsIgnoreCase("darkaqua") || color.equals("&3"))
			return "&3";
		if(color.equalsIgnoreCase("darkred") || color.equals("&4"))
			return "&4";
		if(color.equalsIgnoreCase("purple") || color.equals("&5"))
			return "&5";
		if(color.equalsIgnoreCase("gold") || color.equals("&6"))
			return "&6";
		if(color.equalsIgnoreCase("gray") || color.equals("&7"))
			return "&7";
		if(color.equalsIgnoreCase("darkgray") || color.equals("&8"))
			return "&8";
		if(color.equalsIgnoreCase("indigo") || color.equals("&9"))
			return "&9";
		if(color.equalsIgnoreCase("green") || color.equals("&a"))
			return "&a";
		if(color.equalsIgnoreCase("aqua") || color.equals("&b"))
			return "&b";
		if(color.equalsIgnoreCase("red") || color.equals("&c"))
			return "&c";
		if(color.equalsIgnoreCase("pink") || color.equals("&d"))
			return "&d";
		if(color.equalsIgnoreCase("yellow") || color.equals("&e"))
			return "&e";
		if(color.equalsIgnoreCase("white") || color.equals("&f"))
			return "&f";
		if(color.equalsIgnoreCase("default"))
			return "default";
		return " ";
	}
}
