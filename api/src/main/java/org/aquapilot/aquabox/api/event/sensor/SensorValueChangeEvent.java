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
 * Event fired when a sensor notify the aquabox a new measure.
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface SensorValueChangeEvent extends SensorEvent {

   /**
    * Return the concerned sensor unique id
    *
    * @return
    */
   String getUUID();

   /**
    * Return the previous measure done by the sensor
    *
    * @return
    */
   String getOldValue();

   /**
    * Return the new (current) measure done by the sensor
    *
    * @return
    */
   String getNewValue();
}
