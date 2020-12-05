package me.potassio.ar.Commands;

import me.potassio.ar.F;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class lore implements CommandExecutor {
    F f = new F();
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage( "§cOnly Player");
            return false;
        }
        Player player = (Player)sender;
        if (player.hasPermission("ar.lore") || player.hasPermission("ar.*")) {
            ItemStack item = player.getItemInHand();
            ItemMeta itemmeta = item.getItemMeta();
            if (item.getType().equals(Material.AIR)) {
                player.sendMessage( "§cError: You don't have any item in your hand");
                return false;
            }
            int count = 0;
            count = args.length;
            if (args.length < 1) {
            } else {
                List<String> lore;
                if (itemmeta.getLore() == null) {
                    lore = new ArrayList<>();
                } else {
                    lore = itemmeta.getLore();
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (player.hasPermission("ar.lore") || player.hasPermission("ar.*")) {
                        if (count <= 1) {
                            player.sendMessage( "§cError: Please add a line number");
                            player.sendMessage( "§a/lore set <§cLine§a> <§cLore§a>");
                        } else if (count == 2) {
                            player.sendMessage( "Error: Please type the lore you want to add");
                            player.sendMessage( "§a/lore set <§cLine§a> <§cLore§a>");
                        } else if (count >= 3) {
                            if (itemmeta.getLore() == null) {
                                player.sendMessage( "§cError: Item does not have lore");
                            } else {
                                int line = Integer.parseInt(args[1]);
                                if (line <= lore.size()) {
                                    lore.set(line - 1, pLore(f.translateHexCodes(args[2])));
                                    itemmeta.setLore(lore);
                                    player.sendMessage("§aSucessfully set lore at line §f#" + line);
                                } else {
                                    player.sendMessage("§cError: Line number doesn't exist in item lore");
                                    player.sendMessage( "§a/lore set <§cLine§a> <§cLore§a>");
                                    return false;
                                }
                            }
                        } else {
                            sender.sendMessage("§ctoo many arguments! §ain the lore uses _ instead of spaces");
                            return false;
                        }
                        player.getItemInHand().setItemMeta(itemmeta);
                        return true;
                    }
                    player.sendMessage("§cYou don't have permission to use this command");
                    return false;
                }
                if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("ar.lore") || player.hasPermission("ar.*")) {
                        if (count < 2) {
                            player.sendMessage( "§cError: Please enter the Lore");
                            player.sendMessage( "§a/lore add <§cLore§a>");
                        } else if (count == 2) {
                            lore.add(pLore(f.translateHexCodes(args[1])));
                            itemmeta.setLore(lore);
                            player.sendMessage("§aSucessfully added lore");
                        } else if (count == 3) {
                            int line = Integer.parseInt(args[1]);
                            if (line > lore.size()) {
                                player.sendMessage("§cError: Line number doesn't exist in item lore");
                                player.sendMessage( "§a/lore add <§cLore§a>");
                                return false;
                            }
                            lore.add(line - 1, pLore(f.translateHexCodes(args[1])));
                            itemmeta.setLore(lore);
                            player.sendMessage( "§aSucessfully added lore after line #" + line);
                        } else {
                            sender.sendMessage("§ctoo many arguments! §ain the lore uses _ instead of spaces");
                            player.sendMessage( "§a/lore add <§cLore§a>");
                            return false;
                        }
                        player.getItemInHand().setItemMeta(itemmeta);
                        return true;
                    }
                    player.sendMessage("§cYou don't have permission  to use this command");
                    return false;
                }
                if (args[0].equalsIgnoreCase("remove") || args[0].equals("rem")) {
                    if (player.hasPermission("ar.lore") || player.hasPermission("ar.*")) {
                        if (count == 1) {
                            if (itemmeta.getLore() == null) {
                                player.sendMessage( "§cError: Item has no lore");
                                return false;
                            }
                            lore.remove(lore.size() - 1);
                            itemmeta.setLore(lore);
                            player.sendMessage("§aSucessfully removed last line from item lore");
                        } else if (count == 2) {
                            int line = Integer.parseInt(args[1]);
                            if (line > lore.size()) {
                                player.sendMessage("§cError: Line number doesn't exist in item lore");
                                return false;
                            }
                            lore.remove(line - 1);
                            itemmeta.setLore(lore);
                            player.sendMessage("§aSucessfully removed lore at line #" + line);
                        } else {
                            sender.sendMessage("§cError: Too many arguments.");
                            return false;
                        }
                        player.getItemInHand().setItemMeta(itemmeta);
                        return true;
                    }
                    player.sendMessage("§cYou don't have permission  to use this command");
                    return false;
                }
                if (args[0].equalsIgnoreCase("clear")) {
                    if (player.hasPermission("ar.lore") || player.hasPermission("ar.*")) {
                        if (count > 1) {
                            player.sendMessage( "§cToo Many Arguments");
                            return false;
                        }
                        if (itemmeta.getLore() == null) {
                            player.sendMessage( "§cError: Item has no lore");
                            return false;
                        }
                        lore.clear();
                        itemmeta.setLore(lore);
                        player.getItemInHand().setItemMeta(itemmeta);
                        player.sendMessage("§aSucessfully cleared item lore");
                        return true;
                    }
                    player.sendMessage("§cYou don't have permission  to use this command");
                    return false;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    if (player.hasPermission("ar.lore") || player.hasPermission("ar.*")) {
                        player.sendMessage("§a/lore add <§cLore§a>");
                        player.sendMessage("§a/lore set <§cLine§a> <§cLore§a>");
                        player.sendMessage("§a/lore clear");
                        player.sendMessage("§a/lore remove <§cLine§a>");
                        player.sendMessage("§a/lore remove ");
                    }
                    player.sendMessage("§cYou don't have permission  to use this command");
                    return false;
                }

            }
        } else {
            player.sendMessage( "§cYou don't have permission to use this command");
        }
        return false;
    }
    private String pLore(String str) {
        str = str.replace('_', ' ');
        return str;
    }


}
