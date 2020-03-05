package pulsepvp_.backpack.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import pulsepvp_.backpack.BackPack;
import pulsepvp_.backpack.listeners.InventoryCloseListener;
import pulsepvp_.backpack.listeners.PlayerInteract;

public class EventsManagers implements Listener {
	public void registers() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new InventoryCloseListener(), BackPack.getInstance());
		pm.registerEvents(new PlayerInteract(), BackPack.getInstance());
	}
}

