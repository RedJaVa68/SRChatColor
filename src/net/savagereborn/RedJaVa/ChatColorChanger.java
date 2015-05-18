package net.savagereborn.RedJaVa;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * This class changes the color of the person's chat color
 * @author Jacob Valdiviez
 *
 */
public class ChatColorChanger implements Listener{
	/**
	 * This field contains the main plugin
	 */
	JavaPlugin plugin;
	/**
	 * This instantiates the class with the main plugin passed
	 * @param plugin
	 */
	public ChatColorChanger(JavaPlugin plugin){
		this.plugin = plugin;
	}
	/**
	 * This handles the player chat when someone containing the permission and the color
	 * set to change on player chat
	 * @param event
	 */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
    	String color = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString(event.getPlayer().getName()));
    	if(this.plugin.getConfig().getString(event.getPlayer().getName()) != null){
    		if(event.getPlayer().hasPermission("srchat.setcolor"))
    			event.setMessage(color + event.getMessage());
    		else
    			this.plugin.getConfig().set(event.getPlayer().getName(),null);
    		
    	}
    }
}
