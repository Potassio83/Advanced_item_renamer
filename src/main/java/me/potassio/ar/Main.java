package me.potassio.ar;

import me.potassio.ar.Commands.rename;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    ConsoleCommandSender console = Bukkit.getConsoleSender();


    public void onEnable() {
        comandi();
        console.sendMessage("§6--------------------------------- ");
        console.sendMessage("§6Advanced item renamer by Potassio");
        console.sendMessage("§6----------------------------------");





    }
    @Override
    public void onDisable() {
        console.sendMessage("§6------------------ ");
        console.sendMessage("  §6Plugin Disabled  ");
        console.sendMessage("§6------------------ ");




    }


    private void comandi() {

        getCommand("rename").setExecutor(new rename());

    }





}
