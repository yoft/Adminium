package yoft.Adminium;

import org.bukkit.Material;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class AdminiumBlockListener extends BlockListener {
	
	private Adminium plugin;
	
	public AdminiumBlockListener(Adminium plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlockPlaced().getType() != Material.BEDROCK)
			return;
		
		if (!plugin.hasPermission(event.getPlayer(), "adminium.place"))
			event.setBuild(false);
		
	}
	
	@Override
	public void onBlockDamage(BlockDamageEvent event) {
		if (event.getBlock().getType() != Material.BEDROCK)
			return;
		
		if (!plugin.hasPermission(event.getPlayer(), "adminium.break"))
			return;
		
		event.getBlock().setType(Material.AIR);
		event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.BEDROCK, 1));
	}
}