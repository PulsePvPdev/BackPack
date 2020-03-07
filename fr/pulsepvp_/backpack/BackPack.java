package pulsepvp_.backpack;

import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import pulsepvp_.backpack.commands.MainCommand;
import pulsepvp_.backpack.managers.EventsManagers;
import pulsepvp_.backpack.utils.DatabaseManager;

public class BackPack extends JavaPlugin{
	public static ConsoleCommandSender console = Bukkit.getConsoleSender();
	private static BackPack instance;
	private static DatabaseManager databaseManager;
	@Override
	public void onEnable() {
		instance = this;
		console.sendMessage("§BackPack actif !");
		new EventsManagers().registers();
		getCommand("bp").setExecutor(new MainCommand());
	    databaseManager = new DatabaseManager(this);
	    databaseManager.connect();
	}

	@Override
	public void onDisable() {
		console.sendMessage("§BackPack desactivé");
	}

	public static ConsoleCommandSender getConsole() {
		return console;
	}

	public static BackPack getInstance() {
		return instance;
	}
	public static Connection getDatabase() {
	      return databaseManager.getDatabase();
	   }
	public static Class<?> getNMSClass(String name) throws ClassNotFoundException {
	       return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
	   }

}
