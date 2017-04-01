package org.aquapilot.aquabox.plugin.statusled;

import org.aquapilot.aquabox.api.JavaPlugin;
import org.aquapilot.aquabox.plugin.statusled.listener.LedSensorListener;
import org.aquapilot.aquabox.plugins.PluginManager;

/**
 * Created by vermeille on 31.03.2017.
 */
public class StatusLed extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().debug("Starting Statusled plugin");
        PluginManager pm = this.getPluginManager();
      //  pm.registerEvent(new LedSensorListener(), this);
    }

    @Override
    public void onDisable() {

        getLogger().debug("Stopped Statusled plugin");
    }
}
