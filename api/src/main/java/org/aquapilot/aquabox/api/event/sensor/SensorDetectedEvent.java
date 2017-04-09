/*
 * This file is part of the Aquapilot package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package org.aquapilot.aquabox.api.event.sensor;

/**
 * Event fired when a new sensor is detected by the aquabox.
 * <p>Note: to be qualified as a new sensor, the sensor need to have an uuid unknown from the system.</p>
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface SensorDetectedEvent extends SensorEvent {

   /**
    * Return the concerned sensor unique id
    *
    * @return
    */
   String getUUID();

}
