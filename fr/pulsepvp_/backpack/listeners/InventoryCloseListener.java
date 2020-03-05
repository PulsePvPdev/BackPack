package pulsepvp_.backpack.listeners;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import pulsepvp_.backpack.utils.Functions;

public class InventoryCloseListener implements Listener{
	@EventHandler
	 public void leave(InventoryCloseEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.CHEST)) {
			Player player = (Player) event.getPlayer();
			Inventory inventory = event.getInventory();
			 net.minecraft.server.v1_15_R1.ItemStack back_pack_craft = CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand());
			 NBTTagCompound tag = back_pack_craft.getTag();
			 int inventory_id = tag.getInt("inventory_id");
			 try {
				Functions.saveInventoryToDB(inventory, inventory_id);
			} catch (IOException e) {
				player.sendMessage("ERREUR SQL...");
			}
		}
	}
}
