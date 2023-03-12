package dev.teraprath.corelib.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class CommandBuilder implements CommandExecutor, TabCompleter {

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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> list = new ArrayList<>();
        String current = args[args.length - 1].toLowerCase();

        for (int i = 0; i < this.possibleArgs.size(); i++) {
            if (args.length == i) {
                list.addAll(Arrays.asList(this.possibleArgs.get(i)));
            }
        }

        list.removeIf(s -> !s.toLowerCase().startsWith(current));
        return list;
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
