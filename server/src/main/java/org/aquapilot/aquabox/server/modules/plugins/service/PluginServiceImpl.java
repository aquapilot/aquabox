/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.plugins.service;

import org.aquapilot.aquabox.api.JavaPlugin;
import org.aquapilot.aquabox.api.event.Event;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;
import org.slf4j.Logger;

import javax.inject.Singleton;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This class implement PluginService
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class PluginServiceImpl implements PluginService {

   @Log
   Logger log;

   private final static String PLUGIN_DIR = "plugins";

   private PluginManagerImpl pluginManager;

   public PluginServiceImpl() {
      pluginManager = new PluginManagerImpl();
   }

   @Override
   public void start() throws Exception {

      this.log.debug(">> Plugin service started");
      this.log.info("Loading plugins ...");
      Collection<JavaPlugin> plugins = pluginManager.loadPlugins(Paths.get(PLUGIN_DIR));

      this.log.info(String.format("found %s plugins in plugins/ directory", plugins.size()));

      for(JavaPlugin plugin : plugins) {
         this.log.info(String.format(">> %s (v%s, by %s)", plugin.getPluginDescriptor().getName(),
                                     plugin.getPluginDescriptor().getVersion(),
                                     plugin.getPluginDescriptor().getAuthor()));

      }

      for (JavaPlugin plugin : plugins) {
         this.log.debug(String.format("Enabling plugin %s", plugin.getPluginDescriptor().getName()));
         pluginManager.enablePlugin(plugin);
      }

   }

   @Override
   public Map<Event, List<PluginManagerImpl.EventRegistration>> getRegisteredEvents(){
      return this.pluginManager.getRegisteredEvents();
   }


   @Override
   public void stop() {

      this.log.debug(">> Plugin service stopped");
   }
}
