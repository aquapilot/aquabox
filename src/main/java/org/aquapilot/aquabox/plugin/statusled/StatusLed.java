package org.aquapilot.aquabox.plugin.statusled;

import org.aquapilot.aquabox.api.JavaPlugin;

/**
 * Created by vermeille on 31.03.2017.
 */
public class StatusLed extends JavaPlugin {

   @Override
   public void onEnable() {

      getLogger().debug("Starting Statusled plugin");
   }

   @Override
   public void onDisable() {

      getLogger().debug("Stopped Statusled plugin");
   }
}
