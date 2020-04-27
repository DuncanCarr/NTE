package herizone.nte;

import herizone.nte.utils.Config;
import org.bukkit.plugin.Plugin;

public class UserConfig extends Config {

    public UserConfig(NTE plugin) {
        super(plugin, "users.yml");
    }

}
