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
import org.aquapilot.aquabox.server.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.server.modules.gpio.services.PI4JMockGPIOServiceImpl;

/**
 * This Module provide ability to communicate with the raspberry pi GPIO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class GPIOModule extends AbstractModule {

   @Override
   protected void configure() {

      bind(GPIOService.class).to(PI4JMockGPIOServiceImpl.class).asEagerSingleton();
   }
}
