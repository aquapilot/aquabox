/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.modules.sensors;

import com.google.inject.AbstractModule;

/**
 * This Module provide communication between sensors (gpio) and storage
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorModule extends AbstractModule {

   @Override
   protected void configure() {

      bind(SensorService.class).to(SensorMockServiceImpl.class).asEagerSingleton();
   }
}
