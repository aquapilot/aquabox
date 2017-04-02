/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.plugin.statusled;

import org.aquapilot.aquabox.api.JavaPlugin;
import org.aquapilot.aquabox.api.PluginManager;

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
