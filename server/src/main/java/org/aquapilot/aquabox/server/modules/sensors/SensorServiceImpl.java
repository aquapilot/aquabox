/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors;

import com.pi4j.io.i2c.I2CDevice;
import org.aquapilot.aquabox.server.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class manage the whole interactions between sensors and storage.
 * <p>
 * Basically it will communicate with the sensors
 * retrieve their values and save them to the specified storage engine.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SensorServiceImpl implements SensorService {

   private GPIOService gpioService;

   private ExecutorService executor;

   @Log
   Logger log;

   //setter method injector
   @Inject
   public void setServices(GPIOService gpioService) {

      this.gpioService = gpioService;
   }

   @Override
   public void start() throws Exception {

      I2CDevice tranceiver = gpioService.getI2CDevice();
      long waitTimeRead = 5;

      log.info("Sensor Service started");
      executor = Executors.newSingleThreadExecutor();
      executor.submit(() -> {
         while (true) {
            int dataRead = tranceiver.read();
            log.debug("sensor >>" + dataRead);
            TimeUnit.SECONDS.sleep(waitTimeRead);
         }
      });

   }

   @Override
   public void stop() {

      if (executor.isShutdown()) {
         return;
      }

      executor.shutdownNow();
      log.info(">> Sensor service stopped");
   }
}
