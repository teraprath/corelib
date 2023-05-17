package dev.teraprath.corelib.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandBuilder implements CommandExecutor {

    protected CommandSender sender;
    protected Command command;
    protected String label;
    protected  String[] args;
    protected boolean isPlayer;
    protected Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;

        if (sender instanceof Player) {
            this.isPlayer = true;
            this.player = (Player) sender;
        }

        return onExecute();

    }

    public abstract boolean onExecute();

}
