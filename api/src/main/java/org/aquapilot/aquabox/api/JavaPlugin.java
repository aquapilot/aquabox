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
 * Created by vermeille on 31.03.2017.
 */
public abstract class JavaPlugin {

   private final Logger LOGGER = LoggerFactory.getLogger(getClass());

   private PluginDescriptor descriptor;

   public JavaPlugin(){}

//   public JavaPlugin(PluginDescriptor descriptor){
//       this.descriptor = descriptor;
//   }

    protected void init(PluginDescriptor descriptor){
       this.descriptor = descriptor;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public Logger getLogger() {

       return LOGGER;
    }

    public PluginDescriptor getPluginDescriptor() {
        return descriptor;
    }
}
