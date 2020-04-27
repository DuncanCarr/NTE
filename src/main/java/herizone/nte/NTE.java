package herizone.nte;

import herizone.nte.cmds.NameTagCMD;
import herizone.nte.utils.NameTag;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class NTE extends JavaPlugin {

    private NameTagConfig tagConfig;
    private UserConfig userConfig;
    private NameTag nameTag;

    @Override
    public void onEnable() {
        nameTag = new NameTag();
        tagConfig = new NameTagConfig(this);
        userConfig = new UserConfig(this);

        getNameTag().readTagsConfig(getTagConfig());
        getNameTag().readUsersConfig(getUserConfig());

        getCommand("nametag").setExecutor(new NameTagCMD());
    }

    public String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    @Override
    public void onDisable() {

    }

    public NameTag getNameTag() {
        return nameTag;
    }

    public NameTagConfig getTagConfig() {
        return tagConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }
}
