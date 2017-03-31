package org.aquapilot.aquabox.api;

import org.slf4j.Logger;

/**
 * Created by vermeille on 31.03.2017.
 */
public abstract class JavaPlugin {

   public abstract void onEnable();

   public abstract void onDisable();

   public Logger getLogger() {

      return null;
   }
}
