/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.server.modules.sensors.listener;

import org.aquapilot.aquabox.server.modules.sensors.event.SensorBatteryStatusEventImpl;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorDetectedEventImpl;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorUnreachableEventImpl;
import org.aquapilot.aquabox.server.modules.sensors.event.SensorValueChangeEventImpl;

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
   void onSensorValueChange(SensorValueChangeEventImpl event);

   /**
    * Called when a sensor sent its battery status
    *
    * @param event
    */
   default void onSensorSendBatteryStatus(SensorBatteryStatusEventImpl event) {

   }

   /**
    * Called when a new sensor is connected to the aquabox network
    *
    * @param event
    */
   default void onNewSensorDetected(SensorDetectedEventImpl event) {

   }

   /**
    * Called when a sensor is no more reachable
    *
    * @param event
    */
   default void onSensorUnreachable(SensorUnreachableEventImpl event) {

   }

}
