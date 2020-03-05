package pulsepvp_.backpack.listeners;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import pulsepvp_.backpack.utils.Functions;

public class InventoryCloseListener implements Listener{
	@EventHandler
	 public void leave(InventoryCloseEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand().equals(Material.CHEST)) {
			 net.minecraft.server.v1_15_R1.ItemStack back_pack_craft = CraftItemStack.asNMSCopy(event.getPlayer().getInventory().getItemInMainHand());
			 NBTTagCompound tag = back_pack_craft.getTag();
			 int inventory_id = tag.getInt("inventory_id");
			 try {
				Functions.saveInventoryToDB(event.getInventory(), inventory_id);
			} catch (IOException e) {
				event.getPlayer().sendMessage("ERREUR SQL...");
			}
		}
	}
}
