/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors;

import com.google.common.eventbus.EventBus;
import com.pi4j.io.spi.SpiDevice;
import org.aquapilot.aquabox.api.event.sensor.SensorBatteryStatusEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorDetectedEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorUnreachableEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorValueChangeEvent;
import org.aquapilot.aquabox.server.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorBatteryStatusEventImpl;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorDetectedEventImpl;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorUnreachableEventImpl;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorValueChangeEventImpl;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
public class SensorMockServiceImpl implements SensorService {

   private EventBus eventBus;

   @Log
   Logger log;
   private GPIOService gpioService;
   private ExecutorService executor;

   //setter method injector
   @Inject
   public void setServices(GPIOService gpioService, EventBus eventBus) {

      this.gpioService = gpioService;
      this.eventBus = eventBus;
   }

   private int randomBetween(int min, int max) {

      Random r = new Random();
      int result = r.nextInt(max - min) + min;
      return result;
   }

   @Override
   public void start() throws Exception {

      SpiDevice spi = this.gpioService.getSPI();
      this.executor = Executors.newSingleThreadExecutor();
      this.executor.submit(() -> {

         List<String> registredUUIDs = new ArrayList<>();
         for (int i = 0; i < 3; i++) {
            registredUUIDs.add(UUID.randomUUID().toString());
         }
         while (true) {

            TimeUnit.SECONDS.sleep(8);

            // generate random receptions
               switch (randomBetween(0, 10)) {
                  case 0:
                     SensorDetectedEvent event = SensorDetectedEventImpl
                           .newInstance()
                           .UUID(UUID.randomUUID().toString())
                           .build();
                     eventBus.post(event);
                     break;
                  case 1:
                     SensorBatteryStatusEvent sensorBatteryStatusEvent = SensorBatteryStatusEventImpl
                           .newInstance()
                           .UUID(registredUUIDs.get(randomBetween(0, 2)))
                           .build();
                     eventBus.post(sensorBatteryStatusEvent);
                     break;
                  case 2:
                     SensorUnreachableEvent sensorUnreachableEvent = SensorUnreachableEventImpl
                           .newInstance()
                           .UUID(registredUUIDs.get(randomBetween(0, 2)))
                           .build();
                     eventBus.post(sensorUnreachableEvent);
                     break;
                  default:
                     SensorValueChangeEvent sensorValueChangeEvent = SensorValueChangeEventImpl
                           .newInstance()
                           .UUID(registredUUIDs.get(randomBetween(0, 2)))
                           .oldValue("15.0")
                           .newValue("22.4")
                           .build();
                     eventBus.post(sensorValueChangeEvent);
                     break;
               }

            }

      });
      this.log.debug(">> Sensor Service started");
   }

   @Override
   public void stop() {

      if (this.executor.isShutdown()) {
         return;
      }

      this.executor.shutdownNow();
      this.log.debug(">> Sensor Service stopped");
   }
}
