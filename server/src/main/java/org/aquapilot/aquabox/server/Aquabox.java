/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server;

import com.google.common.eventbus.Subscribe;
import org.aquapilot.aquabox.api.event.AquaboxEvent;
import org.aquapilot.aquabox.api.event.Event;
import org.aquapilot.aquabox.server.common.CreditsUtil;
import org.aquapilot.aquabox.server.common.Service;
import org.aquapilot.aquabox.server.common.SystemUtil;
import org.aquapilot.aquabox.server.common.asciiart.FigletFontAsciiArtConverter;
import org.aquapilot.aquabox.server.modules.gpio.services.GPIOService;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.aquapilot.aquabox.server.modules.notifier.services.NotifierService;
import org.aquapilot.aquabox.server.modules.plugins.manager.PluginManagerImpl;
import org.aquapilot.aquabox.server.modules.plugins.service.PluginService;
import org.aquapilot.aquabox.server.modules.sensors.SensorService;
import org.aquapilot.aquabox.server.modules.storage.services.StorageService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is the main class of Aquabox.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class Aquabox {

   @Log
   @SuppressWarnings({ "unusedPrivate", "unused" })
   private Logger log;

   private final Set<Service> registeredServices = new HashSet<>();

   private PluginService pluginService;
   private boolean started = false;

   @Inject
   public void setServices(StorageService storageService, SensorService sensorService, GPIOService gpioService,
         PluginService pluginService, NotifierService notifierService) {

      this.pluginService = pluginService;

      this.registerService(storageService);
      this.registerService(sensorService);
      this.registerService(gpioService);
      this.registerService(notifierService);
      this.registerService(pluginService);

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

      } catch (Exception exception) {
         this.log.error("We applogize an unexpected error occured.", exception);
      }

   }

   /**
    * Each events of the aquabox are handled here
    */
   @Subscribe
   @SuppressWarnings("unused") // It is actually used, just managed by guava eventbus
   public void handleEvent(AquaboxEvent event) {

      Event ev = null;

      for (Class<?> interfaceClass : event.getClass().getInterfaces()) {
         // test if i is your interface
         if (AquaboxEvent.class.isAssignableFrom(interfaceClass)) {
            Class<? extends AquaboxEvent> eventInterfaceClass = (Class<? extends AquaboxEvent>) interfaceClass;
            ev = Event.valueOf(eventInterfaceClass);
            break;
         }
      }

      if (ev == null) {
         this.log.error("Unable to find an event refering to this implementation " + event.getClass());
      } else {
         System.out.println("CATCHED EVENT " + ev.name());

         // fire event to registered plugin listeners
         Map<Event, List<PluginManagerImpl.EventRegistration>> registeredEvents = this.pluginService.getRegisteredEvents();
         List<PluginManagerImpl.EventRegistration> methodsToCall = new ArrayList<>();
         if (registeredEvents.containsKey(ev)) {
            methodsToCall = registeredEvents.get(ev);
         }
         for (PluginManagerImpl.EventRegistration registration : methodsToCall) {
            propagateEvent(registration, ev, event);
         }
      }
   }

   /**
    * Propagate the given event to all plugins listening to it
    *
    * @param registration
    * @param ev
    * @param event
    */
   private void propagateEvent(PluginManagerImpl.EventRegistration registration, final Event ev, AquaboxEvent event) {

      ExecutorService executor = Executors.newSingleThreadExecutor();
      executor.submit(() -> {
         try {
            Method declaredMethod = registration
                  .getListener()
                  .getClass()
                  .getDeclaredMethod(registration.getMethod().getName(), ev.getAssociatedClass());

            declaredMethod.invoke(registration.getListener(), event);
         } catch (NoSuchMethodException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         } catch (InvocationTargetException e) {
            e.printStackTrace();
         }
      });
   }

}
