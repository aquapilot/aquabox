/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.gpio.services;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.impl.SpiDeviceImpl;
import org.aquapilot.aquabox.server.modules.gpio.event.PinState;
import org.aquapilot.aquabox.server.modules.gpio.event.StateChangedEvent;
import org.aquapilot.aquabox.server.modules.gpio.listener.GPIOPinStateListener;
import org.aquapilot.aquabox.server.modules.logger.Log;
import org.slf4j.Logger;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

/**
 * This class provide simple way to manage PI4J in Aquabox
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Singleton
public class PI4JMockGPIOServiceImpl implements GPIOService {

   private static final GpioController gpio = null;
   @Log
   Logger log;
   Map<Pin, List<GPIOPinStateListener>> listeners = new HashMap<>();
   private ExecutorService executor;
   private Set<Pin> registeredInputPins = new HashSet<>();
   private Set<Pin> registeredOutputPins = new HashSet<>();

   public GpioController getGPIOController() {

      return gpio;
   }

   @Override
   public GpioPinDigitalInput registerInputDigitalPin(Pin pin) {

      this.registeredInputPins.add(pin);
      return null;
   }

   @Override
   public GpioPinDigitalOutput registerOutputDigitalPin(Pin pin) {

      this.registeredOutputPins.add(pin);
      return null;
   }

   @Override
   public Collection<Pin> getRegistredInputPins() {

      return this.registeredInputPins;
   }

   @Override
   public Collection<Pin> getRegistredOutputPins() {

      return this.registeredOutputPins;
   }

   @Override
   public void registerChangeListener(Pin pin, GPIOPinStateListener listener) {

      if (!this.listeners.containsKey(pin)) {
         this.listeners.put(pin, new ArrayList<>());
      }

      List<GPIOPinStateListener> registeredListeners = this.listeners.get(pin);
      registeredListeners.add(listener);
   }

   @Override
   public SpiDevice getSPI() {
      // create SPI object instance for SPI for communication
      return mock(SpiDeviceImpl.class);
   }

   @Override
   public void start() throws Exception {

      // Start a fake gpio simulator
      this.executor = Executors.newSingleThreadExecutor();
      this.executor.submit(() -> {
         while (true) {

            TimeUnit.SECONDS.sleep(10);

            for (Collection<GPIOPinStateListener> listenersCollection : this.listeners.values()) {
               listenersCollection.forEach(listener -> {

                  StateChangedEvent event = StateChangedEvent
                        .newInstance()
                        .oldState(PinState.LOW)
                        .newState(PinState.HIGH)
                        .pin(RaspiPin.GPIO_01)
                        .build();
                  listener.onPinStateChanged(event);
               });
            }

         }
      });

      this.log.debug(">> GPIO Mock Service started");
   }

   @Override
   public void stop() {

      if (this.executor.isShutdown()) {
         return;
      }

      this.executor.shutdownNow();
      this.log.debug(">> GPIO Mock Service stopped");
   }
}
