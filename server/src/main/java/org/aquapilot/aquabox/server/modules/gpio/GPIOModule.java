/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.gpio;

import com.google.inject.AbstractModule;
import org.aquapilot.aquabox.server.Environment;
import org.aquapilot.aquabox.server.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.server.modules.gpio.services.PI4JGPIOServiceImpl;
import org.aquapilot.aquabox.server.modules.gpio.services.PI4JMockGPIOServiceImpl;

/**
 * This Module provide ability to communicate with the raspberry pi GPIO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class GPIOModule extends AbstractModule {

   private final Environment environment;

   public GPIOModule(Environment env) {
      this.environment = env;
   }

   @Override
   protected void configure() {

      Class classToLoad = null;

      if(Environment.RASPBERRY_PI == this.environment){
         classToLoad = PI4JGPIOServiceImpl.class;
      } else if(Environment.SIMULATOR == this.environment) {
         classToLoad = PI4JMockGPIOServiceImpl.class;
      }

      bind(GPIOService.class).to(classToLoad).asEagerSingleton();
   }
}
