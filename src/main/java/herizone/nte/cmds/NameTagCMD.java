package herizone.nte.cmds;

import herizone.nte.NTE;
import herizone.nte.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NameTagCMD implements CommandExecutor {

    private NTE plugin = JavaPlugin.getPlugin(NTE.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.colorize(Message.NO_CONSOLE.get()));
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("nametag.use")) {
            if (args.length == 0) {
                p.sendMessage(plugin.colorize("&eNameTagEditor > &7Command List"));
                p.sendMessage(plugin.colorize(" &8- " + Message.CREATE_SYNTAX.get()));
                p.sendMessage(plugin.colorize(" &8- " + Message.DELETE_SYNTAX.get()));
                p.sendMessage(plugin.colorize(" &8- " + Message.SET_PS_SYNTAX.get()));
                p.sendMessage(plugin.colorize(" &8- " + Message.SET_SYNTAX.get()));
                p.sendMessage(plugin.colorize(" &8- " + Message.UNSET_SYNTAX.get()));
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("create")) {
                    p.sendMessage(plugin.colorize(Message.CREATE_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("delete")) {
                    p.sendMessage(plugin.colorize(Message.DELETE_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("tag")) {
                    p.sendMessage(plugin.colorize(Message.SET_PS_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("set")) {
                    p.sendMessage(plugin.colorize(Message.SET_TAG_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("unset")) {
                    p.sendMessage(plugin.colorize(Message.UNSET_TAG_USAGE.get()));
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {
                    plugin.getNameTag().createTag(p, args[1].toUpperCase());
                }
                if (args[0].equalsIgnoreCase("delete")) {
                    plugin.getNameTag().deleteTag(p, args[1].toUpperCase());
                }
                if (args[0].equalsIgnoreCase("tag")) {
                    p.sendMessage(plugin.colorize(Message.SET_PS_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("set")) {
                    p.sendMessage(plugin.colorize(Message.SET_TAG_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("unset")) {
                    plugin.getNameTag().unsetPlayerTag(p, plugin.getServer().getPlayer(args[1]));
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("create")) {
                    p.sendMessage(plugin.colorize(Message.CREATE_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("delete")) {
                    p.sendMessage(plugin.colorize(Message.DELETE_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("unset")) {
                    p.sendMessage(plugin.colorize(Message.UNSET_TAG_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("tag")) {
                    if (args[2].equalsIgnoreCase("prefix")) {
                        p.sendMessage(plugin.colorize(Message.SET_PREFIX_USAGE.get()));
                    } else if (args[2].equalsIgnoreCase("suffix")) {
                        p.sendMessage(plugin.colorize(Message.SET_SUFFIX_USAGE.get()));
                    } else {
                        p.sendMessage(plugin.colorize(Message.SET_PS_USAGE.get()));
                    }
                }
                if (args[0].equalsIgnoreCase("set")) {
                    plugin.getNameTag().setPlayerTag(p, plugin.getServer().getPlayer(args[1]), args[2].toUpperCase());
                }
            }
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("create")) {
                    p.sendMessage(plugin.colorize(Message.CREATE_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("delete")) {
                    p.sendMessage(plugin.colorize(Message.DELETE_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("tag")) {
                    if (args[2].equalsIgnoreCase("prefix")) {
                        plugin.getNameTag().setTagPrefix(p, args[1].toUpperCase(), args[3]);
                    } else if (args[2].equalsIgnoreCase("suffix")) {
                        plugin.getNameTag().setTagSuffix(p, args[1].toUpperCase(), args[3]);
                    } else {
                        p.sendMessage(plugin.colorize(Message.SET_PS_USAGE.get()));
                    }
                }
                if (args[0].equalsIgnoreCase("set")) {
                    p.sendMessage(plugin.colorize(Message.SET_TAG_USAGE.get()));
                }
                if (args[0].equalsIgnoreCase("unset")) {
                    p.sendMessage(plugin.colorize(Message.UNSET_TAG_USAGE.get()));
                }
            }
        } else {
            p.sendMessage(plugin.colorize(Message.NO_PERMISSION.get()));
        }
        return true;
    }
}
