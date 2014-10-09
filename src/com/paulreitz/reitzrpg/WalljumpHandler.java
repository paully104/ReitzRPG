package com.paulreitz.reitzrpg;

import org.bukkit.Effect;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.util.Vector;

public class WalljumpHandler implements Listener {
       
        public enum Direction {
                SOUTH,
                EAST,
                NORTH,
                WEST
        }
       
        public static Direction getExactDirection(Player player) {
               
                float yaw = player.getLocation().getYaw();
            yaw = yaw / 90;
            yaw = (float)Math.round(yaw);
           
            if (yaw == -4 || yaw == 0 || yaw == 4) {
                return Direction.SOUTH;
            } if (yaw == -1 || yaw == 3) {
                return Direction.EAST;
            } if (yaw == -2 || yaw == 2) {
                return Direction.NORTH;
            }if (yaw == -3 || yaw == 1) {
                return Direction.WEST;
            }
           
            return null;
        }
       
        @SuppressWarnings("deprecation")
        @EventHandler
        public void onWalljump(PlayerToggleSneakEvent e) {
                Player player = e.getPlayer();
                if (!player.isOnGround() && !player.isFlying()) {
                       
                        boolean walljumped = false;
                        if (getExactDirection(player) == Direction.EAST) {
                                if (player.getLocation().add(0.5, 0, 0).getBlock().getType().isSolid()) {
                                        player.playEffect(player.getLocation().add(0.5, 0, 0), Effect.STEP_SOUND, player.getLocation().add(0.5, 0, 0).getBlock().getType());
                                        player.setVelocity(new Vector(player.getVelocity().getX() - 0.4, 0.90, player.getVelocity().getZ()));
                                        walljumped = true;
                                }
                        } else if (getExactDirection(player) == Direction.WEST) {
                                if (player.getLocation().subtract(0.5, 0, 0).getBlock().getType().isSolid()) {
                                        player.playEffect(player.getLocation().subtract(0.5, 0, 0), Effect.STEP_SOUND, player.getLocation().subtract(0.5, 0, 0).getBlock().getType());
                                        player.setVelocity(new Vector(player.getVelocity().getX() + 0.4,0.90, player.getVelocity().getZ()));
                                        walljumped = true;
                                }
                        } else if (getExactDirection(player) == Direction.SOUTH) {
                                if (player.getLocation().add(0, 0, 0.5).getBlock().getType().isSolid()) {
                                        player.playEffect(player.getLocation().add(0, 0, 0.5), Effect.STEP_SOUND, player.getLocation().add(0, 0, 0.5).getBlock().getType());
                                        player.setVelocity(new Vector(player.getVelocity().getX(), 0.90, player.getVelocity().getZ() - 0.4));
                                        walljumped = true;
                                }
                        } else if (getExactDirection(player) == Direction.NORTH) {
                                if (player.getLocation().subtract(0, 0, 0.5).getBlock().getType().isSolid()) {
                                        player.playEffect(player.getLocation().subtract(0, 0, 0.5), Effect.STEP_SOUND, player.getLocation().subtract(0, 0, 0.5).getBlock().getType());
                                        player.setVelocity(new Vector(player.getVelocity().getX(), 0.90, player.getVelocity().getZ() + 0.4));
                                        walljumped = true;
                                }
                        }
                        if (walljumped) {
                               // player.setExp(0);
                               // Assassin.getPlugin().recharge(player, 1);
                        }
                }
        }
        @EventHandler
        public void onDismount(VehicleExitEvent e) {
                if (e.getVehicle() instanceof Horse) {
                        if (e.getExited() instanceof Player) {
                                Player player = (Player) e.getExited();
                                player.setVelocity(player.getLocation().getDirection().multiply(0.8).add(new Vector(0, 0.2, 0)));
                                
                                
                        }
                }
        }
}