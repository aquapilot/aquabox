/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server;

import org.aquapilot.aquabox.server.common.CreditsUtil;
import org.aquapilot.aquabox.server.common.Service;
import org.aquapilot.aquabox.server.common.SystemUtil;
import org.aquapilot.aquabox.server.common.asciiart.FigletFontAsciiArtConverter;
import org.aquapilot.aquabox.server.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.notifier.model.NewSensorDetectedNotification;
import org.aquapilot.aquabox.server.modules.notifier.serices.NotifierService;
import org.aquapilot.aquabox.server.modules.sensors.SensorService;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorDetectedEvent;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorValueChangeEvent;
import org.aquapilot.aquabox.server.modules.sensors.listener.SensorListener;
import org.aquapilot.aquabox.server.modules.storage.services.StorageService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is the main class of Aquabox.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class Aquabox {

   @Log
   private Logger log;

   private boolean started = false;
   private Set<Service> registeredServices = new HashSet<>();

   private SensorService sensorService;
   private StorageService storageService;
   private NotifierService notifierService;

   @Inject
   public void setServices(StorageService storageService, SensorService sensorService, GPIOService gpioService,
         NotifierService notifierService) {

      this.registerService(storageService);
      this.registerService(sensorService);
      this.registerService(gpioService);
      this.registerService(notifierService);

      // TODO not really beautifull code redundancy
      this.sensorService = sensorService;
      this.storageService = storageService;
      this.notifierService = notifierService;
   }

   private void registerService(Service service) {

      this.registeredServices.add(service);
   }

   /**
    * Start the aquabox
    */
   public void start() {

      if (this.started) {
         this.log.warn("Could not start aquabox, it is already running.");
         return;
      }

      init();

      this.started = true;
      while (this.started) {
         try {
            Thread.sleep(3000);
         } catch (InterruptedException e) {
            this.log.warn("Aquabox thread was interrupted", e);
            Thread.currentThread().interrupt();
            this.started = false;
         }
      }

      stop(); // If we are here, it means the aquabox infinite loop is aborted so we stop the app
   }

   /**
    * Stop the aquabox
    */
   public void stop() {

      this.started = false;
      this.log.info("Stopping services ...");
      for (Service service : this.registeredServices) {
         service.stop();
      }
   }

   private void init() {

      new CreditsUtil(new FigletFontAsciiArtConverter()).printCredits();
      new SystemUtil().checkSystem();

      try {

         this.log.info("Starting services ...");
         for (Service service : this.registeredServices) {
            service.start();
         }

         this.sensorService.registerListener(new SensorListener() {

            @Override
            public void onSensorValueChange(SensorValueChangeEvent event) {

               Aquabox.this.log.debug(
                     String.format("Sensor uuid=%s sent a new value %s", event.getUUID(), event.getNewValue()));

               Aquabox.this.storageService.saveMeasure(event.getUUID(), event.getNewValue());
            }

            @Override
            public void onNewSensorDetected(SensorDetectedEvent event) {

               Aquabox.this.log.debug(String.format("A new sensor with uuid=%s has been detected", event.getUUID()));

               // Notify firebase
               Aquabox.this.notifierService.notify(new NewSensorDetectedNotification(event.getUUID()));
            }
         });

      } catch (Exception exception) {
         this.log.error("We applogize an unexpected error occured.", exception);
      }

   }

}
