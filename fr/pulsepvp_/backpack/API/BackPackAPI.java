package pulsepvp_.backpack.API;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import pulsepvp_.backpack.utils.Functions;

public class BackPackAPI {
	public static ItemStack getBackPack() {
        net.minecraft.server.v1_15_R1.ItemStack stack = CraftItemStack.asNMSCopy(new ItemStack(Material.CHEST));
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        int inventory_id = Functions.getNewBPId();
        tag.setInt("inventory_id", inventory_id);
        Functions.createBPinDB(inventory_id);
        stack.setTag(tag);
        ItemStack back_pack=CraftItemStack.asCraftMirror(stack);
        ItemMeta back_pack_meta = back_pack.getItemMeta();
        back_pack_meta.setDisplayName("BACKPACK");
        back_pack.setItemMeta(back_pack_meta);
        return back_pack;
	}
	public static void openBackPackToPlayer(Player player, int inventory_id) {
		 Inventory inventory = Functions.getInventoryFromDB(inventory_id);
		 player.openInventory(inventory);
	}
}
