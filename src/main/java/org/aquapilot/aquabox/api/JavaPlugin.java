package org.aquapilot.aquabox.api;

import org.aquapilot.aquabox.plugins.PluginManager;
import org.slf4j.Logger;

import java.util.Properties;

/**
 * Created by vermeille on 31.03.2017.
 */
public abstract class JavaPlugin {

    public abstract void onEnable();

    public abstract void onDisable();

    public Logger getLogger() {

        return null;
    }

    public Properties getPluginProperties() {
        return null;
    }

    public PluginManager getPluginManager() {
        return null;
    }
}
