package pulsepvp_.backpack.utils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.inventory.Inventory;

import pulsepvp_.backpack.BackPack;

public class Functions {
	public static void saveInventoryToDB(Inventory inventory, int inventory_id) throws IOException {
		String inventory_serialized = Serialization.toBase64(inventory);
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("UPDATE backpack_save SET inventory_serialized = ? WHERE inventory_id = ?");
			statement.setString(1, inventory_serialized);
			statement.setInt(1, inventory_id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {}
	}
	public static Inventory getInventoryFromDB(int inventory_id) throws IOException {
		String inventory_serialized = "";
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("SELECT * FROM backpack_save WHERE inventory_id = ?");
			statement.setInt(1, inventory_id);
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			statement.close();
			if(resultSet.next()) {
				inventory_serialized =  resultSet.getString("inventory_serialized");
			}
		} catch (SQLException e) {}
		return Serialization.fromBase64(inventory_serialized);
	}
	public static int getNewBPId() {
		try {
			PreparedStatement statement = BackPack.getDatabase().prepareStatement("SELECT MAX(inventory_id) as max FROM backpack_save");
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			statement.close();
			if(resultSet.next()) {
				return resultSet.getInt("max")+1;
			}
		} catch (SQLException e) {}
			return 0;
	}
}
