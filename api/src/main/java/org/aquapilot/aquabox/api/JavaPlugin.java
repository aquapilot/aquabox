/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is inherited by plugins and provide a good starting point with access to global logger, plugin
 * informations and so on.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public abstract class JavaPlugin {

   private static final Logger LOGGER = LoggerFactory.getLogger(JavaPlugin.class);

   private PluginDescriptor descriptor;
   private PluginManager pluginManager;

   public JavaPlugin() {

   }

   /**
    * Init the plugin with usefull inputs. This method is called internally by the plugin manager. You shouldn't play
    * with it.
    * TODO: move this logic into a private constructor so that in one step the whole thing is done
    *
    * @param descriptor
    * @param pluginManager
    */
   protected void init(final PluginDescriptor descriptor, final PluginManager pluginManager) {

      this.descriptor = descriptor;
      this.pluginManager = pluginManager;
   }

   /**
    * Method called when the plugin is enabled by the aquabox
    */
   public abstract void onEnable();

   /**
    * Method called when the plugin is disabled by the aquabox
    */
   public abstract void onDisable();

   /**
    * Get the global logger used by the aquabox. This allow you to log informations direct in the aquabox log file.
    * <p>Note: Using this logger is recommended in most case because it simplify the errors analysis.</p>
    *
    * @return
    */
   public Logger getLogger() {

      return LOGGER;
   }

   /**
    * Get all informations declared in the plugin.ini file of the current plugin.
    *
    * @return
    */
   public PluginDescriptor getPluginDescriptor() {

      return this.descriptor;
   }

   /**
    * Get the plugin manager
    * <p>Example: you can register your listeners with {@link #getPluginManager()}</p>
    *
    * @return
    */
   public PluginManager getPluginManager() {

      return this.pluginManager;
   }
}
