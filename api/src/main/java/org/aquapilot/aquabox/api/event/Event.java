/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api.event;

import org.aquapilot.aquabox.api.event.gpio.PinStateChangedEvent;
import org.aquapilot.aquabox.api.event.gpio.PinStateHighEvent;
import org.aquapilot.aquabox.api.event.gpio.PinStateLowEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorBatteryStatusEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorDetectedEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorUnreachableEvent;
import org.aquapilot.aquabox.api.event.sensor.SensorValueChangeEvent;

/**
 * This enum link all events with their event interface.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public enum Event {

   // Events -> Interface mapping
   PIN_STATE_CHANGED_EVENT(PinStateChangedEvent.class), PIN_STATE_HIGH_EVENT(
         PinStateHighEvent.class), PIN_STATE_LOW_EVENT(PinStateLowEvent.class), SENSOR_VALUE_CHANGED_EVENT(
         SensorValueChangeEvent.class), SENSOR_DETECTED_EVENT(SensorDetectedEvent.class), SENSOR_BATTERY_STATUS_EVENT(
         SensorBatteryStatusEvent.class), SENSOR_UNREACHABLE_EVENT(SensorUnreachableEvent.class);

   private Class<? extends AquaboxEvent> clazz;

   Event(Class<? extends AquaboxEvent> clazz) {

      this.clazz = clazz;
   }

   public static Event valueOf(AquaboxEvent event) {

      return valueOf(event.getClass());

   }

   public static Event valueOf(Class<? extends AquaboxEvent> clazz) {

      for (Event event : Event.values()) {

         if (event.getAssociatedClass().equals(clazz)) {
            return event;
         }
      }
      return null; // Todo throw exception
   }

   public Class<? extends AquaboxEvent> getAssociatedClass() {

      return this.clazz;
   }
}
