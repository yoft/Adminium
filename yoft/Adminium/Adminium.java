package yoft.Adminium;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Adminium extends JavaPlugin{
	public PermissionHandler Permissions;

	private final AdminiumBlockListener blockListener = new AdminiumBlockListener(this);
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println("[" + pdfFile.getName() + "] has been disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
	    System.out.println("[" + pdfFile.getName() + "] version [" + pdfFile.getVersion() + "] is enabled!");
		
	    PluginManager pm = getServer().getPluginManager();
			pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
			pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Event.Priority.Normal, this);
		
		setupPermissions();
	}
	
	private void setupPermissions() {
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
		
		if (this.Permissions == null) {
			if (test != null) {
				this.Permissions = ((Permissions)test).getHandler();
			} else {
				System.out.println("Permission system not detected, defaulting to OP");
			}
		}
	}
	
	public boolean hasPermission(Player player, String permission){
		if (this.Permissions == null){
			return player.isOp();
		}else{
			return Permissions.has(player, permission);
		}
	}
	
}