package dev.teraprath.corelib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandBuilder implements CommandExecutor, TabCompleter {

    private final String globalPermission;
    private final boolean playerOnly;
    private String permissionMessage = "You cannot do that!";
    private String noPlayerMessage = "You're not a player!";
    protected String[] args;
    protected CommandSender sender;
    protected Player player;

    public CommandBuilder(String globalPermission, boolean playerOnly) {
        this.globalPermission = globalPermission;
        this.playerOnly = playerOnly;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        this.sender = sender;
        this.args = args;

        if (playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(noPlayerMessage);
            return false;
        }

        if (sender instanceof Player) {
            this.player = (Player) sender;
        }

        if (globalPermission != null && !(sender.hasPermission(globalPermission))) {
            sender.sendMessage(permissionMessage);
            return false;
        }

        handle();
        return false;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return onTabComplete();
    }

    public abstract ArrayList<String> onTabComplete();

    public void setPermissionMessage(String permissionMessage) {
        this.permissionMessage = permissionMessage;
    }

    public String getPermissionMessage() {
        return this.permissionMessage;
    }

    public void setNoPlayerMessage(String noPlayerMessage) {
        this.noPlayerMessage = noPlayerMessage;
    }

    public String getNoPlayerMessage() {
        return this.noPlayerMessage;
    }

    public boolean isPlayer() {
        return (this.sender instanceof Player);
    }

    public boolean hasPermission(String permission, boolean subPermission) {
        final boolean hasPermission = this.sender.hasPermission(subPermission ? globalPermission + "." + permission : permission);
        if (!hasPermission) {
            this.sender.sendMessage(permissionMessage);
        }
        return hasPermission;
    }

    public abstract void handle();

}
