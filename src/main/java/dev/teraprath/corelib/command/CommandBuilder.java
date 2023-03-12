package dev.teraprath.corelib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class CommandBuilder implements CommandExecutor {

    protected CommandSender sender;
    protected String globalPermission;
    protected HashMap<Integer, String[]> possibleArgs;
    protected boolean playerOnly;
    protected String[] args;
    protected String permissionMessage = "You cannot do that!";
    protected String noPlayerMessage = "You're not a player!";

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

        if (globalPermission != null && !(sender.hasPermission(globalPermission))) {
            sender.sendMessage(permissionMessage);
            return false;
        }

        handle();
        return false;

    }

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

    public void setPossibleArgs(int index, String[] args) {
        this.possibleArgs.put(index, args);
    }

    public String[] getArgs() {
        return this.args;
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public Player getPlayer() {
        return (Player) this.sender;
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
