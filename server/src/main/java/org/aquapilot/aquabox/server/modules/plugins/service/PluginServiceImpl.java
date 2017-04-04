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
import org.aquapilot.aquabox.api.event.AquaboxEvent;
import org.aquapilot.aquabox.api.event.SensorValueChangeEvent;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;
import org.aquapilot.aquabox.server.modules.sensors.SensorService;
import org.aquapilot.aquabox.server.modules.sensors.listener.SensorListener;
import org.slf4j.Logger;

import javax.inject.Inject;
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

   private PluginManagerImpl pluginManager;

   private SensorService sensorService;

   @Inject
   public void setServices(SensorService sensorService) {

      this.sensorService = sensorService;
   }

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

      this.manageEvents();
      //this.pluginManager.getRegisteredEvents()
   }

   private void manageEvents() {

      this.sensorService.registerListener(new SensorListener() {

         @Override
         public void onSensorValueChange(SensorValueChangeEvent event) {

            handleEvent(event);
         }
      });

   }

   private void handleEvent(AquaboxEvent event) {
      // fire event to registered plugin listeners

   }

   @Override
   public void stop() {

      this.log.debug(">> Plugin service stopped");
   }
}
