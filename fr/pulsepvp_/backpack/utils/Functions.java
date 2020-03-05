package pulsepvp_.backpack.utils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import pulsepvp_.backpack.BackPack;

public class Functions {
	public static void saveInventoryToDB(Inventory inventory, int inventory_id) throws IOException {
		String inventory_serialized = Serialization.toBase64(inventory);
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("UPDATE backpack_save SET inventory_serialized = ? WHERE inventory_id = ?");
			statement.setString(1, inventory_serialized);
			statement.setInt(2, inventory_id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			BackPack.getConsole().sendMessage("erreursqlsave=" + e);
		}
	}
	public static Inventory getInventoryFromDB(int inventory_id) {
		String inventory_serialized = "";
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("SELECT * FROM backpack_save WHERE inventory_id = ?");
			statement.setInt(1, inventory_id);
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				inventory_serialized =  resultSet.getString("inventory_serialized");
			}
			statement.close();
		} catch (SQLException e) {
			BackPack.getConsole().sendMessage("ERREURSQL=" + e);
		}
		try {
			return Serialization.fromBase64(inventory_serialized);
		} catch (IOException e) {
			BackPack.getConsole().sendMessage("ERREURIOEXCEPTION=" + e);
		}
		return null;
	}
	public static void createBPinDB(int inventory_id) {
		
		Inventory inv = Bukkit.createInventory(null, 54, "BackPack =)");
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("INSERT INTO backpack_save VALUES (?,?)");
			statement.setInt(1, inventory_id);
			statement.setString(2, Serialization.toBase64(inv));
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			BackPack.getConsole().sendMessage("ERREUR SQL=" + e);
		}
	}
	public static int getNewBPId() {
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("SELECT MAX(inventory_id) as max FROM backpack_save");
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt("max")+1;
			}
			statement.close();
		} catch (SQLException e) {
			BackPack.getConsole().sendMessage("ERREUR SQL=" + e);
		}
			return 0;
	}
}
