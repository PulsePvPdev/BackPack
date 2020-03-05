package pulsepvp_.backpack.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import pulsepvp_.backpack.utils.Functions;

public class MainCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(args.length == 1 && args[0].equalsIgnoreCase("get")) {
	        net.minecraft.server.v1_15_R1.ItemStack stack = CraftItemStack.asNMSCopy(new ItemStack(Material.CHEST));
	        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
	        tag.setInt("inventory_id", Functions.getNewBPId());
	        stack.setTag(tag);
	        ItemStack back_pack=CraftItemStack.asCraftMirror(stack);
	        ItemMeta back_pack_meta = back_pack.getItemMeta();
	        back_pack_meta.setDisplayName("BACKPACK");
	        back_pack.setItemMeta(back_pack_meta);
	        player.getInventory().addItem(back_pack);
		}
		return false;
	}

}
