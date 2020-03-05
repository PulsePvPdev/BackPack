package pulsepvp_.backpack.listeners;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import pulsepvp_.backpack.utils.Functions;

public class PlayerInteract implements Listener {
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				if(e.getItem().equals(Material.CHEST) && e.getItem().getItemMeta().getDisplayName().contentEquals("BACKPACK")) {
					net.minecraft.server.v1_15_R1.ItemStack back_pack_craft = CraftItemStack.asNMSCopy(e.getItem());
					 NBTTagCompound tag = back_pack_craft.getTag();
					 int inventory_id = tag.getInt("inventory_id");
					 try {
						Inventory inventory = Functions.getInventoryFromDB(inventory_id);
						e.getPlayer().openInventory(inventory);
					} catch (IOException e1) {
						e.getPlayer().sendMessage("ERREUR SQL...");
					}
				}
			}
	}
}