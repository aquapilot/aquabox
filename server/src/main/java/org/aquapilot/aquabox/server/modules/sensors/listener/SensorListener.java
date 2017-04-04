/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors.listener;

import org.aquapilot.aquabox.api.event.SensorBatteryStatusEvent;
import org.aquapilot.aquabox.api.event.SensorDetectedEvent;
import org.aquapilot.aquabox.api.event.SensorUnreachableEvent;
import org.aquapilot.aquabox.api.event.SensorValueChangeEvent;

/**
 * This class TODO
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface SensorListener {

   /**
    * Called when a sensor sent its measures
    *
    * @param event
    */
   void onSensorValueChange(SensorValueChangeEvent event);

   /**
    * Called when a sensor sent its battery status
    *
    * @param event
    */
   default void onSensorSendBatteryStatus(SensorBatteryStatusEvent event) {

   }

   /**
    * Called when a new sensor is connected to the aquabox network
    *
    * @param event
    */
   default void onNewSensorDetected(SensorDetectedEvent event) {

   }

   /**
    * Called when a sensor is no more reachable
    *
    * @param event
    */
   default void onSensorUnreachable(SensorUnreachableEvent event) {

   }

}
