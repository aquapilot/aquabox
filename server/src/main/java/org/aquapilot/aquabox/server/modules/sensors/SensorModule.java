/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors;

import com.google.inject.AbstractModule;
import org.aquapilot.aquabox.server.Environment;

/**
 * This Module provide communication between sensors (gpio) and storage
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorModule extends AbstractModule {

   private final Environment environment;

   public SensorModule(Environment env){
      this.environment = env;
   }

   @Override
   protected void configure() {

      Class classToLoad = null;
      if(Environment.RASPBERRY_PI == environment) {
         classToLoad = SensorServiceImpl.class;
      } else if(Environment.SIMULATOR == environment){
         classToLoad = SensorMockServiceImpl.class;
      }

      bind(SensorService.class).to(classToLoad).asEagerSingleton();
   }
}
