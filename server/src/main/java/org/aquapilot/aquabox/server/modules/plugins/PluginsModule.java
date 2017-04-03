/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.plugins;

import com.google.inject.AbstractModule;
import org.aquapilot.aquabox.api.PluginManager;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;
import org.aquapilot.aquabox.server.modules.plugins.service.PluginService;
import org.aquapilot.aquabox.server.modules.plugins.service.PluginServiceImpl;

/**
 * This Module provide plugins management
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class PluginsModule extends AbstractModule {

   @Override
   protected void configure() {

      bind(PluginManager.class).to(PluginManagerImpl.class).asEagerSingleton();
      bind(PluginService.class).to(PluginServiceImpl.class).asEagerSingleton();
   }
}
