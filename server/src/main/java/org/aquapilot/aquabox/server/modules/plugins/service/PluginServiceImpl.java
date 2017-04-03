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
import org.aquapilot.aquabox.api.PluginManager;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;
import org.slf4j.Logger;

import javax.inject.Singleton;
import java.nio.file.Paths;
import java.util.Collection;

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

   private PluginManager pluginManager;

   public PluginServiceImpl() {
      pluginManager = new PluginManagerImpl();
   }

   @Override
   public void start() throws Exception {

      this.log.debug(">> Plugin service started");
      Collection<JavaPlugin> plugins = pluginManager.loadPlugins(Paths.get(PLUGIN_DIR));
      for(JavaPlugin plugin : plugins) {
         this.log.info("load "+plugin.getPluginDescriptor().getName());
         pluginManager.enablePlugin(plugin);
      }

   }

   @Override
   public void stop() {

      this.log.debug(">>> Plugin service stopped");
   }
}
