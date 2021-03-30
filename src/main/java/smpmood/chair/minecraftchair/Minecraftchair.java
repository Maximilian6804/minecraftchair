package smpmood.chair.minecraftchair;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.Action;
import org.bukkit.World;
import org.bukkit.Location;
import org.spigotmc.event.entity.EntityDismountEvent;
import java.util.*;

public final class Minecraftchair extends JavaPlugin implements Listener {

    public static String PREFIX = "§7[§bSMP§7]";
    public static Minecraftchair Instance;
    private static List<Material> halfBlockTypes = Arrays.asList(new Material[]{Material.ACACIA_STAIRS, Material.BIRCH_STAIRS, Material.BRICK_STAIRS, Material.COBBLESTONE_STAIRS,
            Material.DARK_OAK_STAIRS, Material.JUNGLE_STAIRS, Material.NETHER_BRICK_STAIRS, Material.PURPUR_STAIRS, Material.QUARTZ_STAIRS, Material.SMOOTH_QUARTZ_STAIRS, Material.RED_SANDSTONE_STAIRS,
            Material.SANDSTONE_STAIRS, Material.SMOOTH_SANDSTONE_STAIRS, Material.SPRUCE_STAIRS, Material.OAK_STAIRS, Material.BLACKSTONE_STAIRS, Material.POLISHED_BLACKSTONE_BRICK_STAIRS, Material.POLISHED_BLACKSTONE_STAIRS,
            Material.DARK_PRISMARINE_STAIRS, Material.PRISMARINE_BRICK_STAIRS, Material.PRISMARINE_STAIRS, Material.SMOOTH_RED_SANDSTONE_STAIRS, Material.CRIMSON_STAIRS, Material.POLISHED_ANDESITE_STAIRS, Material.POLISHED_DIORITE_STAIRS,
            Material.ANDESITE_STAIRS, Material.DIORITE_STAIRS, Material.GRANITE_STAIRS, Material.STONE_STAIRS, Material.STONE_BRICK_STAIRS, Material.MOSSY_COBBLESTONE_STAIRS, Material.MOSSY_STONE_BRICK_STAIRS, Material.RED_NETHER_BRICK_STAIRS,
            Material.WARPED_STAIRS, Material.END_STONE_BRICK_STAIRS, Material.POLISHED_GRANITE_STAIRS});

    public Minecraftchair() {Instance = this;}

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        log("Chairs sitzbereit");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event){

        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (halfBlockTypes.contains(event.getClickedBlock().getType())){
                if (!((event.getPlayer().getInventory().getItemInMainHand().getType().isBlock() && (event.getPlayer().getInventory().getItemInOffHand().getType().isBlock()) && (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.LAVA_BUCKET) && (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET))) || (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR)) {
                Location loc = event.getClickedBlock().getLocation();
                World world = event.getPlayer().getWorld();
                Location loc2 = new Location(world, loc.getX() + 0.5, loc.getY() - 1.15, loc.getZ() + 0.5);

                        ArmorStand as = world.spawn(loc2, ArmorStand.class);
                        Location armorstandLocation = as.getLocation();
                        BlockFace blockFace = ((Directional) event.getClickedBlock().getBlockData()).getFacing();

                        if(blockFace == BlockFace.NORTH)
                            armorstandLocation.setYaw(0);
                        else if(blockFace == BlockFace.EAST)
                            armorstandLocation.setYaw(90);
                        else if(blockFace == BlockFace.SOUTH)
                            armorstandLocation.setYaw(180);
                        else if(blockFace == BlockFace.WEST)
                            armorstandLocation.setYaw(270);

                        as.teleportAsync(armorstandLocation);
                        as.setInvulnerable(true);
                        as.setSilent(true);
                        as.addPassenger(p);
                        as.setGravity(false);
                        as.setInvisible(true);
                 }
                }
            }
        }

        @EventHandler
        public void onLeaveEntity(EntityDismountEvent event){

        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDismounted().getType() == EntityType.ARMOR_STAND)) return;

        ArmorStand armorStand = (ArmorStand) event.getDismounted();

        if(!armorStand.isInvisible()) return;
        armorStand.remove();
        }
    }