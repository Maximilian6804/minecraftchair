package smpmood.chair.minecraftchair;

import org.bukkit.Material;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleCollisionEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.Action;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockEvent;
import java.sql.Array;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.*;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.TimeUnit;

public final class Minecraftchair extends JavaPlugin implements Listener {

    public static String PREFIX = "§7[§bSMP§7]";
    public static Minecraftchair Instance;
    private static List<Material> halfBlockTypes = Arrays.asList(new Material[]{Material.ACACIA_STAIRS, Material.BIRCH_STAIRS, Material.BRICK_STAIRS, Material.COBBLESTONE_STAIRS,
            Material.DARK_OAK_STAIRS, Material.JUNGLE_STAIRS, Material.NETHER_BRICK_STAIRS, Material.PURPUR_STAIRS, Material.QUARTZ_STAIRS, Material.SMOOTH_QUARTZ_STAIRS, Material.RED_SANDSTONE_STAIRS,
            Material.SANDSTONE_STAIRS, Material.SMOOTH_SANDSTONE_STAIRS, Material.SPRUCE_STAIRS, Material.OAK_STAIRS, Material.BLACKSTONE_STAIRS, Material.POLISHED_BLACKSTONE_BRICK_STAIRS, Material.POLISHED_BLACKSTONE_STAIRS});

    public Minecraftchair() {Instance = this;}


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) throws InterruptedException {

        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (halfBlockTypes.contains(event.getClickedBlock().getType())){
                /*new Thread(() -> {
                    while(event.getClickedBlock() != null) {
                        p.sendMessage(PREFIX + "pixel ist gemein!");
                        /*if (as.getPassenger() == null) {
                            as.remove();
                            break;
                        }



                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }




                    }




                }).start();

                 */

                if (event.getHand().equals(EquipmentSlot.HAND)) {
                    Location loc = event.getClickedBlock().getLocation();
                    World world = event.getPlayer().getWorld();
                    Location loc2 = new Location(world,loc.getX()+0.5, loc.getY()-1.15, loc.getZ()+0.5);
                    ArmorStand as = world.spawn(loc2, ArmorStand.class);
                    as.setInvulnerable(true);
                    as.setSilent(true);
                    as.addPassenger(p);
                    as.setGravity(false);
                    as.setInvisible(true);
                    new Thread(() -> {
                        while(as != null) {
                            if (as.getPassenger() == null) {
                                as.remove();
                                break;
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                   }
                }
            }
        }
    }