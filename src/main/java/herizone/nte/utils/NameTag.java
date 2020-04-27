package herizone.nte.utils;

import herizone.nte.NTE;
import herizone.nte.NameTagConfig;
import herizone.nte.UserConfig;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class NameTag {

    private ArrayList<Tag> tags = new ArrayList<>();
    private HashMap<UUID, Tag> setTags = new HashMap<>();
    private NTE plugin = JavaPlugin.getPlugin(NTE.class);

    public void createTag(Player attempter, String identifier) {
        Tag tag = new Tag(identifier, "none", "none");
        for (Tag t : tags) {
            if (t.getIdentifier().equals(identifier)) {
                attempter.sendMessage(plugin.colorize(Message.CREATED_UNSUCCESSFULLY.get().replace("{id}", identifier)));
                return;
            }
        }
        attempter.sendMessage(plugin.colorize(Message.CREATED_SUCCESSFULLY.get().replace("{id}", identifier)));
        tags.add(tag);
        plugin.getTagConfig().set("tags." + identifier + ".prefix", "none");
        plugin.getTagConfig().set("tags." + identifier + ".suffix", "none");
    }

    public void deleteTag(Player attempter, String identifier) {
        for (Tag t : tags) {
            if (t.getIdentifier().equals(identifier)) {
                tags.remove(t);
                plugin.getTagConfig().set("tags." + identifier, null);
                if (getSetTags().containsValue(t)) {
                    for (String uuid : plugin.getUserConfig().getConfig().getConfigurationSection("users").getKeys(false)) {
                        if (plugin.getUserConfig().getConfig().getString("users." + uuid).equals(identifier)) {
                            plugin.getUserConfig().set("users." + uuid, null);
                        }
                    }
                }
                attempter.sendMessage(plugin.colorize(Message.DELETED_SUCCESSFULLY.get().replace("{id}", identifier)));
                return;
            }
        }
        attempter.sendMessage(plugin.colorize(Message.TAG_NOT_FOUND.get().replace("{id}",identifier)));
    }

    public void setTagPrefix(Player attempter, String identifier, String prefix) {
        for (Tag t : tags) {
            if (t.getIdentifier().equals(identifier)) {
                t.setPrefix(prefix);
                plugin.getTagConfig().set("tags." + identifier + ".prefix", prefix);
                attempter.sendMessage(plugin.colorize(Message.SET_PREFIX_SUCCESSFULLY.get().replace("{id}", identifier).replace("{prefix}", prefix)));
                return;
            }
        }
        attempter.sendMessage(plugin.colorize(Message.TAG_NOT_FOUND.get().replace("{id}", identifier)));
    }

    public void setTagSuffix(Player attempter, String identifier, String suffix) {
        for (Tag t : tags) {
            if (t.getIdentifier().equals(identifier)) {
                t.setSuffix(suffix);
                plugin.getTagConfig().set("tags." + identifier + ".suffix", suffix);
                attempter.sendMessage(plugin.colorize(Message.SET_SUFFIX_SUCCESSFULLY.get().replace("{id}", identifier).replace("{suffix}", suffix)));
                return;
            }
        }
        attempter.sendMessage(plugin.colorize(Message.TAG_NOT_FOUND.get().replace("{id}", identifier)));
    }

    public void setPlayerTag(Player attempter, Player target, String identifier) {
        if (target == null) {
            attempter.sendMessage(plugin.colorize(Message.PLAYER_NOT_FOUND.get()));
        } else {
            for (Tag t : tags) {
                if (t.getIdentifier().equals(identifier)) {
                    if (getSetTags().containsKey(target.getUniqueId())) {
                        getSetTags().remove(target.getUniqueId());
                    }
                    getSetTags().put(target.getUniqueId(), t);
                    plugin.getUserConfig().set("users." + target.getUniqueId(), identifier);
                    attempter.sendMessage(plugin.colorize(Message.TAG_SET_SUCCESSFULLY.get().replace("{player}", target.getName()).replace("{id}", identifier)));
                    return;
                }
            }
            attempter.sendMessage(plugin.colorize(Message.TAG_NOT_FOUND.get().replace("{id}", identifier)));
        }
    }

    public void unsetPlayerTag(Player attempter, Player target) {
        if (target == null) {
            attempter.sendMessage(plugin.colorize(Message.PLAYER_NOT_FOUND.get()));
        } else {
            if (getSetTags().containsKey(target.getUniqueId())) {
                plugin.getUserConfig().set("users." + target.getUniqueId(), null);
                getSetTags().remove(target.getUniqueId());
                attempter.sendMessage(plugin.colorize(Message.TAG_UNSET_SUCCESSFULLY.get().replace("{player}", target.getName())));
            } else {
                attempter.sendMessage(plugin.colorize(Message.PLAYER_NO_TAG.get().replace("{player}", target.getName())));
            }
        }
    }

    public HashMap<UUID, Tag> getSetTags() {
        return setTags;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void readTagsConfig(NameTagConfig config) {
        if (config.getConfig().contains("tags")) {
            plugin.getServer().getConsoleSender().sendMessage(plugin.colorize("&eNameTagEditor > &7Starting to register past tags..."));
            for (String id : config.getConfig().getConfigurationSection("tags").getKeys(false)) {
                Tag tag = new Tag(id, config.getConfig().getString("tags." + id + ".prefix"), config.getConfig().getString("tags." + id + ".suffix"));
                plugin.getServer().getConsoleSender().sendMessage(plugin.colorize("&eNameTagEditor > &7Registered tag &f" + id + "&7."));
                getTags().add(tag);
            }
            plugin.getServer().getConsoleSender().sendMessage(plugin.colorize("&eNameTagEditor > &7Finished registering past tags."));
        }
    }

    public void readUsersConfig(UserConfig config) {
        if (config.getConfig().contains("users")) {
            plugin.getServer().getConsoleSender().sendMessage(plugin.colorize("&eNameTagEditor > &7Starting to register past users..."));
            for (String uuid : config.getConfig().getConfigurationSection("users").getKeys(false)) {
                for (Tag t : getTags()) {
                    if (config.getConfig().getString("users." + uuid).equals(t.getIdentifier())) {
                        getSetTags().put(UUID.fromString(uuid), t);
                        plugin.getServer().getConsoleSender().sendMessage(plugin.colorize("&eNameTagEditor > &7Registered user &f" + uuid + "&7."));
                    }
                }
            }
            plugin.getServer().getConsoleSender().sendMessage(plugin.colorize("&eNameTagEditor > &7Finished registering past users."));
        }
    }
}
