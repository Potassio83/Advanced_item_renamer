package me.potassio.ar.Commands;

import me.potassio.ar.F;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class rename implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("rename")) {

            Player p = (Player) sender;
            ConsoleCommandSender console = Bukkit.getConsoleSender();

            F f = new F();
            if (sender != null) {

                if (p.hasPermission("ar.rename")) {

                        String name = "";

                        for (String s : args) {
                            String arg = (s + "");
                            name = (name + arg);
                        }
                       try{ ItemStack item = new ItemStack(p.getItemInHand().getType());
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.setDisplayName(f.translateHexCodes(name));
                        item.setItemMeta(itemMeta);
                        item.setAmount(p.getItemInHand().getAmount());
                        p.setItemInHand(item);
                        p.sendMessage("§aItem Renamed");

                    }catch (Exception e){
                        p.sendMessage("§cyou do not have an item in your hand");
                    }


                } else {

                    p.sendMessage("§cNo perm");
                }
            }else{

                console.sendMessage("§cOnly Player");
            }
        }
        return false;
    }
}

